import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class zonaCamara here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zonaCamara extends zonas
{
    private int xPorta, yPorta;
    private boolean portaAberta = false; // Não estático para evitar bugs ao reiniciar o nível
    private String nomePorta;

    public zonaCamara(int x, int y, String nomePorta, int xPorta, int yPorta)
    {
        // Envia as medidas e as cores vermelhas para o construtor de 'zonas'
        super(x, y, new Color(255, 0, 0, 70), new Color(255, 0, 0, 200));
        
        this.nomePorta = nomePorta;
        this.xPorta = xPorta;
        this.yPorta = yPorta;
    }
    public void aoDetetarProta(protas invasor)
    {
        if (!portaAberta)
        {
            abrirPuerta();
            portaAberta = true;
        }
        
        alertarGuardias(getX(), invasor.getY());
    }

    private void abrirPuerta()
    {
        nivel m = (nivel) getWorld();
        if (m != null)
        {
            m.abrePorta(nomePorta, xPorta, yPorta);
        }
    }

    private void alertarGuardias(int destinoX, int destinoY)
    {
        List<inimigo> guardias = getWorld().getObjects(inimigo.class);

        for (inimigo guardia : guardias)
        {
            guardia.activarAlerta(destinoX, destinoY);
        }
    }
    /**
     * Act - do whatever the zonaCamara wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /*public void act()
    {
        verificaZona();
    }

    
    public zonaCamara(String tipo, int x, int y)
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
            if(abrirPuerta){
                abrirPuerta();
                abrirPuerta = false;
            }
            
            alertarGuardias(getX(), invasor.getY());
        }
    }*/
    
}
