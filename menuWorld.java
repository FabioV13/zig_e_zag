import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menuWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menuWorld extends World
{
    private GreenfootImage fundo;
    /**
     * Constructor for objects of class menuWorld.
     * 
     */
    public menuWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 500, 1); 
        fundo = new GreenfootImage("nivel1_s.jpg");
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
    }
}
