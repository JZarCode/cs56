import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
 
/**
 *
 * @author Joel Zarley, Kevin Hitzler
 */
public class View extends JPanel 
{
    private final Game game;
    private final Image image;
 
    View(Game game, Image image)
    {
        this.game = game;
        this.image = image;
    }
    
    @Override
    public void paintComponent( Graphics graphics)
    {  
        super.paintComponent( graphics ); 
        graphics.drawImage( image, 0, 0, game.IMAGE_SIZE, game.IMAGE_SIZE, this );    
    }
     
     
}
