import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class pessoa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class pessoa extends Actor
{
    /**
     * Act - do whatever the pessoa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /*public void act()
    {
        
    }*/
    public void evitarSobreposicaoHorizontal(int posXPrevia)
    {
        Actor outroActor = getOneIntersectingObject(Actor.class);
        if (outroActor != null) {
            setLocation(posXPrevia, getY());
        }
    }
}
