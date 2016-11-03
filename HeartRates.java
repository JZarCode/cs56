/**
 *
 * @author Joel Zarley & Kevin Hitzler
 */
import java.util.Calendar;

import javax.swing.JOptionPane;

public class HeartRates
{
    private String firstName;
    private String lastName;
    private int month;
    private int day;
    private int year;
      

    public HeartRates() {
	firstName = JOptionPane.showInputDialog("What is your First name? ");
	lastName = JOptionPane.showInputDialog("What is your Last name? ");
	year = getInt("What Year [Example: 1994] where you born in? ");
	month = getInt("What Month [1-12] were you born in? ") - 1;
	day = getInt("What Day [1-31] were you born on? ");
            
	System.out.printf("%s%s your birthday is %d/%d/%d\n", firstName,
			  lastName, month+1,day,year);
	System.out.printf( "%s%s your maximum heart rate should be: %d\n" 
			   ,firstName, lastName, getMaxRate());
	System.out.printf( "%s%s your target heart rate interval is: %s\n" 
			   ,firstName, lastName, getTargetInterval());
    }

    public String getFirstName(){
	return firstName;
    }

    public String getLastName(){
	return lastName;
    }

    public int getMonth(){
	return month;
    }

    public int getDay(){
	return day;
    }

    public int getYear(){
	return year;
    }

    public void setFirstName( String firstName){
	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public void setMonth(int month) {
	this.month = month;
    }

    public void setDay(int day) {
	this.day = day;
    }

    public void setYear(int year) {
	this.year = year;
    }
       
    public int getAge(){
	int age;
	Calendar currentTime = Calendar.getInstance();
	age = currentTime.get(Calendar.YEAR) - year;
	if(month < currentTime.get(Calendar.MONTH))
	    { 
		return age;
	    }
	if(month == currentTime.get(Calendar.MONTH) && day > currentTime.get(Calendar.DATE));
	{
	    age--;
	}
	return age;
    }

    private int getInt(String prompt) {
	String intS = JOptionPane.showInputDialog(prompt);
	return Integer.parseInt(intS);
    }
        
    private int getMaxRate()
    {
	return 220 - getAge();
    }
        
    private String getTargetInterval()
    {
	double min = getMaxRate() * .50;
	double max = getMaxRate() * .85;
	return (min + " to " + max); 
    }
        
    public static void main ( String[] args )
    {
        HeartRates Healthy = new HeartRates();
        
    }
}
