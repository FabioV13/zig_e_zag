import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class inimigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class inimigo extends pessoa
{
    private GreenfootImage policia, policia1;
    private int distancia = 500; 
    private int pasoActual = 0;
    private int velocidad = 1;
    /**
     * Act - do whatever the inimigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        alerta();
    }
    public inimigo(){
        policia = new GreenfootImage("inimigo.png");
        policia1 = new GreenfootImage("inimigo1.png");
        policia.scale(40, 40);
        policia1.scale(40, 40);
        setImage(policia);
    }
    public void alerta(){
        int posXPrevia = getX();
        if(pasoActual<300){
            move(velocidad);
            if((pasoActual/10)% 2 == 0)
               setImage(policia);
            else
               setImage(policia1);
        }else
           setImage(policia);
        pasoActual++;
        if (pasoActual >= distancia) {
            velocidad = -velocidad; 
            pasoActual = 0;  
            policia.mirrorHorizontally();
            policia1.mirrorHorizontally();
        }
        evitarSobreposicaoHorizontal(posXPrevia);
    }
}
