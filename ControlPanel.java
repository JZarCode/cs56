import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.text.DecimalFormat;
 
/**
 *
 * @author Joel Zarley, Kevin Hitzler
 */
public class ControlPanel extends JPanel implements ActionListener, MouseListener
{
    private final static int PAUSE = 150; // milliseconds 150
     
    private final View view;
    private final Game game;
     
    private final JButton newGameButton = new JButton( "New Game" );
    private final JLabel  gameDurationLabel = new JLabel( "  Game duration in seconds:" );
    private final JTextField gameDurationTextField = new JTextField();
     
    private final Timer animationTimer;
    private long gameStartTime;
     
    ControlPanel( View view, Game game ) 
    {
        this.view = view;
        this.game = game;
      
        setLayout( new GridLayout( 1, 3 ) );
        add( newGameButton );
        add( gameDurationLabel );
        add( gameDurationTextField );
 
        animationTimer = new Timer( PAUSE, this );
        gameDurationTextField.setEditable( false );
        initialize();
    }
 
    private void initialize() 
    {
        newGameButton.addActionListener( new ActionListener()
	    {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) 
		{
		    newGameButtonActionPerformed( actionEvent );
		}
	    });
         
        // register this as the listener for mouse events in the View JPanel
        view.addMouseListener( this );
    }
 
    private void newGameButtonActionPerformed( ActionEvent actionEvent ) 
    {
        gameDurationTextField.setText(""); //blank TextField
        gameStartTime = System.currentTimeMillis(); //start time
        animationTimer.restart();
        game.start(); //begin new game
    }
    /**
     * Implementation of ActionListener of Timer
     * @param e unused 
     */
    @Override
    public void actionPerformed( ActionEvent e) 
    {
        game.draw();    // blanks their previous positions and moves them
        view.paintComponent(view.getGraphics()); // repaints image
        if (game.getIsOver()) // check if game is over
	    {
		animationTimer.stop();
		double playTime = (System.currentTimeMillis() - gameStartTime) / 1000.000; //in secs
		DecimalFormat df = new DecimalFormat("##0.000");                                                                        // TRY IT
		gameDurationTextField.setText(df.format(playTime));//output time in secs with 3 sig figs
	    }
         
    }
 
    // implementation of MouseListener
    @Override
    public void mouseClicked( MouseEvent event ) 
    {
        if(game.getIsOver()) //if over just return
	    {
		return;
	    }
        animationTimer.stop();
        game.processClick(event.getX(), event.getY());
        animationTimer.start();
        view.paintComponent(view.getGraphics());                                                                                // WHY NEEDED??
    }
 
    @Override
    public void mousePressed(MouseEvent e) {}
 
    @Override
    public void mouseReleased(MouseEvent e) {}
 
    @Override
    public void mouseEntered(MouseEvent e) {}
     
    @Override
    public void mouseExited(MouseEvent e) {}
}
