import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instructions extends Actor
{
private botaoVoltar voltar;
/**
* Act - do whatever the instructions wants to do. This method is called whenever
* the 'Act' or 'Run' button gets pressed in the environment.
*/
public void act()
{
    // Add your action code here.
}
protected void addedToWorld(World world) {
    // Creamos el botón pasándole este panel
    voltar = new botaoVoltar(this);
    
    int posX = getX() + 430;
    int posY = getY() + 230;
    
    world.addObject(voltar, posX, posY);
}
public instructions(){
    int x = 950;
    int y = 550;
    
    // Crea la base de la ventana
    GreenfootImage ventana = new GreenfootImage(x, y);
    ventana.setColor(new Color(20, 25, 40, 235)); // Fondo oscuro translúcido
    ventana.fillRect(0, 0, x, y);
    
    // Borde exterior
    ventana.setColor(new Color(255, 204, 0)); // Borde dorado
    ventana.drawRect(0, 0, x - 1, y - 1);
    
    // Título
    ventana.setColor(new Color(255, 230, 120)); //amarelo
    ventana.setFont(new Font("Courier New", true, false, 30));
    ventana.drawString("INSTRUCTIONS", 360 , 45);
    
     // Líneas de instrucciones
    ventana.setColor(new Color(180, 220, 255)); //azul
    ventana.setFont(new Font("Courier New", true, false, 20));
    ventana.drawString("Jogador ZIG:", 30, 100);
    
    ventana.setColor(Color.WHITE);
    ventana.setFont(new Font("Courier New", false, false, 17));
    ventana.drawString("• Movimento: W (saltar), A(ESQ), D(DIR);", 30, 130);
    ventana.drawString("• Ações: S (subir escada), E (bater). ", 30, 160);;
    
    ventana.setColor(new Color(180, 220, 255)); //azul
    ventana.setFont(new Font("Courier New", true, false, 20));
    ventana.drawString("Jogador ZAG:", 30, 200);
    
    ventana.setColor(Color.WHITE);
    ventana.setFont(new Font("Courier New", false, false, 17));
    ventana.drawString("• Movimento: Setas up(saltar), left(ESQ), right(DIR);", 30, 230);
    ventana.drawString("• Ações: seta down (subir escada), shift (bater). ", 30, 260);
    
    ventana.setColor(new Color(160, 255, 200)); //verde 
    ventana.setFont(new Font("Courier New", true, false, 20));
    ventana.drawString("Objetivo:", 580, 100);
    
    ventana.setColor(Color.WHITE);
    ventana.setFont(new Font("Courier New", false, false, 20));
    ventana.drawString("• Encontrar a mala com as", 580, 130);
    ventana.drawString("informações necessárias para", 580, 160);
    ventana.drawString("impedir os ataques terroristas", 580, 190);
    ventana.drawString("no país.", 580, 220);
    
    ventana.setColor(new Color (200, 200, 255)); //roxo
    ventana.setFont(new Font("Courier New", true, false, 25));
    ventana.drawString("PONTUAÇÃO", 410, 320);
    
    ventana.setColor(Color.WHITE);
    ventana.setFont(new Font("Courier New", false, false, 20));
    ventana.drawString("- Derrota a maior quantidade de inimigos possíveis;", 150, 350);
    ventana.drawString("- Encontra a chave para abrir a sala onde está a mala;", 150, 380);
    ventana.drawString("- Completa o nível no menor tempo possível.", 150, 410);
    
    ventana.setColor(new Color (255, 179, 128)); //laranja
    ventana.setFont(new Font("Courier New", true, false, 25));
    ventana.drawString("Dica:", 30, 470);
    
    ventana.setColor(Color.WHITE);
    ventana.setFont(new Font("Courier New", false, false, 20));
    ventana.drawString("Utiliza as câmaras para distrair aos polícias.", 110, 470);
    
    ventana.setColor(new Color (255, 230, 120));
    ventana.setFont(new Font("Courier New", true, true, 28));
    ventana.drawString("Pronto para jogar? Carrega no X e depois em PLAY!", 20, 520);
    
    setImage(ventana);
    }
} 
