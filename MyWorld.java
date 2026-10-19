import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 500, 1); 
        colocaObjetos(new pessoa());
    }
        public void colocaObjetos(Actor actor){
        int x; 
        int y;
        if(actor instanceof inimigo){
            x =Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight()/2);
        }else{
           y = getHeight()-50;
           x = getWidth()/2;
        }
        addObject(actor,x,y);
    }
}
