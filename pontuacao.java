import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class pontuacao here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class pontuacao extends Actor
{
    private int pontosTotal = 0;
    
    public pontuacao(int pontosIniciais)
    {
        this.pontosTotal = pontosIniciais;
        atualizarImagem();
    }

    public pontuacao()
    {
        this(0);
    }
    
    public void adicionarPontos(int quantidade)
    {
        pontosTotal += quantidade;
        if (pontosTotal < 0) {
            pontosTotal = 0;
        }
        atualizarImagem();
    }
    
    private void atualizarImagem()
    {
        String texto = String.format("PTS: %04d", pontosTotal);

        GreenfootImage img = new GreenfootImage(115, 25);
        
        // Fondo oscuro y borde naranja pastel
        img.setColor(new Color(20, 25, 40, 180));
        img.fillRect(0, 0, 115, 25);
        
        //img.setColor(new Color(255, 179, 128));
        //img.drawRect(0, 0, 114, 24);

        img.setColor(Color.WHITE);
        img.setFont(new Font("Courier New", true, false, 15));
        img.drawString(texto, 15, 18);

        setImage(img);
    }
    
    public int getPontosTotal()
    {
        return pontosTotal;
    }
    /*private void mostrarResultadoFinal()
    {
        // Formato para el cartel final
        String textoPuntos = String.format("PONTOS: %04d", pontosTotal);

        // Lienzo más amplio para destacar al final (ej: 300x120)
        GreenfootImage img = new GreenfootImage(300, 120);
        
        // Fondo oscuro
        img.setColor(new Color(20, 25, 40, 230));
        img.fillRect(0, 0, 300, 120);
        
        // Borde naranja pastel
        img.setColor(new Color(160, 255, 200));
        img.drawRect(0, 0, 299, 119);

        // Título "NIVEL COMPLETADO"
        img.setColor(new Color(160, 255, 200));
        img.setFont(new Font("Courier New", true, false, 20));
        img.drawString("NIVEL COMPLETADO", 55, 40);

        // Puntos en blanco
        img.setColor(Color.WHITE);
        img.setFont(new Font("Courier New", true, false, 24));
        img.drawString(textoPuntos, 65, 85);

        setImage(img);
    }*/
}
