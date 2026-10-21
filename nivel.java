import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class nivel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class nivel extends World
{
    private int nivel = 1;
    private String nivel1 = "nivel1.jpg";
    private GreenfootImage fundo;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public nivel()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1250, 650, 1);
        preparaNivel();
    }
    public void colocaObjetos(Actor actor){
        int x; 
        int y;
        if(actor instanceof inimigo){
           y = getHeight()-50;
           x = 2*getWidth()/3;
        }else if(actor instanceof ZIG){
           y = getHeight()-55;
           x = 225;
        }else{
           y = getHeight()-55;
           x = 150;
        }
        addObject(actor,x,y);
    }
    public void preparaNivel()
    {
        if(nivel == 1)
        {
            preparaPersonagens();
            preparaCenario(nivel1);
        } else
        {
        }
    }
    public void preparaPersonagens()
    {
        colocaObjetos(new inimigo());
        colocaObjetos(new ZIG());
        colocaObjetos(new ZAG());
    }
    public void preparaCenario(String nivelImagem)
    {
        fundo = new GreenfootImage(nivelImagem);
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
        colocaLimites();
    }
    public void colocaLimites() 
    {
        addObject(new muro(1600,10),getWidth()/2,220);
        addObject(new muro(1600,10),getWidth()/2,20);
        addObject(new muro(10,100),-185+getWidth()/2,60);
        addObject(new muro(10,100),325+getWidth()/2,60);
        
        addObject(new muro(1600,10),getWidth()/2,425);
        addObject(new muro(10,380),-300+getWidth()/2,370);
        addObject(new muro(10,100),305+getWidth()/2,270);
        
        addObject(new muro(1600,10),getWidth()/2,630);
        addObject(new muro(10,100),205+getWidth()/2,480);
        
        addObject(new muro(10,850),getWidth()-35,getHeight()/2);
        addObject(new muro(10,850),35,getHeight()/2);
        
    }
}
