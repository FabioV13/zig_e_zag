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

}
