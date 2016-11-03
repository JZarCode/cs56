import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
 
/**
 *
 * @author Joel Zarley, Kevin Hitzler
 */
public class Game
{
    public final static int IMAGE_SIZE = 600;
     
    private final Image image;
    private final Critter critterFactory;                               
     
    private List<Critter> critterList;
    private boolean isOver;
     
    private final Graphics graphics;
    private final int numCritters = 3;
         
    Game() 
    {         
        // create Image object
        image = new BufferedImage (IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB );
        graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);
        critterFactory = new RoundCritter().makeCritter(); //instantiate critterFactory 
    }
 
    public Image getImage()
    {
        return image;
    }
     
    public void start()
    {
        isOver = false; 
        critterList = new ArrayList<>();
        for (int i=0; i<numCritters; i++)
	    {
		critterList.add(critterFactory.makeCritter()); //make 3 critters
	    }
    }
         
    public void draw()
    {       
        graphics.setColor(Color.white); //blank screen to remove prev critter position
        graphics.fillRect(0,0, IMAGE_SIZE, IMAGE_SIZE );
        for (Critter getC : critterList) 
	    {
		//move then draw
		getC.move(); 
		getC.draw(graphics);
	    }
         
         
    }
         
    public void processClick( int x, int y )
    {        
	for(int i=0; i < critterList.size(); i++)
	    {    
		Critter j = critterList.get(i);
		//check if click is inside a critter
		if(x <= j.x + j.size && x >= j.x && y <= j.y + j.size && y >= j.y)
		    {
			critterList.remove(i);
		    }
		if(critterList.isEmpty()) //if no more critters end game
		    {
			isOver = true;
		    }
	    }
        
    }
     
    abstract private class Critter
    {
        Color Color;
        int x, y;
        int deltaX, deltaY;
        int size = 60;
         
        Critter makeCritter() 
        {
            int rnd = getRandom(1,3); //random critter 
             
            if(rnd == 1) //create square critter
		{
		    SquareCritter sqC = new SquareCritter();
		    return sqC;
		}
            else if(rnd == 2)//create round critter
		{
		    RoundCritter rC = new RoundCritter();
		    return rC;
		}
            else //create wow critter
		{
		    WowCritter wC = new WowCritter();
		    return wC;
		}
        }
         
        private Critter()
        {
            int minDelta = (int) Math.pow(600, .25); //min value of abs of delta
            int maxDelta = (int) Math.pow(600, .5); //max value of abs of delta
            int[] random = new int[7];  // 7 random values
            for(int i=0;i<3;i++)     // 3 for RGB
		{
		    random[i]=getRandom(0,255);
		}
            
            for(int j=3;j<5;j++)    // 2 for x and y values
		{
		    random[j]=getRandom(0,600);
		}
            
            for(int k=5;k<7;k++) // 2 for deltaX and deltaY values
		{
		    random[k]=getRandom(minDelta, maxDelta);
		}
            
            Color = new Color(random[0],random[1],random[2]); //set color with RGB
            x = random[3]; 
            y = random[4];
            deltaX = randomSign(random[5]);
            deltaY = randomSign(random[6]);
        }
         
         
        abstract void draw( Graphics graphics );
         
        void move()
        {
            x = x + deltaX; 
            y = y + deltaY;
            if(x > IMAGE_SIZE) //if x goes above bounds
		{
		    x = x%IMAGE_SIZE;
		}
            if(x < 0) //if x goes below bounds
		{
		    x = (x + IMAGE_SIZE)%IMAGE_SIZE; 
		}
            if (y > IMAGE_SIZE) //if y goes above bounds
		{
		    y = y%IMAGE_SIZE;
		}
            if(y < 0) //if y goes below bounds
		{
		    y =(y + IMAGE_SIZE)%IMAGE_SIZE;
		}
            
        }
         
    }
     
    // Subclass Critter with a square critter
    private class SquareCritter extends Critter
    {
        @Override
	void draw(Graphics graphics) 
        {
            graphics.setColor(super.Color);
            graphics.fillRect(x, y, size, size);
        }
    }
     
    // Subclass Critter with a round critter
    private class RoundCritter extends Critter
    {
        @Override
	void draw(Graphics graphics) 
        {
            graphics.setColor(super.Color);
            graphics.fillOval(x, y, size, size);
        }
    }
     
    // Subclass Critter with a round critter whose color is randomly chosen each time it is drawn
    private class WowCritter extends Critter
    {
        @Override
        void draw(Graphics graphics) 
        {
            graphics.setColor(new Color(getRandom(0,255),getRandom(0,255),getRandom(0,255)));
            graphics.fillOval(x, y, size, size);
        }
    }
     
    private int getRandom(int start, int end) //random generator 
    {
        int rnd = (int) (Math.random()*end+start);
        return rnd;
    }
    
    private int randomSign(int random) //random positve or negative generator
    {
        int rnd = random;
        int j = getRandom(1,2);
        if(j == 1)
	    {
		rnd = -1 * rnd; //make negative
	    }
        return rnd;
    }
 
    public boolean getIsOver() 
    {
        return isOver;
    }
 
    public void setIsOver(boolean isOver) 
    {
        this.isOver = isOver;
    }
     
}
