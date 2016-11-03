import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
 
 
public class Controller extends JPanel{
 
    Controller()
    {       
         
        setLayout(new GridLayout(5,3));
        JLabel Prnt = new JLabel("Printer:");
        Prnt.setHorizontalAlignment(JLabel.RIGHT);
        add(Prnt);
        add(new JTextField("   "));
        add(new JButton("Okay"));
         
        add(new JCheckBox("print to file"));
        add(new JRadioButton("Selection"));
        add(new JButton("Cancel"));
        add(new JCheckBox("2-sided"));      
        add(new JRadioButton("All"));
        add(new JButton("Setup"));
        JLabel prntQ = new JLabel("Printer Quality:");
        prntQ.setHorizontalAlignment(JLabel.RIGHT);
        add(prntQ);
        String combo[] = {" ","high", "low"};
        add(new JComboBox(combo));
        add(new JButton("Help"));
         
        JLabel console = new JLabel("Console:");
        add(console);
        add(new JTextArea(2,80));
        add(new JTextArea(2,80));
 
    }
}
