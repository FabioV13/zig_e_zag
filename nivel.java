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
    public void colocaObjetos()
    {
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
        //Adiciona aos inimigos
        addObject(new inimigo("patrulha",getWidth()/2-200,getHeight()-55),getWidth()/2-200,getHeight()-55);
        addObject(new inimigo("patrulha",getWidth()/2-200,getHeight()/2+65),getWidth()/2-200,getHeight()/2+65);
        addObject(new inimigo("camara",150,getHeight()/2+65),150,getHeight()/2+65);
        addObject(new inimigo("camara",250,getHeight()/2+65),250,getHeight()/2+65);
        
        //Adiciona aos espioes
        addObject(new ZIG(),225,getHeight()-55);
        addObject(new ZAG(),150,getHeight()-55);

    }
    public void preparaCenario(String nivelImagem)
    {
        fundo = new GreenfootImage(nivelImagem);
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
        colocaLimites();
        colocaEcadas();
    }
    public void colocaLimites() 
    {
        addObject(new muro(1600,10),getWidth()/2,220);
        addObject(new muro(1600,10),getWidth()/2,20);
        addObject(new muro(10,100),-185+getWidth()/2,60);
        addObject(new muro(10,100),325+getWidth()/2,60);
        
        addObject(new muro(1600,10),getWidth()/2,425);
        addObject(new muro(10,120),-300+getWidth()/2,370);
        addObject(new muro(10,100),-300+getWidth()/2,270);
        addObject(new muro(10,100),305+getWidth()/2,270);
        
        addObject(new muro(1600,10),getWidth()/2,630);
        addObject(new muro(10,100),205+getWidth()/2,480);
        
        addObject(new muro(10,850),getWidth()-35,getHeight()/2);
        addObject(new muro(10,850),35,getHeight()/2);
        
    }
    public void colocaEcadas() 
    {
        addObject(new escada(60,250),150+getWidth()/2,getHeight()-150);
        addObject(new escada(60,250),250+getWidth()/2,getHeight()/2-30);
    }
}
