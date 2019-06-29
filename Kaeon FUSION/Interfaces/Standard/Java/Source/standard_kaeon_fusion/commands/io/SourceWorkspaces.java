package standard_kaeon_fusion.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class SourceWorkspaces extends FUSIONUnit {
	
	public SourceWorkspaces() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Source Workspaces");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> workspace = new ArrayList<Object>();
		
		workspace.add("");
		
		ArrayList<Object> workspaces = PhilosophersStoneUtilities.call(this, "Get Workspace");
		
		for(int i = 0; i < workspaces.size(); i++) {
			
			try {
				workspace.addAll((ArrayList<String>) workspaces.get(i));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		return workspace;
	}
}