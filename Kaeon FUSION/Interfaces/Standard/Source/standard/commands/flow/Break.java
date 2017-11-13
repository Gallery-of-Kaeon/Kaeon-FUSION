package standard.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.state.State;

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
			
			Element current = element;
			
			while(current.parent.parent != null) {
				
				state.pop();
				
				int parentIndex = ElementUtilities.getIndex(current.parent);
				
				if(parentIndex == current.parent.parent.children.size() - 1) {
					
					current = current.parent;
					
					continue;
				}
				
				return element.parent.parent.children.get(parentIndex + 1);
			}
		}
		
		return null;
	}
	
	public int changeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		
		boolean condition = true;
		
		if(processed.size() > 0)
			condition = Boolean.parseBoolean("" + processed.get(0));
		
		if(condition) {
			
			Element current = element;
			int depth = currentDepth;
			
			while(current.parent.parent != null) {
				
				depth--;
				
				int parentIndex = ElementUtilities.getIndex(current.parent);
				
				if(parentIndex == current.parent.parent.children.size() - 1) {
					
					current = current.parent;
					
					continue;
				}
				
				return depth;
			}
			
			return -1;
		}
		
		return currentDepth;
	}
}