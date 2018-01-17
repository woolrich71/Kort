package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KortRam extends JFrame 
{


	public KortRam()
	{
	}

	public void initComponents() throws Exception
	{
		// the following code sets the frame's initial state
		setSize(new java.awt.Dimension(650, 800));
		getContentPane().setLayout(new java.awt.BorderLayout(0, 0));
		setTitle("KortRam");
		setLocation(new java.awt.Point(0, 0));


		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				thisWindowClosing(e);
			}
		});
	}
  
  	private boolean mShown = false;
  	
	public void addNotify() 
	{
		super.addNotify();
		
		if (mShown)
			return;
			
		JMenuBar jMenuBar = getJMenuBar();
		if (jMenuBar != null) {
			int jMenuBarHeight = jMenuBar.getPreferredSize().height;
			Dimension dimension = getSize();
			dimension.height += jMenuBarHeight;
			setSize(dimension);
		}

		mShown = true;
	}

	// Close the window when the close box is clicked
	void thisWindowClosing(java.awt.event.WindowEvent e)
	{
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
}
