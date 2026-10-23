import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class escada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class escada extends Actor
{
    private GreenfootImage escada;
    /**
     * Act - do whatever the escada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public escada(int x,int y)
    {
        escada = new GreenfootImage("escada.png");
        escada.scale(x,y);
        //muro.setTransparency(0);
        setImage(escada);
    }
}
