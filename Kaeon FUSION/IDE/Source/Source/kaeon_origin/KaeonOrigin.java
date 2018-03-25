package kaeon_origin;

import javax.swing.JOptionPane;

import aether_kaeon_fusion.Aether;
import io.IO;
import kaeon_origin.ide.IDE;
import one.Element;
import one_plus.ONEPlus;

public class KaeonOrigin {
	
	public static void main(String[] args) {
		
		IDE.initializeLookAndFeel();
		
		update();
		
		Element originData = null;
		
		String data = IO.openAsString("Origin.op");
		
		if(data == null) {
			
			originData = new Element();
			
			IO.save("", "Origin.op");
		}
		
		else
			originData = ONEPlus.parseONEPlus(data);
		
		if(args.length == 0) {
			
			new IDE(originData);
			
			return;
		}
		
		// STUB
	}
	
	public static void update() {
		
		String updatePath =
				"https://raw.githubusercontent.com/" +
				"Gallery-of-Kaeon/Kaeon-FUSION/" +
				"master/" +
				"Kaeon%20FUSION/IDE/Application/Update/Update.op";
		
		Object object = Aether.call("Updater", 0, updatePath);
		
		if(object == null)
			return;
		
		if((Boolean) object) {
			
			int response = JOptionPane.showConfirmDialog(
					null,
					"An update is available for Kaeon Origin.\nWould you like to install it?",
					"Kaeon Origin",
					JOptionPane.YES_NO_OPTION);
			
			if(response == JOptionPane.YES_OPTION) {
				
				try {
					
					Runtime.getRuntime().exec(
							"java -jar " +
							"\"Updater.jar\" " +
							"\"" + updatePath + "\" " +
							"command \"java -jar \"\"Kaeon Origin.jar\"\"\"");
					
					System.exit(0);
				}
				
				catch(Exception exception) {
					
				}
			}
		}
	}
}