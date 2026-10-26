import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class objetivo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class objetivo extends Actor
{
    private GreenfootImage imagem;
    private int contador = 0;
    private int velocidad = 1;
    private String nomePorta;
    private int xPorta ,yPorta;

    public objetivo(String nomeImagem, int largura, int altura,String nomePorta,int xPorta,int yPorta)
    {
        imagem = new GreenfootImage(nomeImagem);
        imagem.scale(largura, altura);
        setImage(imagem);
        this.nomePorta = nomePorta;
        this.xPorta = xPorta;
        this.yPorta = yPorta;
    }

    /**
     * Act - do whatever the objetivo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        flotar();
        recolheObjetivo();
    }
    public void flotar()
    {
        setLocation(getX(), getY() + velocidad);
        contador++;

        if (contador >= 20) {
            velocidad = -velocidad;
            contador = 0;
        }
    }
    /*public void recolheObjetivo()
    {
        protas jogador = (protas) getOneIntersectingObject(protas.class);
        
        if (jogador != null) {
            if (this instanceof briefcase) {
                jogador.recolhaBriefcase();
            }
            ((nivel) getWorld()).abrePorta(nomePorta, xPorta, yPorta);
            getWorld().removeObject(this);
        }
    }*/
    public void recolheObjetivo()
    {
    protas jugador = (protas) getOneIntersectingObject(protas.class);
    
    if (jugador != null) {
        nivel mundo = (nivel) getWorld();

        if (this instanceof briefcase) {
            jugador.recolhaBriefcase(); // Si tienes esta función en protas
            if (mundo != null) {
                mundo.somarPontos(100); // Suma 100 puntos fijos por el maletín
            }
        } 
        else if (this instanceof key) {
            if (mundo != null) {
                // Usa las variables de la puerta que le pasaste al constructor
                mundo.abrePorta(this.nomePorta, this.xPorta, this.yPorta);
                mundo.somarPontos(50);  // Suma 50 puntos fijos por la llave
            }
        }

        getWorld().removeObject(this); // Retira el objeto del mapa
    }
    }
}
