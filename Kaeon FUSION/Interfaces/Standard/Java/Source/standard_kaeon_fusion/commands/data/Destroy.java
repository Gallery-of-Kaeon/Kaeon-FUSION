package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Destroy extends FUSIONUnit {

	public State state;
	
	public Destroy() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Destroy");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		if(processed.size() > 1) {
			
			if(Boolean.parseBoolean("" + processed.get(1)) == false)
				state.removeAlias("" + processed.get(0), FUSIONUtilities.isIsolated(element), true, false);
			
			else
				state.removeAlias("" + processed.get(0), FUSIONUtilities.isIsolated(element), false, true);
		}
		
		else
			state.removeAlias("" + processed.get(0));
		
		return null;
	}
}