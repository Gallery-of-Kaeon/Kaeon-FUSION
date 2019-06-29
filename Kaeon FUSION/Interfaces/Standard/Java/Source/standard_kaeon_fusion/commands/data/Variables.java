package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Variables extends FUSIONUnit {

	public State state;
	
	public Variables() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Variables");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		ArrayList<Alias> aliases = new ArrayList<Alias>();
		
		if(processed.size() > 1) {
			
			if(Boolean.parseBoolean("" + processed.get(1)) == false)
				aliases = state.getByType("ALIAS", true, false);
			
			else
				aliases = state.getByType("ALIAS", false, true);
		}
		
		else
			aliases = state.getByType("ALIAS");
		
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i = 0; i < aliases.size(); i++)
			names.add(aliases.get(i).alias);
		
		return names;
	}
}