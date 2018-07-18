package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class IsFunction extends FUSIONUnit {

	public State state;
	
	public IsFunction() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Is Function");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		return state.hasAliasAndType("" + processed.get(0), "FUNCTION");
	}
}