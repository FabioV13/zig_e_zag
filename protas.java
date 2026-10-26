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
    private String teclaIzquierda,teclaDerecha,teclaSalto,teclaBate ,teclaEscada;
    private GreenfootImage imagem, imagemCami, imagemBate, imagemMala, imagemCamiMala;
    private int cooldown;
    
    
    private String nomeImagemMala, nomeImagemCamiMala;
    
    private boolean estaBatendo = false;
    /**
     * Act - do whatever the protas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public protas(String nomeImagem,String nomeImagemCami,String nomeImagemBate, String nomeImagemMala ,String nomeImagemCamiMala,String teclaIzquierda,String teclaDerecha,String teclaSalto,String teclaBate, String teclaEscada)
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
        this.teclaEscada = teclaEscada;
        this.nomeImagemMala = nomeImagemMala;
        this.nomeImagemCamiMala = nomeImagemCamiMala;
    }
    public void act(){
        int posXPrevia = getX();
        int posYPrevia = getY();
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
        if(Greenfoot.isKeyDown(teclaEscada))
            sobeEscada();
        evitarSobreposicao(posXPrevia, posYPrevia);
        verificaSentido(imagem ,imagemCami ,imagemBate,velocidad);
    }
    public void caminhar(){
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
    public void sobeEscada()
    {
        if (cooldown > 0)
        {
            cooldown--;
            return;
        }
        
        escada escada = (escada) getOneIntersectingObject(escada.class);

        // --- Control de ZIG (tecla "s") ---
        if (escada != null)
        {
            int desplazamientoPiso = escada.getImage().getHeight();
            // Abajo -> Subir
            if (getY() > escada.getY())
            {
                setLocation(getX(), getY() - desplazamientoPiso);
                cooldown = 20;
            }
            // Arriba -> Bajar
            else if (getY() < escada.getY())
            {
                setLocation(getX(), getY() + desplazamientoPiso -50);
                cooldown = 20;
            }
        }
    }
    public void recolhaBriefcase(){
        imagem = new GreenfootImage(nomeImagemMala);
        imagemCami = new GreenfootImage(nomeImagemCamiMala);
        if(velocidad < 0)
        {
            imagem.mirrorHorizontally();
            imagemCami.mirrorHorizontally();
        }
        imagem.scale(60,60);
        imagemCami.scale(60,60); 
    }
}
