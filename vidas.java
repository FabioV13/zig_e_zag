import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class vidas extends Actor
{
    // static para que el contador de vidas persista entre reinicios de nivel
    private static int vidasRestantes = 3; 

    public vidas()
    {
        atualizarImagem();
    }

    /**
     * Dibuja el texto o los corazones en pantalla.
     */
    private void atualizarImagem()
    {
        GreenfootImage img = new GreenfootImage("Vidas: " + vidasRestantes, 24, Color.WHITE, new Color(0, 0, 0, 160));
        setImage(img);
    }

    /**
     * Descuenta una vida compartida.
     * Si quedan vidas, reinicia el nivel actual.
     * Si llega a 0, cambia a la pantalla de Game Over y resetea las vidas para la próxima partida.
     */
    public void perderVida()
    {
        vidasRestantes--;
        atualizarImagem();

        if(vidasRestantes <= 0)
        {
            // Sin vidas: restablece el contador a 3 para la próxima partida y muestra la pantalla final
            vidasRestantes = 3;
            //Greenfoot.setWorld(new GameOver());
        }
    }

    public static int getVidasRestantes()
    {
        return vidasRestantes;
    }

    public static void reiniciarVidas()
    {
        vidasRestantes = 3;
    }
}

