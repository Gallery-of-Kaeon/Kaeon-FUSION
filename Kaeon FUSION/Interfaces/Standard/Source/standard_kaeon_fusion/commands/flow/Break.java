package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Break extends FUSIONUnit {
	
	public State state;
	public boolean broke;
	
	public Break() {
		tags.add("Standard");
		tags.add("Break");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Break");
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		if(element.parent.parent == null)
			return null;
		
		boolean condition = true;
		
		if(processed.size() > 0)
			condition = Boolean.parseBoolean("" + processed.get(0));
		
		broke = condition;
		
		if(condition) {
			
			Element current = element.parent;
			
			while(current.parent != null) {
				
				state.pop();
				
				int parentIndex = ElementUtilities.getIndex(current);
				
				if(parentIndex == current.parent.children.size() - 1) {
					
					current = current.parent;
					
					continue;
				}
				
				return current.parent.children.get(parentIndex + 1);
			}
		}
		
		return null;
	}
	
	public boolean terminate(Element element, ArrayList<Object> processed, int currentDepth) {
		
		boolean condition = true;
		
		if(processed.size() > 0)
			condition = Boolean.parseBoolean("" + processed.get(0));
		
		if(condition) {
			
			Element current = element.parent;
			
			while(current.parent != null) {
				
				int parentIndex = ElementUtilities.getIndex(current);
				
				if(parentIndex == current.parent.children.size() - 1) {
					
					current = current.parent;
					
					continue;
				}
				
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}