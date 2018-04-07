package standard_kaeon_fusion.commands.exporting;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Derive extends FUSIONUnit {
	
	public Derive() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Derive");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		PhilosophersStoneUtilities.call(
				this,
				"Derive",
				element.children.get(0).content,
				processed.get(1),
				processed.size() == 3 ? processed.get(2) : new ArrayList<Object>());
		
		return null;
	}
}