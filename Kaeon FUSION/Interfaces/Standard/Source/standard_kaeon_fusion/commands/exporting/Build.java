package standard_kaeon_fusion.commands.exporting;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Build extends FUSIONUnit {
	
	public Build() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Build");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		PhilosophersStoneUtilities.call(
				this,
				"Build",
				element.children.get(0).content,
				processed.get(1),
				processed.size() == 3 ? processed.get(2) : new ArrayList<Object>());
		
		return null;
	}
}