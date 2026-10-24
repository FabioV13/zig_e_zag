import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public void voltaParaPiso()
    {
        
    }
    public void voltaParaX()
    {
        if(xIni == getX()){
           estaOlhando = false;
           voltarInicio = false;
           pasoActual = 300;
           }
        else
        {
            if(xIni - getX() > 0){
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
            voltaParaX();
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
