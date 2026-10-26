import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zonas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class zonas extends Actor
{
    public zonas(int x, int y, Color colorRelleno, Color colorBorde)
    {
        GreenfootImage img = new GreenfootImage(x, y);
        
        img.setColor(colorRelleno);
        img.fillRect(0, 0, x, y);

        img.setColor(colorBorde);
        img.drawRect(0, 0, x - 1, y - 1);

        setImage(img);
    }

    public void act()
    {
        verificaZona();
    }

    public void verificaZona()
    {
        protas invasor = (protas) getOneIntersectingObject(protas.class);

        if (invasor != null)
        {
            aoDetetarProta(invasor);
        }
    }

    // Cada subclase implementa qué ocurre al entrar en contacto con el protagonista
    public abstract void aoDetetarProta(protas prota);
}
