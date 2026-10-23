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
    private String teclaIzquierda,teclaDerecha,teclaSalto;
    private GreenfootImage imagem, imagem1;
    /**
     * Act - do whatever the protas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public protas(String nomeImagem,String nomeImagem1,String teclaIzquierda,String teclaDerecha,String teclaSalto)
    {
        imagem = new GreenfootImage(nomeImagem);
        imagem1 = new GreenfootImage(nomeImagem1);
        imagem.scale(60,60);
        imagem1.scale(60,60);
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
        int posYPrevia = getY();
        if(Greenfoot.isKeyDown(teclaIzquierda))
        {
           move(-velocidad);
           animacaoCami(imagem ,imagem1, -velocidad);
        }else if(Greenfoot.isKeyDown(teclaDerecha))
        {
           move(velocidad);
           animacaoCami(imagem ,imagem1, velocidad);
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
        setLocation(getX(), getY() + velocidadeVertical);

        if (!evitarSobreposicao(posXPrevia, posYPrevia))  
        {
            velocidadeVertical = 0;
            return;
        }

        // Límite del suelo inferior de la pantalla
        int limiteSuelo = getWorld().getHeight() - (getImage().getHeight() / 2);

        if (getY() >= limiteSuelo) 
        {
            setLocation(getX(), limiteSuelo);
            velocidadeVertical = 0;
        } else 
        {
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
