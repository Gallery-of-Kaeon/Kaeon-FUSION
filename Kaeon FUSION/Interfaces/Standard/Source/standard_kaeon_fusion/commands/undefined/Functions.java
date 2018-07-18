package standard_kaeon_fusion.commands.undefined;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.flow.Catch;
import standard_kaeon_fusion.commands.flow.FUSIONException;
import standard_kaeon_fusion.commands.flow.Return;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.Priority;
import standard_kaeon_fusion.utilities.Stopper;
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
					!(command instanceof Priority) &&
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
		
		for(int i = 0; i < state.inStates.size(); i++) {
			
			for(int j = 0; j < state.inStates.get(i).size(); j++)
				newState.global.addAll(state.inStates.get(i).get(j).global);
		}
		
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
		function.content = "";
		
		((Stopper) PhilosophersStoneUtilities.get(functionFUSION, "Stopper").get(0)).fusion.add(functionFUSION);
		
		functionFUSION.internalProcess(function, false);
		
		Object toReturn = null;
		
		try {
			toReturn = ((Return) PhilosophersStoneUtilities.get(functionFUSION, "Return").get(0)).toReturn;
		}
		
		catch(Exception exception) {
			
		}
		
		Catch localCatch = (Catch) PhilosophersStoneUtilities.get(this, "Catch").get(0);
		Catch functionCatch = (Catch) PhilosophersStoneUtilities.get(functionFUSION, "Catch").get(0);
		
		FUSIONException localException = (FUSIONException) PhilosophersStoneUtilities.get(this, "Exception").get(0);
		FUSIONException functionException = (FUSIONException) PhilosophersStoneUtilities.get(functionFUSION, "Exception").get(0);
		
		if(!localCatch.caught)
			localCatch.caught = functionCatch.caught;
		
		localException.exception = functionException.exception;
		
		PhilosophersStoneUtilities.destroy(functionFUSION);
		
		return toReturn;
	}
}