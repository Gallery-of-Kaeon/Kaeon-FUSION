package standard_kaeon_fusion.commands.build;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Derive extends FUSIONUnit {
	
	public State state;
	public ArrayList<Element> dialects;
	
	public Derive() {
		
		tags.add("Standard");
		tags.add("Derive");
		
		dialects = new ArrayList<Element>();
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Derive");
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
				"Derive",
				element.children.get(0).content,
				processed.get(0),
				arguments);
		
		return null;
	}
}