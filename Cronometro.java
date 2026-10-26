import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Cronometro extends Actor
{
    private static int tiempoSegundos = 0;
    private int contadorCiclos = 0;
    private boolean activo = true;

    public Cronometro()
    {
        actualizarImagen();
    }

    public void act()
    {
        if (activo) {
            contarTiempo();
        }
    }

    private void contarTiempo()
    {
        contadorCiclos++;
        
        // A velocidad normal de Greenfoot (~55-60 fps), ~60 ciclos equivalen a 1 segundo
        if (contadorCiclos >= 60) {
            tiempoSegundos++;
            contadorCiclos = 0;
            actualizarImagen();
        }
    }

    private void actualizarImagen()
    {
        int minutos = tiempoSegundos / 60;
        int segundos = tiempoSegundos % 60;

        // Formato MM:SS (por ejemplo: 01:05)
        String texto = String.format("%02d:%02d", minutos, segundos);

        GreenfootImage img = new GreenfootImage(105, 25);
        
        // Opcional: fondo oscuro semitransparente para que se lea bien sobre el mapa
        img.setColor(new Color(20, 25, 40, 180));
        img.fillRect(0, 0, 105, 25);
        
        // Borde en naranja pastel
        img.setColor(new Color(255, 179, 128));
        img.drawRect(0, 0, 104, 24);

        // Texto del cronómetro en blanco
        img.setColor(Color.WHITE);
        img.setFont(new Font("Courier New", true, false, 17));
        img.drawString(texto, 27, 18);

        setImage(img);
    }

    // Métodos útiles para controlar el cronómetro desde el nivel o juego
    public void detener()
    {
        activo = false;
    }

    public void reanudar()
    {
        activo = true;
    }

    public int getTiempoSegundos()
    {
        return tiempoSegundos;
    }
}