import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class pessoa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class pessoa extends Actor
{
    private int velocidadeAnimacao = 10, imagemAtual = 1;
    private int contadorFrames = 0 ,direcao = 1 ;
    
    /**
     * Act - do whatever the pessoa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /*public void act()
    {
        
    }*/
    public boolean evitarSobreposicao(int posXPrevia, int posYPrevia)
    {
        List<Actor> colisiones = getIntersectingObjects(Actor.class);
    
        for (Actor chocado : colisiones)
        {
            // Si entre los objetos tocados hay un muro o un enemigo, bloqueamos el paso
            if (chocado instanceof muro || chocado instanceof pessoa)
            {
                setLocation(posXPrevia, posYPrevia);
                return false; // Bloquea y sale de la función inmediatamente
            }
        }
        return true;
    }
    public void animacaoCami(GreenfootImage imagem ,GreenfootImage imagem1 ,int velocidad)
    {
        contadorFrames++;
        if(contadorFrames >= velocidadeAnimacao)
        {
            boolean sentidoDeseado = (direcao > 0 && velocidad > 0 || direcao < 0 && velocidad <0) ? true : false;
            if(!sentidoDeseado)
            {
               direcao = -direcao;
               imagem.mirrorHorizontally();
               imagem1.mirrorHorizontally();
            }
            contadorFrames = 0;
            if(imagemAtual == 1)
            {
                imagemAtual = 2;
                setImage(imagem1);
            }else
            {
                imagemAtual = 1;
                setImage(imagem);
            }
        }
    }
}
