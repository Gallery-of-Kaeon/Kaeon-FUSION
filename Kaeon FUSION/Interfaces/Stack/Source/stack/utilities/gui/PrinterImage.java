package stack.utilities.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PrinterImage extends JPanel {
	
	BufferedImage image;
	
	public PrinterImage() {
		
	}
	
	public PrinterImage(BufferedImage image, int width, int height) {
		
		this.image = image;
		
		setSize(width, height);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}