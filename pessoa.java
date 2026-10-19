import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class pessoa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class pessoa extends Actor
{
    private GreenfootImage imagem1;
    /**
     * Act - do whatever the pessoa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public pessoa(){
        imagem1 = new GreenfootImage("inimigo.png");
        imagem1.scale(50, 50);
        setImage(imagem1);
    }
}
