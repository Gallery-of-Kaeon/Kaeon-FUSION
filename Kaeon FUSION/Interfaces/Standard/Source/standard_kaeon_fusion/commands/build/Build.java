package standard_kaeon_fusion.commands.build;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Build extends FUSIONUnit {
	
	public State state;
	public ArrayList<Element> dialects;
	
	public Build() {
		
		tags.add("Standard");
		tags.add("Build");
		
		dialects = new ArrayList<Element>();
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Build");
	}
	
	public boolean trickleDown(Element element) {
		
		dialects.add(element.children.get(0));
		
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> arguments = new ArrayList<Object>(processed);
		arguments.remove(0);
		
		PhilosophersStoneUtilities.call(
				this,
				"Build",
				element.children.get(0).content,
				processed.get(0),
				arguments);
		
		return null;
	}
}