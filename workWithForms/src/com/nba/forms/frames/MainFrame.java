package com.nba.forms.frames;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.nba.forms.listners.InvertButtonMouseListner;
import com.nba.forms.listners.OpenFileMouseListner;
import com.nba.forms.listners.SmoothButtonMouseListner;
import com.nba.forms.listners.SubmitFileListner;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() {
		super();
	}

	public MainFrame(String name) {
		super(name);
	}

	public void create() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(200, 150);
		Container pane = getContentPane();
		createContent(pane);
		pack();
		setVisible(true);
	}

	public void createContent(Container pane) {	 
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    // overall
	    c.fill = GridBagConstraints.HORIZONTAL;
	    	    
	    //00
	    JLabel filePath = new JLabel("Image path: ");
	    c.insets = new Insets(10, 10, 5, 0);
	    c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
	    pane.add(filePath, c);
	    
	    //10
	    JLabel filePathText = new JLabel();
	    c.gridx = 1;
	    c.gridy = 0;
	    c.ipadx = 0;
	    c.gridwidth = 2;
	    pane.add(filePathText, c);
	    
	    //30
	    JButton fileChooser = new JButton("Open file...");
	    fileChooser.addMouseListener(new OpenFileMouseListner(this));
	    c.gridx = 3;
	    c.gridy = 0;
	    c.ipadx = 50;
	    c.gridwidth = 1;
	    pane.add(fileChooser, c);
	    
	    //40
	    JButton submitFileButton = new JButton("Submit");
	    submitFileButton.addMouseListener(new SubmitFileListner(this));
	    c.insets = new Insets(10, 10, 5, 10);
	    c.gridx = 4;
	    c.gridy = 0;
	    c.ipadx = 50;
	    pane.add(submitFileButton, c);
	    
		//01
		JLabel pictureBeforeText = new JLabel("Picture before: ");
		c.insets = new Insets(0, 5, 5, 0);
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		c.gridwidth = 3;	
		c.ipadx = 0;
		pane.add(pictureBeforeText, c);
		
		//31
		JLabel pictureAfterText = new JLabel("Picture after: ");
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 3;
		pane.add(pictureAfterText, c);

		//02
		JLabel pictureBefore = new JLabel();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		pane.add(pictureBefore, c);
		
		//32
		JLabel pictureAfter = new JLabel();
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 3;
		pane.add(pictureAfter, c);

		//03
		JButton invertButton = new JButton("Invert");
		invertButton.addMouseListener(new InvertButtonMouseListner(this));
		invertButton.setEnabled(false);
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 20;
		c.gridwidth = 1;
		c.insets = new Insets(20, 10, 20, 0);		
		pane.add(invertButton, c);
		
		//13
		JButton smoothButton = new JButton("Smooth");
		smoothButton.addMouseListener(new SmoothButtonMouseListner(this));
		smoothButton.setEnabled(false);
		c.gridx = 1;
		c.gridy = 3;
		c.ipadx = 20;
		c.gridwidth = 1;
		c.insets = new Insets(20, 10, 20, 0);		
		pane.add(smoothButton, c);

	}

}
