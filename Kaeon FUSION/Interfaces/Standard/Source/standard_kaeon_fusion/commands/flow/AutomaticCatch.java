package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class AutomaticCatch extends FUSIONUnit {
	
	public AutomaticCatch() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Automatic Catch");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		((Catch) PhilosophersStoneUtilities.get(this, "Catch").get(0)).auto =
				Boolean.parseBoolean("" + processed.get(0));
		
		return null;
	}
}