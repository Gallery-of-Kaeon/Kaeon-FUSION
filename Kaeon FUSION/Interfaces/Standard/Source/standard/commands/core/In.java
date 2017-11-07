package standard.commands.core;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.FUSIONUtilities;
import standard.utilities.state.Alias;
import standard.utilities.state.State;

public class In extends FUSIONUnit {
	
	public FUSION fusion;
	public State state;
	
	Element marked;
	
	public In() {
		tags.add("Standard");
	}
	
	public boolean deny(Element element) {
		
		if(element == marked)
			marked = null;
		
		if(marked != null)
			return element.parent == marked;
		
		return false;
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("In");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		marked = element.parent;
		
		FUSION functionFUSION = FUSIONUtilities.copy(fusion);
		
		ArrayList<PhilosophersStone> atlas = PhilosophersStoneUtilities.getAtlas(functionFUSION);
		
		for(PhilosophersStone stone : atlas) {
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State") ||
					PhilosophersStoneUtilities.isTagged(stone, "Arguments")) {
				
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
			}
		}
		
		State newState = new State();
		
		newState.global.addAll(state.global);
		newState.global.addAll(((State) processed.get(0)).global);
		
		for(ArrayList<Alias> aliases : state.state)
			newState.state.add(new ArrayList<Alias>(aliases));
		
		for(ArrayList<Alias> aliases : ((State) processed.get(0)).state)
			newState.state.add(aliases);
		
		newState.state.add(((State) processed.get(0)).global);
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		Element code = ElementUtilities.copyElement(element.parent);
		int index = ElementUtilities.getIndex(element);
		
		for(int i = 0; i <= index; i++)
			code.children.remove(0);
		
		functionFUSION.process(code);
		
		Object toReturn = null;
		
		try {
			toReturn = ((Return) PhilosophersStoneUtilities.get(functionFUSION, "Return").get(0)).toReturn;
		}
		
		catch(Exception exception) {
			
		}
		
		PhilosophersStoneUtilities.destroy(functionFUSION);
		
		return toReturn;
	}
}