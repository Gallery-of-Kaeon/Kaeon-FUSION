package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class In extends FUSIONUnit {
	
	public State state;
	
	public In() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("In");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		for(int i = 0; i < processed.size(); i++) {
			
			State inState = (State) processed.get(i);
			
			ArrayList<Alias> functions = inState.getByType("FUNCTION");
			
			inState.state = new ArrayList<ArrayList<Alias>>();
			inState.inStates = new ArrayList<ArrayList<State>>();
			
			for(int j = 0; j < 2; j++) {
				inState.inStates.add(new ArrayList<State>());
				inState.state.add(new ArrayList<Alias>());
			}
			
			inState.state.get(inState.state.size() - 1).addAll(functions);
			
			state.inStates.get(state.inStates.size() - 2).add(inState);
		}
		
		return null;
	}
}