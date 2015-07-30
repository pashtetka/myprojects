package com.nba.forms.listners;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.nba.image.modificators.ImageModificator;

public class SmoothButtonMouseListner implements MouseListener {
	
	private JFrame frame;
	
	public SmoothButtonMouseListner(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Container pane = frame.getContentPane();
		JButton smoothButton = (JButton) pane.getComponent(9);
		if (smoothButton.isEnabled()) {
			ImageModificator modificator = new ImageModificator();
			JLabel imagePath = (JLabel) pane.getComponent(1);
			modificator.smoothingImage(imagePath.getText(), "smooth.jpg");
			JLabel label = (JLabel) pane.getComponent(7);
			ImageIcon im = new ImageIcon("smooth.jpg");
			label.setIcon(im);
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
