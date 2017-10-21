package standard.commands.core;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.state.Alias;
import standard.utilities.state.State;

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
	
	public double getPriority(Element element) {
		return -1;
	}
	
	public boolean trickleDown(Element element) {
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(Element child : element.children) {
			
			try {

				String alias = child.content;
				
				Element function = ElementUtilities.copyElement(child);
				function.content = null;
				
				Alias functionAlias = new Alias();
				
				functionAlias.type = "FUNCTION";
				functionAlias.alias = alias;
				functionAlias.object = function;
				
				state.setGlobalAlias(functionAlias);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		return null;
	}
}