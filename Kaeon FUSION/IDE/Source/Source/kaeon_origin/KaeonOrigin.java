package kaeon_origin;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import io.IO;
import kaeon_fusion.KaeonFUSION;
import kaeon_origin.ide.IDE;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class KaeonOrigin {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			
			String manifest = IO.openAsString("Manifest.op");
			
			if(manifest == null)
				new IDE();
			
			else {
				
				try {
					
					try {
						
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					    	
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					}
					
					catch (Exception exception) {
						
					}
					
					Element manifestElement = one_plus.ONEPlus.parseONEPlus(manifest);
					
					Element code = ONEPlus.parseONEPlus(
							IO.openAsString(manifestElement.children.get(0).content));
					
					KaeonFUSION fusion = new KaeonFUSION();
					
					if(manifestElement.children.size() == 1) {
						
						ArrayList<Object> arguments =
								IDE.getArguments(
										manifestElement.children.get(1).children.get(0).content);
						
						PhilosophersStone argumentStone = IDE.getArgumentStone(arguments);
						PhilosophersStoneUtilities.publiclyConnect(fusion, argumentStone);
						
						fusion.process(code);
					}
					
					else if(manifestElement.children.get(1).children.size() == 0) {
						
						ArrayList<Object> arguments =
								IDE.getArguments(
										JOptionPane.showInputDialog("Enter the program arguments:"));
						
						PhilosophersStone argumentStone = IDE.getArgumentStone(arguments);
						PhilosophersStoneUtilities.publiclyConnect(fusion, argumentStone);
						
						fusion.process(code);
					}
					
					else
						fusion.process(code);
				}
				
				catch(Exception exception) {
					
				}
			}
		}

		else {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			try {
				
				ArrayList<Object> arguments = new ArrayList<Object>();
				
				for(int i = 1; i < args.length; i++)
					arguments.add(args[i]);
				
				PhilosophersStone argumentStone = IDE.getArgumentStone(arguments);
				PhilosophersStoneUtilities.publiclyConnect(fusion, argumentStone);
				
				fusion.process(ONEPlus.parseONEPlus(IO.openAsString(args[0])));
			}
			
			catch(Exception exception) {
				
			}
		}
	}
}