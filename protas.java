import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class protas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class protas extends pessoa
{
    private int velocidad = 2, forcaSalto = -14;
    private int  imagemAtual = 1;
    private String teclaIzquierda,teclaDerecha,teclaSalto,teclaBate;
    private GreenfootImage imagem, imagemCami, imagemBate, imagemMala, imagemCamiMala;
    
    
    
    private String nomeImagemMala, nomeImagemCamiMala;
    
    private boolean estaBatendo = false;
    /**
     * Act - do whatever the protas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public protas(String nomeImagem,String nomeImagemCami,String nomeImagemBate, String nomeImagemMala ,String nomeImagemCamiMala,String teclaIzquierda,String teclaDerecha,String teclaSalto,String teclaBate)
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
        this.nomeImagemMala = nomeImagemMala;
        this.nomeImagemCamiMala = nomeImagemCamiMala;
    }
    public void act(){
        super.act();
        if(Greenfoot.isKeyDown(teclaIzquierda) || Greenfoot.isKeyDown(teclaDerecha))
            caminhar();
        else{
        setImage(imagem);
        imagemAtual = 1;
        }
        if(Greenfoot.isKeyDown(teclaSalto))
            verificaSalto();
        if(Greenfoot.isKeyDown(teclaBate))
            bater(inimigo.class, imagem, imagemBate);
        verificaSentido(imagem ,imagemCami ,imagemBate,velocidad);
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

    private boolean estaNoChao()
    {
        
        // Está en el suelo si toca el fondo o si está justo encima del otro compañero
        boolean sobreCompanheiro = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, pessoa.class) != null;
        boolean sobreLimites = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, muro.class) != null;
        return  sobreLimites || sobreCompanheiro;
    }
    /*public void verificarBriefcase()
    {
        if (isTouching(briefcase.class)) {
            removeTouching(briefcase.class);
            
        imagem = new GreenfootImage(nomeImagemMala);
        imagemCami = new GreenfootImage(nomeImagemCamiMala);
        
        imagem.scale(60,60);
        imagemCami.scale(60,60);
            
        }
    }*/
    public void recolhaBriefcase(){
        imagem = new GreenfootImage(nomeImagemMala);
        imagemCami = new GreenfootImage(nomeImagemCamiMala);
        
        imagem.scale(60,60);
        imagemCami.scale(60,60); 
    
    }
}
