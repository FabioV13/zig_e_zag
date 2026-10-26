import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class nivel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class nivel extends World
{
    private int nivel = 1;
    private String nivel1 = "nivel1.jpg";
    private GreenfootImage fundo;
    
    private cronometro cronometro;
    private int puntosAcumulados = 0; // Guarda los puntos en silencio durante la partida
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public nivel()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1250, 650, 1);
        preparaNivel();
    }
    public void colocaObjetivo()
    {
        addObject(new briefcase(), 1120, 150);
        addObject(new key(), 150, 350);
    }
    public void preparaNivel()
    {
        if(nivel == 1)
        {
            preparaPersonagens();
            preparaCenario(nivel1);
            colocaZonas();
            colocaObjetivo();
            colocaCronometro();
        } else
        {
        }
    }
    public void preparaPersonagens()
    {
        //Adiciona aos inimigos
        addObject(new inimigo("patrulha",getWidth()/2-200,getHeight()-55),getWidth()/2-200,getHeight()-55);
        addObject(new inimigo("patrulha",getWidth()/2-200,getHeight()/2+65),getWidth()/2-200,getHeight()/2+65);
        addObject(new inimigo("camara",150,getHeight()/2+65),150,getHeight()/2+65);
        addObject(new inimigo("camara",250,getHeight()/2+65),250,getHeight()/2+65);
        
        //Adiciona aos espioes
        addObject(new ZIG(),225,getHeight()-55);
        addObject(new ZAG(),150,getHeight()-55);

    }
    public void preparaCenario(String nivelImagem)
    {
        fundo = new GreenfootImage(nivelImagem);
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
        colocaLimites();
        colocaEscadas();
    }
    public void colocaLimites() 
    {
        addObject(new muro(1600,10),getWidth()/2,220); //tercer piso
        addObject(new muro(1600,10),getWidth()/2,20); // parte de arriba del mapa
        addObject(new muro(10,100),-185+getWidth()/2,60); // sala final
        addObject(new muro(10,100),325+getWidth()/2,60); // sala del maletin 
        
        addObject(new muro(1600,10),getWidth()/2,425);// segundo piso
        addObject(new muro(10,120),-300+getWidth()/2,370);// sala de la llave (puerta)
        addObject(new muro(10,100),-300+getWidth()/2,270);// sala de la llave (arriba)
        addObject(new muro(10,100),305+getWidth()/2,270);// sala de la electricidad
        
        addObject(new muro(1600,10),getWidth()/2,630); //parte de abajo del mapa
        addObject(new muro(10,100),205+getWidth()/2,480); //sala de "biblioteca"
        
        addObject(new muro(10,850),getWidth()-35,getHeight()/2);// lado del mapa
        addObject(new muro(10,850),35,getHeight()/2);// lado del mapa
        
        addObject(new muro(10,140),325+getWidth()/2,170); // sala del maletin (porta)
    }
    public void colocaEscadas() 
    {
        addObject(new escada(60,250),140+getWidth()/2,getHeight()-150);
        addObject(new escada(60,250),260+getWidth()/2,getHeight()/2-30);
    }
    /*public void colocaZonaCamara()
    {
        addObject(new zonaCamara(370, 200) , getWidth()-230, getHeight()-120);
        
        addObject(new zonaCamara(250, 200) , getWidth()-170, 120);
        addObject(new zonaCamara(500, 200) , getWidth()/2+70, 120);
    }*/
    public void colocaZonas()
    {
        // Zonas de camaras (rojas)
        addObject(new zona("camara", 370, 200), getWidth() - 230, getHeight() - 120); //biblioteca
        addObject(new zona("camara", 250, 200), getWidth() - 170, 120); //sala da mala
        addObject(new zona("camara", 500, 200), getWidth() / 2 + 70, 120); //sala do meio do terceiro andar
    
        // Zona de salida (verde) en la sala superior izquierda
        addObject(new zona("salida", 75, 150), 80, 140); //porta de saida
    }
    public void abrePorta(String nomeImagem ,int removeX ,int removeY)
    {
        fundo = new GreenfootImage(nomeImagem);
        fundo.scale(getWidth(), getHeight());
        setBackground(fundo);
        
        List<muro> muros = getObjectsAt(removeX, removeY, muro.class);
        if (!muros.isEmpty())
        {
            removeObject(muros.get(0));
        }
    }
    public void colocaCronometro()
    {
        cronometro = new cronometro();
        // Colocado en la parte superior central (X: centro, Y: 40)
        addObject(cronometro, getWidth() / 2, 40);
        
    }
    public void somarPontos(int pontos)
    {
        puntosAcumulados += pontos;
        if (puntosAcumulados < 0) {
            puntosAcumulados = 0;
        }
    }
    public void finalizarNivelConExito()
    {
        int bonusTiempo = 0;
        if (cronometro != null) {
            cronometro.detener();
            bonusTiempo = cronometro.calcularBonusTiempo();
            somarPontos(bonusTiempo);
        }

        // Crea el cartel final mostrando el total (llave + maletin + bonus de tiempo)
        pontuacao cartel = new pontuacao(puntosAcumulados);
        addObject(cartel, getWidth() / 2, getHeight() / 2);

        // Opcional: pausar el bucle de Greenfoot al ganar
        Greenfoot.stop();
    }
}
