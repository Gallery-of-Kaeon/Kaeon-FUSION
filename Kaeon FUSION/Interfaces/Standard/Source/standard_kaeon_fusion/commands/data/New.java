package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class New extends FUSIONUnit {
	
	public FUSION fusion;
	public State state;
	
	public New() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(element.parent != null) {
			
			if(element.parent.content != null) {
				
				if(element.parent.content.equalsIgnoreCase("New"))
					return true;
			}
		}
		
		return element.content.equalsIgnoreCase("New");
	}
	
	public boolean trickleDown(Element element) {
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(!element.content.equalsIgnoreCase("New"))
			return processed;
		
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
		
		for(int i = 0; i < state.inStates.size(); i++) {
			
			for(int j = 0; j < state.inStates.get(i).size(); j++)
				newState.global.addAll(state.inStates.get(i).get(j).global);
		}
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		@SuppressWarnings({ "unchecked" })
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? list : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
		Element function = (Element) state.getByAliasAndType(element.children.get(0).content, "FUNCTION");
		
		function = ElementUtilities.copyElement(function);
		function.content = "";
		
		functionFUSION.process(function);
		
		State toReturn = (State) PhilosophersStoneUtilities.get(functionFUSION, "State").get(0);
		
		PhilosophersStoneUtilities.destroy(functionFUSION);
		
		return toReturn;
	}
}