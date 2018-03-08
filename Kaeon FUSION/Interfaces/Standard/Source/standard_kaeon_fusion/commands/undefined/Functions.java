package standard_kaeon_fusion.commands.undefined;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.core.Return;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Functions extends FUSIONUnit {
	
	public FUSION fusion;
	public State state;
	
	public Functions() {
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
					!(command instanceof Literals) &&
					!(command instanceof Variables)) {
				
				if(command.verify(element))
					return false;
			}
		}
		
		return state.getByAliasAndType(element.content, "FUNCTION") != null;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
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
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? processed : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
		Element function = (Element) state.getByAliasAndType(element.content, "FUNCTION");
		
		function = ElementUtilities.copyElement(function);
		function.content = null;
		
		functionFUSION.process(function);
		
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