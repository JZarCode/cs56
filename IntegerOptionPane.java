/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
/* @author Joel Zarley, Kevin Hitzler
 * @ input
 * @ returns
 * @throws
 */

import javax.swing.JOptionPane;

public class IntegerOptionPane 
{
    private String prompt;

    public IntegerOptionPane(String prompt)
    {
	this.prompt = prompt;
    }

    public int getIntvalue()
    {
	int ret;
	String intS = JOptionPane.showInputDialog(prompt);
	try
	    {
		ret = Integer.parseInt(intS);
	    }
	catch (NumberFormatException e)
	    {
		JOptionPane.showMessageDialog(null, "NumberFormatException occured. The number entered was not an integer.", "NumberFormatException", JOptionPane.ERROR_MESSAGE);
		ret = getIntvalue();
	    }

	return ret;
    }

    public static void main(String[] args) 
    {
	IntegerOptionPane iop = new IntegerOptionPane("Please enter an interger to quit. ");
	System.out.println(iop.getIntvalue());
    }
}
