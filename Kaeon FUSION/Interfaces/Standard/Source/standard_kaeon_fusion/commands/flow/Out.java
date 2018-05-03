package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Out extends FUSIONUnit {
	
	public State state;
	
	public Out() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Out");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		for(int i = 0; i < processed.size(); i++) {
			
			ArrayList<State> inStates = state.inStates.get(state.inStates.size() - 1);
			
			for(int j = 0; j < inStates.size(); j++) {
				
				if(inStates.get(j) == processed.get(i)) {
					
					inStates.remove(j);
					
					break;
				}
			}
		}
		
		return null;
	}
}