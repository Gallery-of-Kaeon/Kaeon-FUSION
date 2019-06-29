package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Call extends FUSIONUnit {
	
	public Call() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Call");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(processed.get(i) instanceof State)
				processed.set(i, ((State) processed.get(i)).toArrayList());
		}
		
		Object toReturn = PhilosophersStoneUtilities.call(this, processed);
		
		if(processed.size() > 0) {
			
			if(("" + processed.get(0)).equalsIgnoreCase("Generate")) {
				
				State state = new State();
				state.generateFromArrayList((ArrayList<Object>) toReturn);
				
				toReturn = state;
			}
		}
		
		return toReturn;
	}
}