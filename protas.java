import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class protas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class protas extends pessoa
{
    private int velocidade = 2, velocidadeVertical = 0, gravidade = 1, forcaSalto = -14;
    private boolean mirandoDerecha = true;
    private String teclaIzquierda,teclaDerecha,teclaSalto;
    private GreenfootImage imagem;
    /**
     * Act - do whatever the protas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public protas(String nomeImagem,String teclaIzquierda,String teclaDerecha,String teclaSalto)
    {
        imagem = new GreenfootImage(nomeImagem);
        imagem.scale(60,60);
        setImage(imagem);
        this.teclaIzquierda = teclaIzquierda;
        this.teclaDerecha = teclaDerecha;
        this.teclaSalto = teclaSalto;
        
    }
    public void act(){
        caminhar();
        aplicarGravidade();
        verificaSalto();
    }
    public void caminhar(){
        int posXPrevia = getX();
        if(Greenfoot.isKeyDown(teclaIzquierda)){
           move(-velocidade);
           if(mirandoDerecha){
             imagem.mirrorHorizontally();
             mirandoDerecha = !mirandoDerecha;
           }
        }else if(Greenfoot.isKeyDown(teclaDerecha))
        {
           move(velocidade);
           if(!mirandoDerecha)
           {
             imagem.mirrorHorizontally();
             mirandoDerecha = !mirandoDerecha;
           }
        }
        evitarSobreposicaoHorizontal(posXPrevia);
    }
    private void verificaSalto()
    {
        if (Greenfoot.isKeyDown(teclaSalto) && estaNoChao()) {
            velocidadeVertical = forcaSalto;
        }
    }
    private void aplicarGravidade()
    {
        int posYPrevia = getY();
        setLocation(getX(), getY() + velocidadeVertical);

        // Comprobar si al caer o subir choca verticalmente con el otro espía (subirse encima)
        Actor outroProta = getOneIntersectingObject(Actor.class);
        if (outroProta != null) {
            setLocation(getX(), posYPrevia);
            velocidadeVertical = 0;
            return;
        }

        // Límite del suelo inferior de la pantalla
        int limiteSuelo = getWorld().getHeight() - (getImage().getHeight() / 2);

        if (getY() >= limiteSuelo) {
            setLocation(getX(), limiteSuelo);
            velocidadeVertical = 0;
        } else {
            velocidadeVertical += gravidade;
        }
    }
    private boolean estaNoChao()
    {
        
        // Está en el suelo si toca el fondo o si está justo encima del otro compañero
        boolean sobreCompanheiro = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, pessoa.class) != null;
        boolean sobreLimites = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, muro.class) != null;
        return  sobreLimites || sobreCompanheiro;
    }
}
