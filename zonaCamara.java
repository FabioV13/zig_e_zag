import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zonaCamara here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zonaCamara extends Actor
{
    private int xPorta ,yPorta;
    private static boolean abrirPuerta = true;
    private String nomePorta;
    /**
     * Act - do whatever the zonaCamara wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        verificaZona();
    }
    public zonaCamara(int x ,int y,String nomePorta ,int xPorta , int yPorta)
    {
        GreenfootImage img = new GreenfootImage(x, y);
        
        // Relleno rojo translúcido (R, G, B, Alfa: 0 transparente, 255 opaco)
        img.setColor(new Color(255, 0, 0, 70));
        img.fillRect(0, 0, x, y);

        // Borde rojo visible para ver claramente los límites
        img.setColor(new Color(255, 0, 0, 200));
        img.drawRect(0, 0, x - 1, y - 1);

        setImage(img);
        this.nomePorta = nomePorta;
        this.xPorta = xPorta;
        this.yPorta = yPorta;
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
    private void abrirPuerta()
    {
        nivel m = (nivel) getWorld();
        if (m != null)
        {
            ((nivel) getWorld()).abrePorta(nomePorta, xPorta, yPorta);
        }
    }
    public void verificaZona()
    {
        protas invasor = (protas) getOneIntersectingObject(protas.class);

        if (invasor != null)
        {
            if(abrirPuerta){
                abrirPuerta();
                abrirPuerta = false;
            }
            
            alertarGuardias(getX(), invasor.getY());
        }
    }
}
