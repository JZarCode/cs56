/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.swing.JOptionPane;

/**
 *
 * @author joelzarley
 */

public class BoundedIntegerOptionPane extends IntegerOptionPane{
    
    private final int upperBound;
    private final int lowerBound;

    public BoundedIntegerOptionPane(String prompt, int lower, int upper) 
    {
        super(prompt);
        lowerBound = lower;
        upperBound = upper;
    }
    
    
   
    public int getIntvalue()
    {
	int ret = super.getIntvalue();
	String bounds = ("Please enter a number between "+lowerBound+" and "+upperBound+"!");
	while(ret < lowerBound || ret > upperBound)
	    {
		JOptionPane.showMessageDialog(null,bounds,"OUT OF BOUNDS",0);
		ret = super.getIntvalue();
	    }
	return ret;    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	BoundedIntegerOptionPane iop = new BoundedIntegerOptionPane("Please enter an interger: ", 0, 1000);
	System.out.println(iop.getIntvalue());
        
    }
    
}
