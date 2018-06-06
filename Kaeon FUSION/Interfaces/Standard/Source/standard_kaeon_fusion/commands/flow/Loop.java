package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

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
		int nest = 0;
		
		for(int i = 0; i < processed.size(); i++) {
			
			try {
				nest = Integer.parseInt("" + processed.get(i)) - 1;
			}
			
			catch(Exception exception) {
				
				if(("" + processed.get(i)).equalsIgnoreCase("false") ||
						("" + processed.get(i)).equalsIgnoreCase("true")) {
					
					condition = Boolean.parseBoolean("" + processed.get(i));
				}
			}
		}
		
		Element current = element;
		
		int popState = 0;
		
		for(int i = 0; i < nest && current.parent.parent != null; i++) {
			
			current = current.parent;
			
			popState++;
		}
		
		if(condition) {
			
			for(int i = 0; i < popState; i++)
				state.pop();
			
			state.pop();
			state.push();
			
			return current.parent.children.get(0);
		}
		
		return null;
	}
}