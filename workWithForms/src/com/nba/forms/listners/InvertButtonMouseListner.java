package com.nba.forms.listners;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.nba.image.modificators.ImageModificator;

public class InvertButtonMouseListner implements MouseListener {

	private JFrame frame;

	public InvertButtonMouseListner(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Container pane = frame.getContentPane();
		JButton invertButton = (JButton) pane.getComponent(8);
		if (invertButton.isEnabled()) {
			ImageModificator modificator = new ImageModificator();
			JLabel imagePath = (JLabel) pane.getComponent(1);
			Random random = new Random();
			int randomName = random.nextInt();
			modificator.invertImage(imagePath.getText(), String.valueOf(randomName));
			JLabel label = (JLabel) pane.getComponent(7);
			ImageIcon im = new ImageIcon(String.valueOf(randomName));
			label.setIcon(im);
			File file = new File(String.valueOf(randomName));
			file.delete();
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
