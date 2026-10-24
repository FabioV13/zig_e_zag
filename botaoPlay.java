import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class botaoInicio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class botaoPlay extends Actor
{
    private GreenfootImage botao;
    /**
     * Act - do whatever the botaoInicio wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //
    }
    public botaoPlay()
    {
        botao = new GreenfootImage("botomPlay.png");
        botao.scale(400,200);
        setImage(botao);
    }

}
