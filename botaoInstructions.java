import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class botaoInstrucoes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class botaoInstructions extends Actor

{
    private GreenfootImage botao;
    /**
     * Act - do whatever the botaoInstrucoes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
        public botaoInstructions()
    {
        botao = new GreenfootImage("botomIntructions.png");
        botao.scale(300,200);
        setImage(botao);
    }
}
