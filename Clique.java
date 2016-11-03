import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Joel Zarley
 */
public class Clique 
{    
    /* For this use case, we make the data members below attributes of Clique. 
     * If Clique is used by other classes, these attributes may best be thought 
     * of as NOT being attributes of Clique.
     */
    
    private static final int IMAGE_SIZE = 800; // units: pixels
    private final Image image;
    private final ImageIcon imageIcon;
    private final JLabel jLabel;
    private final JFrame jFrame;
    
    private final int nNodes; // # of nodes in the clique
    
    // optionally add dependent parameters
    private final int nodeDiameter;
    private final int borderSize;
    private final double nodeTheta;
    private final int radiusLength;
    
    //attribute for paint and private methods
    private double tmpTheta = 0.0;
       
    Clique()
    {
        //skeleton given
        image = new BufferedImage( IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jLabel = new JLabel( imageIcon );
        jFrame = new JFrame( "Clique" );
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container container = jFrame.getContentPane();
        container.setLayout( new BorderLayout() );
        container.add( jLabel, BorderLayout.CENTER );
        jFrame.pack();
        
        // set image parameters
        nNodes = getInt( "What is the size of the clique [3 - 30]?" );
        borderSize = ( IMAGE_SIZE / 100 );
        
        // set dependent parameter values
        nodeTheta = (2 * Math.PI) / nNodes;
        nodeDiameter = (IMAGE_SIZE / nNodes);
        //radius from midpoint to center of node
        radiusLength = (IMAGE_SIZE / 2) - ( borderSize + (nodeDiameter / 2) );
          
    }
    
    /**
     * @param args the command line arguments: Unused.
     */
    public static void main(String[] args) 
    {
        Clique clique = new Clique();
        clique.paint();
        clique.view();
    }
    
    private void view() { jFrame.setVisible( true ); }
    
    private int getInt( String promptString )
    {
	// convert returned value from JOptionPane.showInputDialog to an int
	String IntNodes = JOptionPane.showInputDialog(promptString);
        return Integer.parseInt( IntNodes );
    }
        
    /**
     * Paint the clique representation onto the Image.
     */
    private void paint()
    {
        Graphics graphics = image.getGraphics();      
        graphics.setColor(Color.white);
        graphics.fillRect(0,0, IMAGE_SIZE, IMAGE_SIZE);
        int xcoor = getXEdge();
        int ycoor = getYEdge();

        for( int j = 0; j < nNodes; j++)
	    {
		for( int k = j; k < nNodes; k++)
		    {               
			float red = Math.abs((float)Math.cos(nodeTheta * j));
			float green = 0.0f;
			float blue = Math.abs((float)Math.sin(nodeTheta * k));
			graphics.setColor(new Color(red, green, blue));  
			tmpTheta = tmpTheta + nodeTheta;
			graphics.drawLine(xcoor, ycoor, getXEdge(), getYEdge());              
		    }
		tmpTheta = nodeTheta + (nodeTheta * j);
		xcoor = getXEdge();
		ycoor = getYEdge();
	    }

	tmpTheta = 0.0; //reset tmpTheta
       
	for( int i = 0; i < nNodes; i++)
	    {
		xcoor = getXOval();
		ycoor = getYOval();
		graphics.setColor(Color.red);
		graphics.drawOval( xcoor , ycoor, nodeDiameter, nodeDiameter);
		graphics.fillOval( xcoor, ycoor, nodeDiameter, nodeDiameter);
		graphics.setColor(Color.black);
		graphics.drawOval(xcoor, ycoor, nodeDiameter, nodeDiameter);
		tmpTheta = tmpTheta + nodeTheta;
	    } 
        
    }   
    // make private methods, if that simplifies your paint code.
    
    // xcoor for ovals
    private int getXOval(){
        double tmpX = Math.cos(tmpTheta) * radiusLength  + (IMAGE_SIZE / 2) - (nodeDiameter / 2);
        return (int) tmpX;
    }
    // ycoor for ovals
    private int getYOval(){
        double tmpY = Math.sin(tmpTheta) * radiusLength + (IMAGE_SIZE / 2) - (nodeDiameter / 2);
        return (int) tmpY;
    }
    
    // xcoor for edges
    private int getXEdge(){
        double tmpX = Math.cos(tmpTheta) * radiusLength  + (IMAGE_SIZE / 2);
        return (int) tmpX;
    }
    // ycoor for edges
    private int getYEdge(){
        double tmpX = Math.sin(tmpTheta) * radiusLength + (IMAGE_SIZE / 2);
        return (int) tmpX;
    }
    
}
