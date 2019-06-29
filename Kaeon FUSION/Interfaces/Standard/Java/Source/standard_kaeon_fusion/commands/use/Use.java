package standard_kaeon_fusion.commands.use;

import java.io.File;
import java.util.ArrayList;

import aether_standard.Aether;
import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Use extends FUSIONUnit {
	
	public FUSION fusion;
	
	public Use() {
		tags.add("Kaeon FUSION");
		tags.add("Use");
	}
	
	public boolean verify(Element element) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		return element.content.equalsIgnoreCase("Use");
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
		
		for(int i = 0; i < processed.size(); i++) {
			
			for(int j = 0; j < workspace.size(); j++) {
				
				String path = "" + processed.get(i);
				
				if(path.toLowerCase().endsWith(".jar"))
					path = path.substring(0, path.length() - 4);
				
				File file = new File(workspace.get(j) + path + ".jar");
				
				if(file.exists()) {
					
					Aether.call(workspace.get(j) + path, 0, fusion);
					PhilosophersStoneUtilities.call(fusion, "Update");
					
					break;
				}
			}
		}
		
		return null;
	}
}