package com.nba.forms.listners;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OpenFileMouseListner implements MouseListener {
	
	private JFrame frame;

	public OpenFileMouseListner(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JFileChooser fc = new JFileChooser();
		Container pane = frame.getContentPane();
		int result = fc.showOpenDialog(pane);
		if(result == JFileChooser.APPROVE_OPTION) {
			JLabel tf = (JLabel) pane.getComponent(1);
			File file = fc.getSelectedFile();
			tf.setText(file.getAbsolutePath());
			fc.cancelSelection();
			frame.pack();
		}
		if(result == JFileChooser.CANCEL_OPTION) {
			fc.cancelSelection();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
