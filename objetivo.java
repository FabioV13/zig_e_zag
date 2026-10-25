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

    public objetivo(String nomeImagem, int largura, int altura)
    {
        imagem = new GreenfootImage(nomeImagem);
        imagem.scale(largura, altura);
        setImage(imagem);
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
    /*public void recolheObjetivo(){
        if (isTouching(protas.class)){
            removeTouching(objetivo.class);
            protas objeto = getOneIntersectingObject(briefcas.class);
            if (this instanceof briefcase){
               objeto.recolhaBriefcase();
            }else{
                
            }
        }
    }*/
    public void recolheObjetivo()
    {
        protas jugador = (protas) getOneIntersectingObject(protas.class);
        
        if (jugador != null) {
            if (this instanceof briefcase) {
                jugador.recolhaBriefcase();
            }
            getWorld().removeObject(this);
        }
    }
}
