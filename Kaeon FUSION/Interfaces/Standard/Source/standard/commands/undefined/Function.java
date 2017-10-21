package standard.commands.undefined;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.commands.core.Return;
import standard.utilities.FUSIONUtilities;
import standard.utilities.state.State;

public class Function extends FUSIONUnit {
	
	public FUSION fusion;
	public State state;
	
	public Function() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		for(FUSIONUnit command : fusion.fusionUnits) {
			
			if(command != this &&
					!(command instanceof State) &&
					!(command instanceof Literal) &&
					!(command instanceof Variable)) {
				
				if(command.verify(element))
					return false;
			}
		}
		
		return state.getByAliasAndType(element.content, "FUNCTION") != null;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
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
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? processed : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
		functionFUSION.process((Element) state.getByAliasAndType(element.content, "FUNCTION"));
		
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