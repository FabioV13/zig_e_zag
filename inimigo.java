import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class inimigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class inimigo extends pessoa
{
    private GreenfootImage policia, policiaCami ,policiaBate;
    private int xIni ,yIni;
    private int distancia = 500; 
    private int pasoActual = 0;
    private int velocidad = 1 ,direcao = 1;
    private boolean estaOlhando = false, voltarInicio = false;
    private String modoInimigo;
    /**
     * Act - do whatever the inimigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(modoInimigo.equals("patrulha"))
        {
        int offsetX = direcao * 35;
        boolean protaAlcanzable = getOneObjectAtOffset(offsetX, 0, protas.class) != null;
    
        if (protaAlcanzable)
        {
            // Comprobación específica del enemigo: ataca automáticamente a los protagonistas
            bater(protas.class, policia, policiaBate);
        }
        else if (voltarInicio)
        {
            voltaPosInicial();
        }
        else
        {
            patrulha();
        }
        direcao = verificaSentido(policia ,policiaCami ,policiaBate ,velocidad);
        }
    }
    public escada buscaEscada(boolean paraBajar)
    {
        List<escada> todas = getWorld().getObjects(escada.class);
        escada seleccionada = null;
        int menorDistanciaX = 0;
    
        for (escada esc : todas)
        {
            // Si va a bajar, busca la escalera por debajo; si va a subir, por encima
            boolean direccionValida = paraBajar ? (esc.getY() > getY()) : (esc.getY() < getY());
    
            if (direccionValida)
            {
                int distX = Math.abs(esc.getX() - getX());
    
                // Si es la primera que encuentra, o si está más cerca que la anterior guardada
                if (seleccionada == null || distX < menorDistanciaX)
                {
                    menorDistanciaX = distX;
                    seleccionada = esc;
                }
            }
        }
        return seleccionada;
    }
    public void voltaParaPiso()
    {
        boolean necesitaBajar = (yIni - getY()) > 5;
        boolean necesitaSubir = (getY() - yIni) > 5;
    
        // Solo busca escalera si no está en el piso correcto
        if (necesitaBajar || necesitaSubir)
        {
            escada objetivo = buscaEscada(necesitaBajar);
    
            if (objetivo != null)
            {
                // 1. Si no ha llegado a la escalera en X, se desplaza hacia ella
                if (Math.abs(getX() - objetivo.getX()) > 2)
                {
                    vaiParaX(objetivo.getX());
                }
                // 2. Cuando ya está en la escalera, sube o baja verticalmente
                else
                {
                    int velVertical = necesitaBajar ? getWorld().getHeight()/3-20 : -getWorld().getHeight()/3;
                    setLocation(objetivo.getX(), getY() + velVertical);
                }
            }
        }
    }
    public void vaiParaX(int x)
    {
        if(x == getX()){
           estaOlhando = false;
           voltarInicio = false;
           pasoActual = 300;
           }
        else
        {
            if(x - getX() > 0){
                velocidad = 1;
                caminhar(true);
            } else
            {
                velocidad = -1;
                caminhar(true);
            }
        }
    }
    public void voltaPosInicial()
    {
        if(yIni == getY()){
            vaiParaX(xIni);
        }else
            voltaParaPiso();
    }
    public inimigo(String modo ,int x ,int y){
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
    public void patrulha(){
        caminhar(false);
        if (pasoActual >= distancia) {
           velocidad = -velocidad; 
           pasoActual = 0;
        }
    }
    public void caminhar(boolean voltando)
    {
        int posXPrevia = getX();
        int posYPrevia = getY();
        if(pasoActual<300 || verificaVerProtas(200, 40) || voltando){
            move(velocidad);
            animacaoCami(policia ,policiaCami );
        }else
           setImage(policia);
        if(!verificaVerProtas(200, 40) || !voltando)
           pasoActual++;
        evitarSobreposicao(posXPrevia, posYPrevia);
    }
    public boolean verificaVerProtas(int distanciaVision, int margenY)
    {
        var enRango = getObjectsInRange(distanciaVision, protas.class);

        for (protas j : enRango)
        {
            int dx = j.getX() - getX();
            int dy = Math.abs(j.getY() - getY());

            // Comprueba altura vertical
            if (dy <= margenY)
            {
                // Mirando a la derecha y protagonista delante
                if (velocidad > 0 && dx > 0 )
                { 
                    velocidad = 2;
                    estaOlhando = true;
                    return estaOlhando;
                }else if (velocidad < 0 && dx < 0)
                { 
                    velocidad = -2;
                    estaOlhando = true;
                    return estaOlhando;
                }
            }
        }
        
        if(velocidad > 0)
            velocidad = 1;
        else
            velocidad = -1;
            
        if(estaOlhando)
        {
            voltarInicio = true;
            return estaOlhando;
        }else
        {
            voltarInicio = false;
            return estaOlhando;
        }
    }
}
