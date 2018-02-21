package standard_kaeon_fusion.commands.build;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Build extends FUSIONUnit {
	
	public State state;
	
	public Build() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Build");
	}
	
	public boolean trickleDown(Element element) {
		return true;
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