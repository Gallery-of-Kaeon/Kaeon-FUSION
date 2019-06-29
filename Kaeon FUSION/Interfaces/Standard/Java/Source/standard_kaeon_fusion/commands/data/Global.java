package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Global extends FUSIONUnit {
	
	public State state;
	
	public Global() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Global");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		for(Element child : element.children) {
			
			Alias alias = new Alias();
			
			alias.alias = child.content;
			alias.object = state.getByAlias(child.content);
			
			state.setGlobalAlias(alias);
		}
		
		return null;
	}
}