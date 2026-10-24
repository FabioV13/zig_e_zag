import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class protas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class protas extends pessoa
{
    private int velocidad = 2, velocidadeVertical = 0, gravidade = 1, forcaSalto = -14;
    private int  imagemAtual = 1;
    private String teclaIzquierda,teclaDerecha,teclaSalto,teclaBate;
    private GreenfootImage imagem, imagemCami, imagemBate;
    
    private boolean estaBatendo = false;
    /**
     * Act - do whatever the protas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public protas(String nomeImagem,String nomeImagemCami,String nomeImagemBate,String teclaIzquierda,String teclaDerecha,String teclaSalto,String teclaBate)
    {
        imagem = new GreenfootImage(nomeImagem);
        imagemCami = new GreenfootImage(nomeImagemCami);
        imagemBate = new GreenfootImage(nomeImagemBate);
        imagem.scale(60,60);
        imagemCami.scale(60,60);
        imagemBate.scale(60,60);
        setImage(imagem);
        this.teclaIzquierda = teclaIzquierda;
        this.teclaDerecha = teclaDerecha;
        this.teclaSalto = teclaSalto;
        this.teclaBate = teclaBate;
        
    }
    public void act(){
        aplicarGravidade();
        if(Greenfoot.isKeyDown(teclaIzquierda) || Greenfoot.isKeyDown(teclaDerecha))
            caminhar();
        if(Greenfoot.isKeyDown(teclaSalto))
            verificaSalto();
        if(Greenfoot.isKeyDown(teclaBate))
            bater(inimigo.class, imagem, imagemBate);
        verificaSentido(imagem ,imagemCami ,imagemBate ,velocidad);
    }
    public void caminhar(){
        int posXPrevia = getX();
        int posYPrevia = getY();
        if(Greenfoot.isKeyDown(teclaIzquierda))
        {
           velocidad = -2;
           move(velocidad);
           animacaoCami(imagem ,imagemCami);
        }else if(Greenfoot.isKeyDown(teclaDerecha))
        {
           velocidad = 2;
           move(velocidad);
           animacaoCami(imagem ,imagemCami);
        }else{
        setImage(imagem);
        imagemAtual = 1;
        }
        evitarSobreposicao(posXPrevia, posYPrevia);
    }
    private void verificaSalto()
    {
        if (Greenfoot.isKeyDown(teclaSalto) && estaNoChao()) 
        {
            velocidadeVertical = forcaSalto;
        }
    }
    private void aplicarGravidade()
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
    private boolean estaNoChao()
    {
        
        // Está en el suelo si toca el fondo o si está justo encima del otro compañero
        boolean sobreCompanheiro = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, pessoa.class) != null;
        boolean sobreLimites = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, muro.class) != null;
        return  sobreLimites || sobreCompanheiro;
    }
}
