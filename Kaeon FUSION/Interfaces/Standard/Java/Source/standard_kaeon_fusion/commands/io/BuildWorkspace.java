package standard_kaeon_fusion.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class BuildWorkspace extends FUSIONUnit {
	
	public BuildWorkspace() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Build Workspace");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		try {
			return "" + PhilosophersStoneUtilities.call(this, "Get Build Workspace").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		return "";
	}
}