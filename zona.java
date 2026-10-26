import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class zonaCamara here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zona extends Actor
{
    private String tipo;
    /**
     * Act - do whatever the zonaCamara wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        verificaZona();
    }
    /*public zonaCamara(int x ,int y)
    {
        GreenfootImage img = new GreenfootImage(x, y);
        
        // Relleno rojo translúcido (R, G, B, Alfa: 0 transparente, 255 opaco)
        img.setColor(new Color(255, 0, 0, 70));
        img.fillRect(0, 0, x, y);

        // Borde rojo visible para ver claramente los límites
        img.setColor(new Color(255, 0, 0, 200));
        img.drawRect(0, 0, x - 1, y - 1);

        setImage(img);
    }*/
    
    public zona(String tipo, int x, int y)
    {
        this.tipo = tipo;
        
        GreenfootImage img = new GreenfootImage(x, y);

        if (tipo.equals("camara")) {
            // Rojo para cámaras de seguridad
            img.setColor(new Color(255, 0, 0, 70));
            img.fillRect(0, 0, x, y);
            img.setColor(new Color(255, 0, 0, 200));
            img.drawRect(0, 0, x - 1, y - 1);
        } 
        else if (tipo.equals("salida")) {
            // Verde para la zona de escape final
            img.setColor(new Color(0, 255, 0, 70));
            img.fillRect(0, 0, x, y);
            img.setColor(new Color(0, 255, 0, 200));
            img.drawRect(0, 0, x - 1, y - 1);
        }

        setImage(img);
    }
    
    private void alertarGuardias(int destinoX, int destinoY)
    {
        // Obtiene todos los policías del mapa
        java.util.List<inimigo> guardias = getWorld().getObjects(inimigo.class);
    
        for (inimigo guardia : guardias)
        {
            // Les indica a dónde deben acudir
            guardia.activarAlerta(destinoX, destinoY);
        }
    }
    public void verificaZona()
    {
        protas invasor = (protas) getOneIntersectingObject(protas.class);
        
        if (invasor != null)
        {
            if (tipo.equals("camara")) {
                alertarGuardias(invasor.getX(), invasor.getY());
            } 
            else if (tipo.equals("salida")) {
                nivel mundo = (nivel) getWorld();
                if (mundo != null) {
                    mundo.finalizarNivelConExito();
                    mundo.removeObject(this); // Evita ejecuciones repetidas
                }
            }
        }
        /*if (invasor != null)
        {
            alertarGuardias(invasor.getX(), invasor.getY());
        }*/
    }
}
