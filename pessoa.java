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
    protected int velocidadeVertical = 0, gravidade = 1;
    /**
     * Act - do whatever the pessoa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        aplicarGravidade(); 
    }
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
    public void animacaoCami(GreenfootImage imagem ,GreenfootImage imagemCami)
    {
        contadorFrames++;
        if(contadorFrames >= velocidadeAnimacao)
        {
            contadorFrames = 0;
            if(imagemAtual == 1)
            {
                imagemAtual = 2;
                setImage(imagemCami);
            }else
            {
                imagemAtual = 1;
                setImage(imagem);
            }
        }
    }
    public void bater(Class<? extends pessoa> alvoClass, GreenfootImage imgBase, GreenfootImage imgGolpe)
    {
        // 1. Ejecuta la animación de golpe reutilizando animacaoCami
        animacaoCami(imgBase, imgGolpe);
    
        // 2. Comprueba el impacto hacia el frente según hacia dónde mira (direcao: 1 o -1)
        int alcanceGolpe = 35;
        int offsetX = direcao * alcanceGolpe;
    
        pessoa alvo = (pessoa) getOneObjectAtOffset(offsetX, 0, alvoClass);
    
        // 3. Diferencia entre eliminar al enemigo o reiniciar el nivel si atrapa al protagonista
        if (alvo != null && alvo.getWorld() != null)
        {
            if (alvo instanceof protas)
            {
                ((nivel)getWorld()).reiniciaNivel();
            }
            else if (alvo instanceof inimigo)
            {
                nivel m = (nivel) getWorld();
                    if (m != null)
                    {
                        m.somarPontos(30); // Suma los 30 puntos en tiempo real
                    }
            getWorld().removeObject(alvo);
                
            }
        }
    }
    public int verificaSentido(GreenfootImage imagem ,GreenfootImage imagemCami,GreenfootImage imagemBate ,int velocidad)
    {
        boolean sentidoDeseado = (direcao > 0 && velocidad > 0 || direcao < 0 && velocidad <0) ? true : false;
        if(!sentidoDeseado)
        {
            direcao = -direcao;
            imagem.mirrorHorizontally();
            imagemCami.mirrorHorizontally();
            imagemBate.mirrorHorizontally();
        }
        return direcao;
    }
    public void aplicarGravidade()
    {
        int posXPrevia = getX();
        int posYPrevia = getY();
        
        // 1. Mover hacia abajo según la velocidad actual
        setLocation(getX(), getY() + velocidadeVertical);
    
        // 2. Si colisionó con el suelo o muro invisible
        if (!evitarSobreposicao(posXPrevia, posYPrevia))  
        {
            velocidadeVertical = 0; // Detiene la caída al tocar el suelo
            return;
        }
    
        // 3. Si no colisionó, significa que sigue en el aire: acelera la caída
        velocidadeVertical += gravidade;
    }
}
