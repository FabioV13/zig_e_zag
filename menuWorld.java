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
        super(1250, 650, 1); 
        fundo = new GreenfootImage("backgroundMenu.jpg");
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
        colocaObjetos(new botaoPlay());
        colocaObjetos(new botaoInstructions());
    }
    public void colocaObjetos(Actor actor)
    {
        int x ; 
        int y ;
        if(actor instanceof botaoPlay)
        {
           y = getHeight()/2+50;
           x = getWidth()/2;
        }else{
           y = 2*getHeight()/3+60;
           x = getWidth()/2;
        }
        addObject(actor,x,y);
    }
}
