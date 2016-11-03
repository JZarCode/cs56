/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author joelzarley
 */
public class IntegerSet {

    private static int IntRange = 101;
    private boolean[] boolArray = new boolean[IntRange];
    
    
    public static IntegerSet union(IntegerSet setA, IntegerSet setB)
    {
        IntegerSet union = new IntegerSet();
        for(int i = 0; i < IntRange; i++)
	    {
		union.boolArray[i] = setA.boolArray[i] || setB.boolArray[i];
	    }
        return union;
    }
    
    public static IntegerSet intersection(IntegerSet setA, IntegerSet setB)
    {
        IntegerSet intersect = new IntegerSet();
        int i = 0;
        while(i < IntRange)
	    {
		intersect.boolArray[i] = setA.boolArray[i] && setB.boolArray[i];
		i++;
	    };
        
        return intersect;
    }
    
    public void insertElement( int newElement )
    {
        boolArray[newElement] = true;
    }
    
    public void deleteElement( int delElement )
    {
        boolArray[delElement] = false;
    }
    
    public String toString()
    {
        // prof showed this in method in class
        StringBuilder string = new StringBuilder();
        // set empty set
        boolean isEmpty = true;
        string.append( "{ ");
        int i=0;
        while( i < IntRange )
	    {
		if( boolArray[i])
		    {
			isEmpty = false;
			string.append(i).append(' ');
		    }
		i++;
	    };
        return isEmpty?"---":string.append('}').toString();
    }
            
    public boolean isEqual(IntegerSet setA)
    {
        for(int i=0; i<IntRange; i++)
	    {
		if(boolArray[i] != setA.boolArray[i])
		    {
			return false;
		    }
	    }
        return true;    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IntegerSet intSetA = new IntegerSet();
        IntegerSet intSetB = new IntegerSet();
        System.out.println("A and B are empty => A:"+intSetA+" B: "+intSetB);
        IntegerSet intSetALL = new IntegerSet();
        for( int i=0; i<IntRange; i++)
	    {
		intSetALL.boolArray[i] = true;
	    }
        IntegerSet intSetU = new IntegerSet();
        IntegerSet intSetX = new IntegerSet();
        intSetA.insertElement(2);
        intSetA.insertElement(4);
        intSetA.insertElement(6);
        System.out.println("Inserted 2: in A ");
        System.out.println("Inserted 4: in A ");
        System.out.println("Inserted 6: in A ");
        System.out.println("setA: "+intSetA);
        System.out.print("testing union of A and ALL and insertection of A and ALL: \n");
        intSetU = union(intSetA, intSetALL);
        intSetX = intersection(intSetA, intSetALL);
        System.out.println("Union: "+intSetU+"\n"+"Intersect: "+intSetX);
        intSetA.deleteElement(2);
        System.out.println("Deleted 2 from setA");
        System.out.println("setA: "+intSetA);
        intSetB.insertElement(4);
        intSetB.insertElement(6);
        System.out.println("Inserted 4: in B");
        System.out.println("Inserted 6: in B");
        System.out.println("SetA = "+intSetA+ "\nSetB = "+intSetB);
        System.out.println("SetA and SetB isEqual => "+intSetA.isEqual(intSetB));
          System.out.println("Thanks for watching.. GOODBYE!");
        
        
        
   
    }
    
    
}
