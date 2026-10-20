import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class nivel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class nivel extends World
{

    private GreenfootImage fundo;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public nivel()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 500, 1); 
        colocaObjetos(new inimigo());
        colocaObjetos(new ZIG());
        colocaObjetos(new ZAG());
        fundo = new GreenfootImage("nivel1.jpg");
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
    }
        public void colocaObjetos(Actor actor){
        int x; 
        int y;
        if(actor instanceof inimigo){
           y = getHeight()-50;
           x = 2*getWidth()/3;
        }else if(actor instanceof ZIG){
           y = getHeight()-50;
           x = getWidth()/2;
        }else{
           y = getHeight()-50;
           x = getWidth();
        }
        addObject(actor,x,y);
    }
}
