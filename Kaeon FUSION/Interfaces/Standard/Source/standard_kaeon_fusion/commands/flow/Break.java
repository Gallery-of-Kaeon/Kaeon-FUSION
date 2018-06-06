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
		
		boolean condition = true;
		int nest = 0;
		
		for(int i = 0; i < processed.size(); i++) {
			
			try {
				nest = Integer.parseInt("" + processed.get(i)) - 1;
			}
			
			catch(Exception exception) {
				
				if(("" + processed.get(i)).equalsIgnoreCase("false") ||
						("" + processed.get(i)).equalsIgnoreCase("true")) {
					
					condition = Boolean.parseBoolean("" + processed.get(i));
				}
			}
		}
		
		Element current = element;
		
		if(current.parent.parent == null)
			return null;
		
		int popState = 0;
		
		for(int i = 0; i < nest; i++) {
			
			current = current.parent;
			
			if(current.parent.parent == null)
				return null;
			
			popState++;
		}
		
		broke = condition;
		
		if(condition) {
			
			for(int i = 0; i < popState; i++)
				state.pop();
			
			current = current.parent;
			
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
	
	public boolean terminate(Element element, ArrayList<Object> processed) {
		
		boolean condition = true;
		int nest = 0;
		
		for(int i = 0; i < processed.size(); i++) {
			
			try {
				nest = Integer.parseInt("" + processed.get(i)) - 1;
			}
			
			catch(Exception exception) {
				
				if(("" + processed.get(i)).equalsIgnoreCase("false") ||
						("" + processed.get(i)).equalsIgnoreCase("true")) {
					
					condition = Boolean.parseBoolean("" + processed.get(i));
				}
			}
		}
		
		Element current = element;
		
		for(int i = 0; i < nest; i++) {
			
			current = current.parent;
			
			if(current.parent.parent == null)
				return true;
		}
		
		if(condition) {
			
			current = element.parent;
			
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