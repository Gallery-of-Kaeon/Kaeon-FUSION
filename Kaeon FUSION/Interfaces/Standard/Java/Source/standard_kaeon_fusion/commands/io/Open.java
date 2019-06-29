package standard_kaeon_fusion.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import io.IO;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Open extends FUSIONUnit {
	
	public Open() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Open");
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
		
		for(int i = 0; i < workspace.size(); i++) {
			
			String open = IO.openAsString(workspace.get(i) + processed.get(0));
			
			if(open != null)
				return open;
		}
		
		return null;
	}
}