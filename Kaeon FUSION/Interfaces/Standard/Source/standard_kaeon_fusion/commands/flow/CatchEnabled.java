package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class CatchEnabled extends FUSIONUnit {
	
	public CatchEnabled() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Catch Enabled");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return ((Catch) PhilosophersStoneUtilities.get(this, "Catch").get(0)).auto;
	}
}