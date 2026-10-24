import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class escada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class escada extends Actor
{
    private int cooldown = 0;
    private GreenfootImage escada;
    /**
     * Act - do whatever the escada wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

     public void act()
    {
        sobeEscada();
    }
    public void sobeEscada()
    {
        if (cooldown > 0)
        {
            cooldown--;
            return;
        }
        
        ZIG zig = (ZIG) getOneIntersectingObject(ZIG.class);
        ZAG zag = (ZAG) getOneIntersectingObject(ZAG.class);
        
        int desplazamientoPiso = getImage().getHeight();
    
        // --- Control de ZIG (tecla "s") ---
        if (zig != null)
        {
            // Abajo -> Subir
            if (zig.getY() > getY() && Greenfoot.isKeyDown("s"))
            {
                zig.setLocation(zig.getX(), zig.getY() - desplazamientoPiso);
                cooldown = 20;
            }
            // Arriba -> Bajar
            else if (zig.getY() < getY() && Greenfoot.isKeyDown("s"))
            {
                zig.setLocation(zig.getX(), zig.getY() + desplazamientoPiso -50);
                cooldown = 20;
            }
        }
    
        // --- Control de ZAG (tecla "down") ---
        if (zag != null)
        {
            // Abajo -> Subir
            if (zag.getY() > getY() && Greenfoot.isKeyDown("down"))
            {
                zag.setLocation(zag.getX(), zag.getY() - desplazamientoPiso);
                cooldown = 20;
            }
            // Arriba -> Bajar
            else if (zag.getY() < getY() && Greenfoot.isKeyDown("down"))
            {
                zag.setLocation(zag.getX(), zag.getY() + desplazamientoPiso + 50);
                cooldown = 20;
            }
        }
    }
    public escada(int x,int y)
    {
        escada = new GreenfootImage("escada.png");
        escada.scale(x,y);
        escada.setColor(Color.RED);
        
        // 3. Dibujar un rectángulo en los bordes exactos (0 a width-1, 0 a height-1)
        escada.drawRect(0, 0, escada.getWidth() - 1, escada.getHeight() - 1);
        //escada.setTransparency(0);
        setImage(escada);
    }
}
