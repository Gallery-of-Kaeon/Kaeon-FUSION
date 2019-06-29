package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.list.ElementToList;
import standard_kaeon_fusion.utilities.state.State;

public class GetFunction extends FUSIONUnit {

	public State state;
	
	public GetFunction() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Get Function");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		ArrayList<Object> function =
				ElementToList.elementToList(
						((Element) state.getByAliasAndType("" + processed.get(0), "FUNCTION")));
		
		function.remove(0);
		
		return function;
	}
}