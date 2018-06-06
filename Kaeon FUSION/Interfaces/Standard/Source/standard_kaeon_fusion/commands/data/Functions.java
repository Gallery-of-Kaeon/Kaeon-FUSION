package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Functions extends FUSIONUnit {

	public State state;
	
	public Functions() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Functions");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		ArrayList<Alias> aliases = state.getByType("FUNCTION");
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i = 0; i < aliases.size(); i++)
			names.add(aliases.get(i).alias);
		
		return names;
	}
}