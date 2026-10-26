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
    private static int nivel = 1;
    private String nivel1 = "nivel1.jpg";
    private GreenfootImage fundo;
    
    private int pontosIniciais;
    
    private cronometro cronometro;
    private pontuacao pontuacao;
    
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
        addObject(new briefcase("nivel1_salida_a.jpeg"), 1120, 150);
        addObject(new key("nivel1_luz_a.jpg",325+getWidth()/2,170), 150, 350);
    }
    public void preparaNivel()
    {
        if(nivel == 1)
        {
            preparaPersonagens();
            preparaCenario(nivel1);
            colocaZonas();
            colocaObjetivo();
            colocaPainel();
            colocaVida();

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
        addObject(new muro(10,100),-305+getWidth()/2,480);
        
        addObject(new muro(10,850),getWidth()-35,getHeight()/2);// lado del mapa
        addObject(new muro(10,850),35,getHeight()/2);// lado del mapa
        
        addObject(new muro(10,140),325+getWidth()/2,170); // sala del maletin (porta)
    }
    public void colocaEscadas() 
    {
        addObject(new escada(60,250),140+getWidth()/2,getHeight()-150);
        addObject(new escada(60,250),260+getWidth()/2,getHeight()/2-30);
    }
    public void colocaZonas()
    {
        // Zonas de camaras (rojas)
        addObject(new zonaCamara(370, 200 ,"nivel1_a.jpg",-300+getWidth()/2,370), getWidth() - 230, getHeight() - 120); //biblioteca
        addObject(new zonaCamara(250, 200 ,"nivel1_a.jpg",-300+getWidth()/2,370), getWidth() - 170, 120); //sala da mala
        addObject(new zonaCamara(500, 200 ,"nivel1_a.jpg",-300+getWidth()/2,370), getWidth() / 2 + 70, 120); //sala do meio do terceiro andar
    
        // Zona de salida (verde) en la sala superior izquierda
        addObject(new zonaSaida (75, 150), 80, 140); //porta de saida   

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
    public void colocaVida()
    {
        addObject(new vidas(),90,40);
    }
    public void colocaPainel()
    {
        cronometro = new cronometro();
        // Colocado en la parte superior central (X: centro, Y: 40)
        addObject(cronometro, getWidth() / 2, 40);
        
        pontuacao = new pontuacao(pontosIniciais);
        addObject(pontuacao, getWidth() / 2, 70);
    }
    public void somarPontos(int pontos)
    {
        if (pontuacao != null) {
            pontuacao.adicionarPontos(pontos);
        }
    }
    public void finalizarNivelComExito()
    {
        if (cronometro != null) {
            cronometro.detener();
            int bonusTempo = cronometro.calcularBonusTempo();
            somarPontos(bonusTempo); // El total sube en vivo en la barra superior
        }
    }

    public void reiniciaNivel()
    {
        ((vidas) getObjects(vidas.class).get(0)).perderVida();
        Greenfoot.setWorld(new nivel());
    }
}

