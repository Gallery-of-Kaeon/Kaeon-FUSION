package kaeon_fusion.use;

import java.io.File;
import java.util.ArrayList;

import aether_kaeon_fusion.Aether;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class Use extends FUSIONUnit {
	
	public Use() {
		tags.add("Kaeon FUSION");
		tags.add("Use");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Use");
	}
	
	public double getPriority(Element element) {
		return Double.NEGATIVE_INFINITY;
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> workspace = new ArrayList<String>();
		
		workspace.add("");
		
		ArrayList<Object> workspaces = PhilosophersStoneUtilities.call(this, "Get Workspace");
		
		for(int i = 0; i < workspaces.size(); i++) {
			
			try {
				workspace.addAll((ArrayList<String>) workspaces.get(i));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		PhilosophersStone fusion = PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		for(int i = 0; i < processed.size(); i++) {
			
			for(int j = 0; j < workspace.size(); j++) {
				
				File file = new File(workspace.get(j) + processed.get(i) + ".jar");
				
				if(file.exists()) {
					
					Aether.call(workspace.get(j) + processed.get(i), 0, fusion);
					PhilosophersStoneUtilities.call(fusion, "Update");
					
					break;
				}
			}
		}
		
		return element.content;
	}
}