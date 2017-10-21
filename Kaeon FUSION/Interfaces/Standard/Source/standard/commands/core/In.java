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
	
	public In() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("In");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		FUSION functionFUSION = FUSIONUtilities.copy(fusion);
		
		for(int i = 0; i < functionFUSION.publicConnections.size(); i++) {
			
			PhilosophersStone stone = functionFUSION.publicConnections.get(i);
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State") ||
					PhilosophersStoneUtilities.isTagged(stone, "Arguments")) {
				
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
			}
		}
		
		for(int i = 0; i < functionFUSION.privateConnections.size(); i++) {
			
			PhilosophersStone stone = functionFUSION.privateConnections.get(i);
			
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
			newState.state.add(new ArrayList<Alias>(aliases));
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? processed : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
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
	
	public Element jump(Element element, ArrayList<Object> processed) {
		
		if(element.parent.parent == null)
			return null;
		
		Element current = element;
		
		while(current.parent.parent != null) {
			
			state.pop();
			
			int parentIndex = ElementUtilities.getIndex(current.parent);
			
			if(parentIndex < current.parent.parent.children.size())
				return current.parent.parent.children.get(parentIndex + 1);
			
			current = current.parent;
		}
		
		return null;
	}
	
	public int changeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		
		if(element.parent.parent == null)
			return -1;
		
		Element current = element;
		int depth = currentDepth;
		
		while(current.parent.parent != null) {
			
			depth--;
			
			int parentIndex = ElementUtilities.getIndex(current.parent);
			
			if(parentIndex < current.parent.parent.children.size())
				return depth;
			
			current = current.parent;
		}
		
		return -1;
	}
}