package stack.utilities.gui;

import java.awt.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.awt.print.*;

public class Printer implements Printable {
	
	private BufferedImage image;
	
	public Printer() {
		
	}
	
	public static void print(BufferedImage image) {
		new Printer(image).printOut();
	}
	
	private Printer(BufferedImage image) {
		this.image = image;
	}
	
	private void printOut() {
		
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		
		if (printJob.printDialog())
			
			try {
				printJob.print();
			}
			
			catch (PrinterException exception) {
				
				JOptionPane.showMessageDialog(null,
						"Error in printing !!! " + exception);
			}
	}
	
	public int print(Graphics g, PageFormat pf, int pageIndex) {
		
		if (pageIndex > 0)
			return (NO_SUCH_PAGE);
		
		else {
			
			double pageWidth = pf.getImageableWidth();
			double pageHeight = pf.getImageableHeight();
			
			double imageWidth = image.getWidth();
			double imageHeight = image.getHeight();
			
			double scaleX = pageWidth / imageWidth;
			double scaleY = pageHeight / imageHeight;
			
			double scaleFactor = Math.min(scaleX, scaleY);
			
			PrinterImage printerImage = new PrinterImage(
					image, image.getWidth(), image.getHeight());
			
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.scale(scaleFactor, scaleFactor);
			
			printerImage.paint(g2d);
			
			return (PAGE_EXISTS);
		}
	}
}