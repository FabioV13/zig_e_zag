import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class inimigo extends pessoa
{
    private GreenfootImage policia, policiaCami, policiaBate;
    private final int xIni, yIni;
    private int distancia = 500; 
    private int pasoActual = 0;
    private int velocidad = 1, direcao = 1;
    private int alertaX, alertaY;

    private boolean estaOlhando = false, voltarInicio = false;
    private String modoInimigo, modoInimigoIni;

    public inimigo(String modo, int x, int y)
    {
        modoInimigoIni = modo;
        modoInimigo = modo;
        xIni = x;
        yIni = y;
        policia = new GreenfootImage("inimigo.png");
        policiaCami = new GreenfootImage("inimigo1.png");
        policiaBate = new GreenfootImage("inimigoBate.png");
        policia.scale(60, 60);
        policiaCami.scale(60, 60);
        policiaBate.scale(60, 60);
        setImage(policia);
    }

    public void act()
    {
        int posXPrevia = getX();
        int posYPrevia = getY();
        super.act();
        
        int offsetX = direcao * 35;
        boolean protaAlcanzable = getOneObjectAtOffset(offsetX, 0, protas.class) != null;

        // 1. Prioridad: Golpear
        if (protaAlcanzable)
        {
            bater(protas.class, policia, policiaBate);
        }
        // 2. Volver a casa (ya sea tras perder de vista o por alarma resuelta)
        else if (voltarInicio)
        {
            if (vaiPosicao(xIni, yIni))
            {
                voltarInicio = false;
                modoInimigo = modoInimigoIni;
                pasoActual = 0;
                velocidad = 1;
            }
        }
        // 3. Atender alerta
        else if ("alerta".equals(modoInimigo))
        {
            if (vaiPosicao(alertaX, alertaY))
            {
                notificarLlegadaAlerta();
            }
        }
        // 4. Patrulla normal
        else if ("patrulha".equals(modoInimigo))
        {
            patrulha();
        } 

        evitarSobreposicao(posXPrevia, posYPrevia);
        direcao = verificaSentido(policia, policiaCami, policiaBate, velocidad);
    }

    public escada buscaEscada(boolean paraBajar)
    {
        List<escada> todas = getWorld().getObjects(escada.class);
        escada seleccionada = null;
        int menorDistanciaX = 0;
        int menorDistanciaY = 0;

        for (escada esc : todas)
        {
            boolean direccionValida = paraBajar ? (esc.getY() > getY()) : (esc.getY() < getY());

            if (direccionValida)
            {
                int distX = Math.abs(esc.getX() - getX());
                int distY = Math.abs(esc.getY() - getY());

                if (seleccionada == null || distY < menorDistanciaY - 10)
                {
                    menorDistanciaY = distY;
                    menorDistanciaX = distX;
                    seleccionada = esc;
                }
                else if (Math.abs(distY - menorDistanciaY) <= 10 && distX < menorDistanciaX)
                {
                    menorDistanciaY = distY;
                    menorDistanciaX = distX;
                    seleccionada = esc;
                }
            }
        }
        return seleccionada;
    }

    public void notificarLlegadaAlerta()
    {
        if (getWorld() == null) return;
        List<inimigo> companheiros = getWorld().getObjects(inimigo.class);

        for (inimigo policiaActivo : companheiros)
        {
            if ("alerta".equals(policiaActivo.modoInimigo))
            {
                policiaActivo.voltarInicio = true;
                policiaActivo.modoInimigo = "retorno";
            }
        }
    }

    public void vaiParaPiso(int y)
    {
        boolean necesitaBajar = (y - getY()) > 5;
        boolean necesitaSubir = (getY() - y) > 5;

        if (necesitaBajar || necesitaSubir)
        {
            escada objetivo = buscaEscada(necesitaBajar);

            if (objetivo != null)
            {
                // Si aún no está centrado en la escalera, camina hacia ella
                if (Math.abs(getX() - objetivo.getX()) > 4)
                {
                    vaiParaX(objetivo.getX());
                }
                // Si ya está alineado en la escalera, sube o baja
                else
                {
                    int velVertical = necesitaBajar ? (getWorld().getHeight() / 3 - 20) : (-getWorld().getHeight() / 3);
                    setLocation(objetivo.getX(), getY() + velVertical);

                    // Pequeño desplazamiento lateral para despejar la escalera al cambiar de piso
                    int pasoSalida = (xIni >= getX()) ? 6 : -6;
                    setLocation(getX() + pasoSalida, getY());
                }
            }
        }
    }

    public void vaiParaX(int x)
    {
        if (Math.abs(x - getX()) > 2)
        {
            velocidad = (x > getX()) ? 1 : -1;
            caminhar(true);
        }
        else
        {
            estaOlhando = false;
        }
    }

    public boolean vaiPosicao(int x, int y)
    {
        // 1. Si no está en el piso adecuado, busca y usa la escalera
        if (Math.abs(y - getY()) > 5)
        {
            vaiParaPiso(y);
            return false;
        }
        // 2. Si ya está en el piso, ajusta la posición horizontal
        else if (Math.abs(x - getX()) > 4)
        {
            vaiParaX(x);
            return false;
        }
        
        // Ha llegado dentro del margen de tolerancia
        return true;
    }

    public void patrulha()
    {
        caminhar(false);
        if (pasoActual >= distancia)
        {
            velocidad = -velocidad; 
            pasoActual = 0;
        }
    }

    public void caminhar(boolean voltando)
    {
        boolean viendoProta = verificaVerProtas(200, 40);

        if (pasoActual < 300 || viendoProta || voltando)
        {
            move(velocidad);
            animacaoCami(policia, policiaCami);
        }
        else
        {
            setImage(policia);
        }

        if (!viendoProta && !voltando)
        {
            pasoActual++;
        }
    }

    public void activarAlerta(int x, int y)
    {
        modoInimigo = "alerta";
        voltarInicio = false;
        alertaX = x;
        alertaY = y;
    }

    public boolean verificaVerProtas(int distanciaVision, int margenY)
    {
        List<protas> enRango = getObjectsInRange(distanciaVision, protas.class);

        for (protas j : enRango)
        {
            int dx = j.getX() - getX();
            int dy = Math.abs(j.getY() - getY());

            if (dy <= margenY)
            {
                if (velocidad > 0 && dx > 0)
                { 
                    velocidad = 2;
                    estaOlhando = true;
                    return true;
                }
                else if (velocidad < 0 && dx < 0)
                { 
                    velocidad = -2;
                    estaOlhando = true;
                    return true;
                }
            }
        }
        
        // Si perseguía al jugador y acaba de salir del rango de visión:
        if (estaOlhando)
        {
            estaOlhando = false;
            voltarInicio = true; // Comienza a volver al punto inicial
        }

        // Restablece velocidad normal
        velocidad = (velocidad > 0) ? 1 : -1;
        return false;
    }
}
