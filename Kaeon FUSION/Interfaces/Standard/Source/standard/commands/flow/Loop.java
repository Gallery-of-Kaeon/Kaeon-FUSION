package standard.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.state.State;

public class Loop extends FUSIONUnit {
	
	public State state;
	
	public Loop() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		return element.content.equalsIgnoreCase("Loop");
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		
		boolean condition = true;
		
		if(processed.size() > 0)
			condition = Boolean.parseBoolean("" + processed.get(0));
		
		if(condition) {
			
			state.pop();
			state.push();
			
			return element.parent.children.get(0);
		}
		
		return null;
	}
}