package standard_kaeon_fusion.commands.undefined;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Variable extends FUSIONUnit {
	
	public FUSION fusion;
	public State state;
	
	public Variable() {
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
					!(command instanceof Literal)) {
				
				if(command.verify(element))
					return false;
			}
		}
		
		if(element.children.size() == 0)
			return state.hasAlias(element.content);
		
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.size() > 0) {
			
			Alias alias = new Alias();
			
			alias.alias = element.content;
			alias.object = processed.get(0);
			
			state.setAlias(alias);
		}
		
		return state.getByAlias(element.content);
	}
}