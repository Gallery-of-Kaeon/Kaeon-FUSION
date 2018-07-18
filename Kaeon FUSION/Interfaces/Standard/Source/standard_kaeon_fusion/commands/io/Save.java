package standard_kaeon_fusion.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import io.IO;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Save extends FUSIONUnit {
	
	public Save() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Save");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		String workspace = "";
		
		try {
			workspace = "" + PhilosophersStoneUtilities.call(this, "Get Build Workspace").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		IO.save("" + processed.get(1), workspace + processed.get(0));
		
		return null;
	}
}