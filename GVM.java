 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
 
/**
 *
 * @author Joel Zarley, Kevin Hitzler
 */
 
public class GVM 
{
     
    // symbolic program constants go here
    // DEFINES
    private final int STOP = 0;
    private final int SET = 1;
    private final int LOAD = 2;
    private final int STORE = 3;
    private final int ADD = 4;
    private final int ZERO = 5;
    private final int GOTO = 6;
    private final int SETCOLOR = 7;
    private final int DRAWLINE = 8;
    private final int DRAWRECT = 9;
    private final int FILLRECT = 10;
    private final int DRAWOVAL = 11;
    private final int FILLOVAL = 12;
     
    // dataMemory special variables
    private final int ACCUMULATOR = 0;
    private final int X = 1;
    private final int Y = 2;
    private final int WIDTH = 3;
    private final int HEIGHT = 4;
    private final int RED = 5;
    private final int GREEN = 6;
    private final int BLUE = 7;
     
    // optional dataMemory (for extra 40%)
    private final int COUNTER_COLUMN = 8;
    private final int COUNTER_ROW = 9;
    private final int ONE = 10;
     
    // attribute type declarations go here
    private Graphics graphics;
    public static final int IMAGE_SIZE = View.IMAGE_SIZE;
    private final int[] dataMemory = new int[100];
    private int[][] programMemory;
    private int instructionAddress = 0;
    private final Image image;  
     
     
    GVM() 
    {         
        image = new BufferedImage( IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB );
        graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);
        graphics.setColor(Color.black);
    }
     
    Image getImage() 
    { 
        return image;
    }
     
    void load()
    {
        // The basic testing program is given below. Replace it, if you want to go for extra credit. See below.
        // Note: I defined symbolic program constants (not shown in listing) 
        // that enable this somewhat more readable version to compile.
         
        programMemory = new int[][]
        {
            { SET, 20 },  // ACC <- 10                           IMPORTANT! programMemory[0][0] = SET; programMemory[0][1] = 20;
            { STORE, X },   // STORE ACC -> X
            { STORE, Y },   // STORE ACC -> Y
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT }, // STORE ACC -> HEIGHT
            { DRAWRECT }, // DRAWRECT
            { SET, 255 },  // ACC <- 255
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to red
            { LOAD, X }, // ACC <- X
            { ADD, X }, // ACC += X
            { STORE, X }, // ACC -> X
            { STORE, Y }, // ACC -> Y
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT }, // STORE ACC -> HEIGHT
            { DRAWOVAL }, // FILLRECT
            { LOAD, X }, // ACC <- X
            { ADD, X }, // ACC += X
            { STORE, X }, // ACC -> X
            { STORE, Y }, // ACC -> Y
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT }, // STORE ACC -> HEIGHT
            { FILLOVAL }, // FILLRECT
            { LOAD, X }, // ACC <- X
            { ADD, X }, // ACC += X
            { STORE, X }, // ACC -> X
            { STORE, Y }, // ACC -> Y
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT }, // STORE ACC -> HEIGHT
            { FILLRECT }, // FILLRECT
            { LOAD, X }, // ACC <- X
            { ADD, X }, // ACC += X
            { STORE, X }, // ACC -> X
            { STORE, Y }, // ACC -> Y
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT }, // STORE ACC -> HEIGHT
            { DRAWLINE }, // FILLRECT
            { STOP  }       // STOP     (37)    
        };
    }
     
    void load2()
    {
        // The basic testing program is given below. Replace it, if you want to go for extra credit. See below.
        // Note: I defined symbolic program constants (not shown in listing) 
        // that enable this somewhat more readable version to compile.
         
        programMemory = new int[][]
        {
            // **************************** START FIRST PART (*****************************
            // *****************************************************************************
            //------------------- Initialization of dataMemory -----------------------------
            { SET, -9  },  // -8 -> ACC 
            { STORE, COUNTER_ROW }, // ACC -> COUNTER_ROW
             
            { SET, 0 },  // ACC <- 0
            { STORE, Y }, // STORE ACC -> Y#
             
             
            //------------------------- LOOP 2 BEGINS ---------------------------------------
            { SET, -8 },  // -8 -> ACC 
            { STORE, COUNTER_COLUMN }, // ACC -> COUNTER_COLUMN
             
            { SET, 1 },  // ACC <- 1 
            { STORE, ONE }, // ACC -> ONE            
             
            { SET, 0 },  // ACC <- 0
            { STORE, X },   // STORE ACC -> X
             
            { SET, 40 },
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT },                                                                  // line  12
            //--------------------End of Initialization -------------------------------------
             
             
            //------------------------- FIRST ROW -------------------------------------------
            // set parameters for FIRST filloval (which may not be in the loop
            // because the last one has also to be an oval not a rect) and do X= X+40
            { SET, 255 },  // ACC <- 255
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to red
            { FILLOVAL }, // FILLOVAL
             
            //X = X + 40
            { LOAD, X },  // ACC <- X
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
             
            //------------------------ LOOP 1 BEGINS --------------------------------------- 
            // set parameters for fillrect
            { SET, 0 },                                                                         // line 20
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to black (0,0,0)
            { FILLRECT }, // FILLRECT
             
            //X = X + WIDTH
            { LOAD, X },  // ACC <- X
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
            // set parameters for filloval
            { SET, 255 },  // ACC <- 255
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to red
            { FILLOVAL }, // FILLOVAL                                                           // line 30
             
            //X = X + WIDTH
            { LOAD, X },  // ACC <- X                                                            
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
            { LOAD, COUNTER_COLUMN }, // ACC -> 8 as a variable in the dataMemory
            { ADD, ONE }, // ACC -> 8 as a variable in the dataMemory
            { STORE, COUNTER_COLUMN }, // ACC -> 8 as a variable in the dataMemory
             
            { ZERO, 20 }, // FIRST LOOP OF FIRST PART - CMP ACC to 0. IF NOT 0 GOTO 21
             
            //---------------------- FIRST ROW AND LOOP END -------------------------------
             
            //Y = Y + HEIGHT
            { LOAD, Y },  // ACC <- Y
            { ADD, HEIGHT },  // ACC <- ACC+WIDTH
            { ADD, HEIGHT },  // ACC <- ACC+WIDTH                                                // line 40
            { STORE, Y },   // STORE ACC -> 
             
            { LOAD, COUNTER_ROW }, // ACC -> 8 as a variable in the dataMemory
            { ADD, ONE }, // ACC -> 8 as a variable in the dataMemory
            { STORE, COUNTER_ROW }, // ACC -> 8 as a variable in the dataMemory
             
            { ZERO, 4 }, // SECOND LOOP OF FIRST PART - CMP ACC to 0. IF NOT 0 GOTO 21
             
            //--------------------- END OF ODD ROWS AND 2nd LOOP --------------------------
             
            // *****************************************************************************
            // **************************** END FIRST PART (********************************
             
             
            // **************************** START SECOND PART (*****************************
            // *****************************************************************************
             
             
            //------------------- 2nd Initialization of dataMemory -------------------------
            { SET, -8  },  // -8 -> ACC                                                      // line 46
            { STORE, COUNTER_ROW }, // ACC -> COUNTER_ROW
             
            { SET, 40 },
            { STORE, HEIGHT },
             
            { LOAD, HEIGHT },  // ACC <- HEIGHT
            { STORE, Y }, // STORE ACC -> Y#
             
             
            //------------------------- LOOP 2 BEGINS --------------------------------------
            { SET, -8 },  // -8 -> ACC                                                           // line 52
            { STORE, COUNTER_COLUMN }, // ACC -> COUNTER_COLUMN
             
            { SET, 1 },  // ACC <- 1 
            { STORE, ONE }, // ACC -> ONE            
             
            { SET, 0 },  // ACC <- 0
            { STORE, X },   // STORE ACC -> X
             
            { SET, 40 },
            { STORE, WIDTH }, // STORE ACC -> WIDTH
            { STORE, HEIGHT },
            //--------------------End of 2nd Initialization --------------------------------
             
 
            //------------------------- FIRST ROW -------------------------------------------
            // set parameters for FIRST fillrect (which may not be in the loop
            // because the last one has also to be an rect not a oval) and do X= X+40
            { SET, 0 },     
            { STORE, RED }, // ACC -> RED                                                        // line 62
            { SETCOLOR }, // SETCOLOR to black (0,0,0)
            { FILLRECT }, // FILLRECT
             
            //X = X + 40
            { LOAD, X },  // ACC <- X
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
            //------------------------ LOOP 1 BEGINS --------------------------------------- 
            // set parameters for filloval
            { SET, 255 },  // ACC <- 255                                                     // line 68
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to red
            { FILLOVAL }, // FILLOVAL
             
            //X = X + WIDTH
            { LOAD, X },  // ACC <- X
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
            // set parameters for fillrect
            { SET, 0 },     
            { STORE, RED }, // ACC -> RED
            { SETCOLOR }, // SETCOLOR to black (0,0,0)
            { FILLRECT }, // FILLRECT
             
            //X = X + WIDTH
            { LOAD, X },  // ACC <- X
            { ADD, WIDTH },  // ACC <- ACC+WIDTH
            { STORE, X },   // STORE ACC -> X
             
            { LOAD, COUNTER_COLUMN }, // ACC -> 8 as a variable in the dataMemory
            { ADD, ONE }, // ACC -> 8 as a variable in the dataMemory
            { STORE, COUNTER_COLUMN }, // ACC -> 8 as a variable in the dataMemory
             
            { ZERO, 68 }, // FIRST LOOP IN SECOND PART - CMP ACC to 0. IF NOT 0 GOTO 66
             
            //---------------------- FIRST ROW AND LOOP END -------------------------------
             
            //Y = Y + HEIGHT
            { LOAD, Y },  // ACC <- Y
            { ADD, HEIGHT },  // ACC <- ACC+WIDTH
            { ADD, HEIGHT },  // ACC <- ACC+WIDTH
            { STORE, Y },   // STORE ACC -> 
             
            { LOAD, COUNTER_ROW }, // ACC -> 8 as a variable in the dataMemory
            { ADD, ONE }, // ACC -> 8 as a variable in the dataMemory
            { STORE, COUNTER_ROW }, // ACC -> 8 as a variable in the dataMemory
             
            { ZERO, 52 }, // SECOND LOOP IN SECOND PART - CMP ACC to 0. IF NOT 0 GOTO 50
             
            //--------------------- END OF ODD ROWS AND 2nd LOOP -------------------------- 
             
            // *****************************************************************************
            // **************************** END SECOND PART (*******************************
     
            { STOP }, // TERMINATE PROGRAM  
        };
    }
         
    void step()
    { 
        if(programMemory[instructionAddress][0] != STOP)            // programMemory[instructionAddress][0] == CHECK STOP
	    {
		executeInstruction(programMemory[instructionAddress]);   // programMemory[instructionAddress] == INSTRUCTION ITSELF!
	    }
         
    }
     
    void run()
    {       
        while(programMemory[instructionAddress][0] != STOP)         // programMemory[instructionAddress][0] == CHECK STOP
	    {
		executeInstruction(programMemory[instructionAddress]);   // programMemory[instructionAddress] == INSTRUCTION ITSELF!
	    }
    }
     
    private void executeInstruction( int[] instructionArray )
    {
        int instruction = instructionArray[0];
         
        switch(instruction){
         
	case 0:     // stop:  The stop instruction (opcode 0) means 
	    // stop executing instructions; the program has terminated.
	    break;
      
	case 1:     // set: Store the instruction operand in the accumulator; 
	    // increment the instruction address.    
	    dataMemory[ACCUMULATOR] = programMemory[instructionAddress][1];
	    instructionAddress++;
	    break;
                 
	case 2:     // load: Load in the accumulator the data memory cell 
	    // whose index is the instruction operand; increment the instruction address.
	    dataMemory[ACCUMULATOR] = dataMemory[programMemory[instructionAddress][1]];
	    instructionAddress++;
	    break;
                 
	case 3:     // store: Store the accumulator in the data memory cell whose 
	    // index is the instruction operand; increment the instruction address.
	    dataMemory[programMemory[instructionAddress][1]] = dataMemory[ACCUMULATOR];
	    instructionAddress++;
	    break;
                 
	case 4:     // add: Add to the accumulator the contents of the data memory 
	    // cell whose index is the instruction operand; increment the instruction address.
	    dataMemory[ACCUMULATOR] = dataMemory[ACCUMULATOR] + dataMemory[programMemory[instructionAddress][1]];
	    instructionAddress++;
	    break;       
                 
	case 5:     // zero: If the accumulator is not equal to 0, set the instruction address to 
	    // the current instruction's operand; otherwise, increment the instruction address.
	    if (dataMemory[ACCUMULATOR] != 0)
		instructionAddress = programMemory[instructionAddress][1];
	    else
		instructionAddress++;
	    break;
                 
	case 6:     // goto: Set the instruction address to the current instruction's operand.
	    instructionAddress = programMemory[instructionAddress][1];
	    break;
                 
	case 7:     // setColor: Set the current color to the Java Color 
	    // object contructed (int RGB constructor) from the red, 
	    // green, & blue data memory cell values; increment the instruction address.
	    graphics.setColor(new Color(dataMemory[5],dataMemory[6],dataMemory[7]));
	    instructionAddress++;
	    break;
                 
	case 8:     // drawLine: Draw a line of the current color, using data memory 
	    // values x & y for the starting point, & x + width, y + height as the end point; 
	    // increment the instruction address.
	    graphics.drawLine(dataMemory[1], dataMemory[2], dataMemory[1]+dataMemory[3], dataMemory[2]+dataMemory[4]);
	    instructionAddress++;
	    break;
                 
	case 9:     // drawRect: Draw a rectangle with the current color, using 
	    // data memory values x & y for the lower left coordinate, the 
	    // data memory value of width for its width, & the data memory height 
	    // value for its height; increment the instruction address.
	    graphics.drawRect(dataMemory[1], dataMemory[2], dataMemory[3], dataMemory[4]);
	    instructionAddress++;
	    break;
                 
	case 10:    // fillRect: Fill a rectangle with the current color, using data memory values 
	    // x & y for the lower left coordinate, the data memory value of width for its 
	    // width, & the data memory height value for its height; increment the instruction address.
	    graphics.fillRect(dataMemory[1], dataMemory[2], dataMemory[3], dataMemory[4]);
	    instructionAddress++;
	    break;
                 
	case 11:    // drawOval: Draw an oval with the current color, using data memory values x & y for 
	    // the lower left coordinate, the data memory value of width for its width, & the data 
	    // memory height value for its height; increment the instruction address.
	    graphics.drawOval(dataMemory[1], dataMemory[2], dataMemory[3], dataMemory[4]);
	    instructionAddress++;
	    break;
                 
	case 12:    // fillOval: Fill an oval with the current color, using data memory values x & y 
	    // for the lower left coordinate, the data memory value of width for its width, & 
	    // the data memory height value for its height; increment the instruction address.
	    graphics.fillOval(dataMemory[1], dataMemory[2], dataMemory[3], dataMemory[4]);
	    instructionAddress++;
	    break;    
        }
    }
}
