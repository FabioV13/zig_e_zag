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
        if (Greenfoot.mouseClicked(this)){
            World mundo = getWorld();
            // Comprueba si ya existe un panel abierto antes de crear uno nuevo
            if (mundo.getObjects(instructions.class).isEmpty()) {
                mundo.addObject(new instructions(), mundo.getWidth() / 2, mundo.getHeight() / 2);
            }
        }
    }
    public botaoInstructions()
    {
        botao = new GreenfootImage("botomIntructions.png");
        botao.scale(500,200);
        setImage(botao);
    }
}
