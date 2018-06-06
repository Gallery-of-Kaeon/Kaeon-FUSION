package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Define extends FUSIONUnit {
	
	public State state;
	
	public Define() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		try {
			
			if(state == null)
				state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		return element.content.equalsIgnoreCase("Define");
	}
	
	public boolean trickleDown(Element element) {
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(Element child : element.children) {
			
			if(child.content.equalsIgnoreCase("Meta"))
				continue;
			
			try {

				String alias = child.content;
				
				Element function = child;
				
				Alias functionAlias = new Alias();
				
				functionAlias.type = "FUNCTION";
				functionAlias.alias = alias;
				functionAlias.object = function;
				
				state.setGlobalAlias(functionAlias, FUSIONUtilities.isIsolated(element));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		return null;
	}
}