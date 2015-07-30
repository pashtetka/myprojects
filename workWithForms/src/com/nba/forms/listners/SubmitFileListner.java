package com.nba.forms.listners;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SubmitFileListner implements MouseListener {
	
	private JFrame frame;
	
	public SubmitFileListner(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Container pane = frame.getContentPane();
		JLabel tf = (JLabel) pane.getComponent(1);
		if(tf.getText().endsWith(".jpg") || tf.getText().endsWith(".JPG")) {		
			JLabel label = (JLabel) pane.getComponent(6);
			ImageIcon ii = new ImageIcon(tf.getText());
			label.setIcon(ii);
			JButton invertButton = (JButton) pane.getComponent(8);
			JButton smoothButton = (JButton) pane.getComponent(9);
			invertButton.setEnabled(true);
			smoothButton.setEnabled(true);
			frame.pack();
		} else {
			JLabel label = (JLabel) pane.getComponent(10);
			label.setText("File isn't JPG file!");
			frame.pack();
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
