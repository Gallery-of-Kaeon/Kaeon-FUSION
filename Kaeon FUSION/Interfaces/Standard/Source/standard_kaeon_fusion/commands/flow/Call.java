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
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(processed.get(i) instanceof State)
				processed.set(i, ((State) processed.get(i)).toArrayList());
		}
		
		PhilosophersStoneUtilities.call(this, processed);
		
		return null;
	}
}