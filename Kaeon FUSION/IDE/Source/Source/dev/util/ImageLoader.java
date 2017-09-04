package dev.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	static ImageLoader rl = new ImageLoader();
	
	public static Image getImage(String fileName){
		return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(fileName));
	}
	
	public static BufferedImage getBufferedImage(String fileName) {
		
		BufferedImage bimage = null;
		
		try {
			bimage = ImageIO.read(ImageLoader.class.getResourceAsStream(fileName));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return bimage;
	}
}