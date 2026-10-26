import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zonaSaida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zonaSaida extends zonas
{
    public zonaSaida(int x, int y)
    {
        // Tons verdes translúcidos para a saída
        super(x, y, new Color(0, 255, 0, 70), new Color(0, 255, 0, 200));
    }

    public void aoDetetarProta(protas fugitivo)
    {
        nivel mundo = (nivel) getWorld();
        if (mundo != null)
        {
            mundo.finalizarNivelComExito();
            mundo.removeObject(this); // Remove a zona para disparar a vitória apenas uma vez
        }
    }
}
