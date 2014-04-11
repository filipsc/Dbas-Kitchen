
/**
 * @author Filip
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KitchenReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteJDBC kitchendb = new SQLiteJDBC("kitchen");
	}
	
	public class Frame1 extends JFrame
	{
		private static final long serialVersionUID = 1L;
	JPanel pane = new JPanel();
	  Frame1() // the frame constructor method
	  {
	    super("My Simple Frame"); setBounds(100,100,300,100);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container con = this.getContentPane(); // inherit main frame
	    con.add(pane); // add the panel to frame
	    // customize panel here
	    // pane.add(someWidget);
	    setVisible(true); // display this frame
	  }
	}

}
