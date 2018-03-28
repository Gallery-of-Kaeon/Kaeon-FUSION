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
		return element.content.equalsIgnoreCase("New");
	}
	
	public boolean trickleDown(Element element) {
		return false;
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
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		FUSION clone = FUSIONUtilities.clone(fusion);
		
		Element copy = ElementUtilities.copyElement(element.children.get(0));
		copy.content = "List";
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<Object> list = (ArrayList) clone.process(copy);
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? list : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
		Element function = (Element) state.getByAliasAndType(element.children.get(0).content, "FUNCTION");
		
		function = ElementUtilities.copyElement(function);
		function.content = null;
		
		functionFUSION.process(function);
		
		State toReturn = (State) PhilosophersStoneUtilities.get(functionFUSION, "State").get(0);
		
		PhilosophersStoneUtilities.destroy(functionFUSION);
		
		return toReturn;
	}
}