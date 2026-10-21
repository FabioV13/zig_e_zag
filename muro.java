import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class muro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class muro extends Actor
{
    private GreenfootImage muro;
    /**
     * Act - do whatever the muro wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public muro(int x,int y)
    {
        muro = new GreenfootImage("muro.png");
        muro.scale(x,y);
        //muro.setTransparency(0);
        setImage(muro);
    }
}
