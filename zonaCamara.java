import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zonaCamara here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zonaCamara extends Actor
{
    /**
     * Act - do whatever the zonaCamara wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        verificaZona();
    }
    public zonaCamara(int x ,int y)
    {
        GreenfootImage img = new GreenfootImage(x, y);
        
        // Relleno rojo translúcido (R, G, B, Alfa: 0 transparente, 255 opaco)
        img.setColor(new Color(255, 0, 0, 70));
        img.fillRect(0, 0, x, y);

        // Borde rojo visible para ver claramente los límites
        img.setColor(new Color(255, 0, 0, 200));
        img.drawRect(0, 0, x - 1, y - 1);

        setImage(img);
    }
    public void verificaZona()
    {
        protas invasor = (protas) getOneIntersectingObject(protas.class);

        if (invasor != null)
        {
            
        }
    }
}
