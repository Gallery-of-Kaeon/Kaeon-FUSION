package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Adapt extends FUSIONUnit {
	
	public Adapt() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Adapt");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		PhilosophersStoneUtilities.call(
				this,
				"Adapt",
				((State) processed.get(0)).toArrayList(),
				"" + processed.get(1));
		
		return null;
	}
}