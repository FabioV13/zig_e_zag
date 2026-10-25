import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class botaoVoltar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class botaoVoltar extends Actor
{
    private GreenfootImage botaoVoltar;
    private instructions painel;
    
    public botaoVoltar(instructions painel) {
        this.painel = painel;
        botaoVoltar = new GreenfootImage("botaoVoltar.png");
        botaoVoltar.scale(80,80);
        setImage(botaoVoltar);
    }
    /**
    * Act - do whatever the botaoVoltar wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public void act() {
    if (Greenfoot.mouseClicked(this)) {
    World mundo = getWorld();
    // Elimina primero el panel y luego este mismo botón
    mundo.removeObject(painel);
    mundo.removeObject(this);
    }
    }
} 

