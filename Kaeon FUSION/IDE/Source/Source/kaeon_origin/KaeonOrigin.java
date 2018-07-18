package kaeon_origin;

import java.io.File;
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
import philosophers_stone.PhilosophersStone;
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
		
		if(args.length == 0) {
			
			new IDE(originData);
			
			return;
		}
		
		String flag = "";
		
		String file = null;
		String arguments = "";
		
		for(int i = 0; i < args.length; i++) {
			
			if(args[i].startsWith("-") && args[i].length() == 2) {
				
				flag = args[i].toLowerCase();
				
				continue;
			}
			
			if(flag.equals("-r") && file == null)
				file = args[i];
			
			if(flag.equals("-a")) {
				
				if(arguments.length() > 0)
					arguments += " ";
				
				arguments += args[i];
			}
		}
		
		if(file != null) {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			PhilosophersStoneUtilities.publiclyConnect(
					fusion,
					Utilities.getArgumentStone(
							IDE.getArguments(
									arguments)));
			
			ArrayList<String> source = new ArrayList<String>();
			source.add("");
			
			String build = "";
			
			try {
				
				String buildWorkspace =
						ElementUtilities.getChild(
								ElementUtilities.getChild(
										ElementUtilities.getChild(
												originData,
												"Kaeon Origin"),
										"Workspace"),
								"Build").children.get(0).content;
				
				if(buildWorkspace.length() > 0)
					build = buildWorkspace;
				
				Element sourceWorkspace =
						ElementUtilities.getChild(
								ElementUtilities.getChild(
										ElementUtilities.getChild(
												originData,
												"Kaeon Origin"),
										"Workspace"),
								"Source");
				
				for(int i = 0; i < sourceWorkspace.children.size(); i++)
					source.add(sourceWorkspace.children.get(i).content);
				
				buildWorkspace =
						ElementUtilities.getChild(
							ElementUtilities.getChild(
									ElementUtilities.getChild(
											ElementUtilities.getChild(
													originData,
													"Perspectives"),
											"Kaeon FUSION"),
									"Workspace"),
							"Build").children.get(0).content;
				
				if(buildWorkspace.length() > 0)
					build = buildWorkspace;
				
				sourceWorkspace =
						ElementUtilities.getChild(
								ElementUtilities.getChild(
										ElementUtilities.getChild(
												ElementUtilities.getChild(
														originData,
														"Perspectives"),
												"Kaeon FUSION"),
										"Workspace"),
								"Source");
				
				for(int i = 0; i < sourceWorkspace.children.size(); i++)
					source.add(sourceWorkspace.children.get(i).content);
			}
			
			catch(Exception exception) {
				
			}
			
			String finalBuild = build;
			
			PhilosophersStone workspace = new PhilosophersStone() {

				public Object onCall(ArrayList<Object> packet) {

					if(((String) packet.get(0)).equalsIgnoreCase("Get Workspace")) {
						return source;
					}

					if(((String) packet.get(0)).equalsIgnoreCase("Get Build Workspace")) {
						return finalBuild;
					}
					
					return null;
				}
			};

			workspace.tags.add("Workspace");

			PhilosophersStoneUtilities.publiclyConnect(fusion, workspace);
			
			String code = "";
			
			for(int i = 0; i < source.size(); i++) {
				
				code = IO.openAsString(source.get(i) + file);
				
				if(code != null)
					break;
			}
			
			fusion.processKaeonFUSION(ONEPlus.parseONEPlus(code));
		}
	}
	
	public static void update() {
		
		if(!(new File("Origin.op").exists()))
			IO.save("", "Origin.op");
		
		Element origin = ONEPlus.parseONEPlus(IO.openAsString("Origin.op"));
		
		String updatePath = null;
		
		try {
			
			updatePath =
					ElementUtilities.getChild(
							origin,
							"Update Path").children.get(0).content;
		}
		
		catch(Exception exception) {
			return;
		}
		
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