package kaeon_origin;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import aether_kaeon_fusion.Aether;
import io.IO;
import kaeon_fusion.KaeonFUSION;
import kaeon_origin.ide.IDE;
import kaeon_origin.utilties.Utilities;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;

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
		
		if(ElementUtilities.hasChild(originData, "reset")) {
			
			ElementUtilities.removeChild(originData, "Source");
			ElementUtilities.removeChild(originData, "Arguments");
			ElementUtilities.removeChild(originData, "Prompt");
			ElementUtilities.removeChild(originData, "reset");
			
			IO.save("" + originData, "Origin.op");
		}
		
		if(args.length == 0) {
			
			if(ElementUtilities.hasChild(originData, "Source")) {
				
				try {
					
					String arguments = null;
					
					if(ElementUtilities.hasChild(originData, "Arguments"))
						arguments = ElementUtilities.getChild(originData, "Source").children.get(0).content;
					
					else if(ElementUtilities.hasChild(originData, "Prompt"))
						arguments = JOptionPane.showInputDialog("Enter the program arguments:");
					
					KaeonFUSION fusion = new KaeonFUSION();
					
					PhilosophersStoneUtilities.publiclyConnect(
							fusion,
							Utilities.getArgumentStone(IDE.getArguments(arguments)));
					
					fusion.process(ONEPlus.parseONEPlus(
							IO.openAsString(
									ElementUtilities.getChild(
											originData,
											"Source").children.get(0).content)));
				}
				
				catch(Exception exception) {
					
				}
			}
			
			else
				new IDE(originData);
			
//			new IDE(originData);
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("-reset")) {
			
			ElementUtilities.removeChild(originData, "Source");
			ElementUtilities.removeChild(originData, "Arguments");
			ElementUtilities.removeChild(originData, "Prompt");
			
			IO.save("" + originData, "Origin.op");
		}
		
		ArrayList<Object> arguments = new ArrayList<Object>();
		
		for(int i = 1; i < args.length; i++)
			arguments.add(args[i]);
		
		KaeonFUSION fusion = new KaeonFUSION();
		PhilosophersStoneUtilities.publiclyConnect(fusion, Utilities.getArgumentStone(arguments));
		
		try {
			fusion.process(ONEPlus.parseONEPlus(IO.openAsString(args[0])));
		}
		
		catch(Exception exception) {
			
		}
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