package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Break extends Command {
	
	private boolean broke;
	
	public Break() {
		
		super();
		
		tag("Break");
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Break");
	}
	
	public Element onChangeElement(Element element, ArrayList<Object> processed) {
		
		broke = processed.size() == 0 ? true : Boolean.parseBoolean("" + processed.get(0));
		
		if(broke) {
			
			Element current = element;
			
			while(current.getParent() != null) {
				
				current = current.getParent();
				
				int position = getElementPosition(current);
				
				if(position == -1)
					return null;
				
				if(position < current.getParent().getNumElements() - 1)
					return current.getParent().getElement(position + 1);
			}
		}
		
		return null;
	}
	
	public int onChangeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		
		boolean change = processed.size() == 0 ? true : Boolean.parseBoolean("" + processed.get(0));
		
		if(change) {
			
			Element current = element;
			int newDepth = currentDepth;
			
			while(current.getParent() != null) {
				
				current = current.getParent();
				newDepth--;
				
				int position = getElementPosition(current);
				
				if(position == -1)
					return 0;
				
				if(position < current.getParent().getNumElements() - 1)
					return newDepth;
			}

			return currentDepth;
		}
		
		return currentDepth;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		if(parent != null) {
			
			for(int i = 0; i < parent.getNumElements(); i++) {
				
				if(parent.getElement(i) == element)
					return i;
			}
		}
		
		return -1;
	}
	
	public boolean broke() {
		
		boolean temp = broke;
		broke = false;
		
		return temp;
	}
}