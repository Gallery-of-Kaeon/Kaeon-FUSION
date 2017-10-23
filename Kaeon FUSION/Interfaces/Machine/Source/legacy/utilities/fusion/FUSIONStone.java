package legacy.utilities.fusion;

import java.util.ArrayList;

import legacy.utilities.one_plus.element.Element;
import legacy.utilities.philosophers_stone_plus.PhilosophersStonePlus;

public class FUSIONStone extends PhilosophersStonePlus {
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(packet.size() == 1) {
			
			if(packet.get(0) instanceof Element) {
				
				try {
					return onVerify((Element) packet.get(0)) ? this : null;
				}
				
				catch(Exception e) {
					
				}
			}
		}
		
		return null;
	}
	
	public Object process(Element element) {
		return process(element, 0);
	}
	
	public Object process(Element element, int depth) {
		
		int index = getElementIndex(element);
		
		ArrayList<ArrayList<Object>> subProcessed = new ArrayList<ArrayList<Object>>();
		
		for(int i = 0; i < depth + 1; i++)
			subProcessed.add(new ArrayList<Object>());
		
		boolean bubbleUp = false;
		
		while(true) {
			
			FUSIONStone fusion = getModule(element);
			
			if(!bubbleUp) {
				
				boolean trickleDown = true;
				
				try {
					trickleDown = fusion.onTrickleDown(element);
				}
				
				catch(Exception e) {
					
				}
				
				if(trickleDown && element.getNumElements() > 0) {
					
					element = element.getElement(0);
					subProcessed.add(new ArrayList<Object>());
					
					index = 0;
					depth++;
					
					continue;
				}
			}
			
			Object processed = null;
			
			try {
				
				processed = fusion.onProcess(
						element,
						element.getNumElements() > 0 ?
								subProcessed.get(subProcessed.size() - 1) : new ArrayList<Object>());
			}
			
			catch(Exception e) {
				
			}
			
			int newDepth = depth;
			
			try {
				
				newDepth = fusion.onChangeDepth(
						element,
						element.getNumElements() > 0 ?
								subProcessed.get(subProcessed.size() - 1) : new ArrayList<Object>(),
						depth);
			}
			
			catch(Exception e) {
				
			}
			
			Element newElement = null;
			
			try {
				
				newElement = fusion.onChangeElement(
						element,
						element.getNumElements() > 0 ?
								subProcessed.get(subProcessed.size() - 1) : new ArrayList<Object>());
			}
			
			catch(Exception e) {
				
			}
			
			if(bubbleUp) {
				subProcessed.remove(subProcessed.size() - 1);
				bubbleUp = false;
			}
			
			subProcessed.get(subProcessed.size() - 1).add(processed);
			
			depth = newDepth;
			
			if(newElement != null) {
				
				element = newElement;
				
				subProcessed = new ArrayList<ArrayList<Object>>();
				
				for(int i = 0; i < depth + 1; i++)
					subProcessed.add(new ArrayList<Object>());
				
				continue;
			}
			
			if(depth == 0 || element.getParent() == null)
				return processed;
			
			if(index < getNumSiblings(element) - 1) {
				element = element.getParent().getElement(index + 1);
				index++;
			}
			
			else {
				
				element = element.getParent();
				
				depth--;
				index = getElementIndex(element);
				
				bubbleUp = true;
			}
		}
	}
	
	private int getNumSiblings(Element element) {
		return element.getParent() != null ? element.getParent().getNumElements() : 0;
	}
	
	private int getElementIndex(Element element) {
		
		if(element.getParent() != null) {
			
			Element parent = element.getParent();
			
			for(int i = 0; i < parent.getNumElements(); i++) {
				
				if(parent.getElement(i) == element)
					return i;
			}
			
			return -1;
		}
		
		return 0;
	}
	
	private FUSIONStone getModule(Element element) {
		
		ArrayList<Object> packet = new ArrayList<Object>();
		packet.add(element);
		
		ArrayList<Object> call = call(packet);
		
		for(int i = 0; i < call.size(); i++) {
			
			if(call.get(i) instanceof FUSIONStone)
				return (FUSIONStone) call.get(i);
		}
		
		return null;
	}
	
	public boolean onVerify(Element element) {
		return true;
	}
	
	public boolean onTrickleDown(Element element) {
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> subProcessed) {
		return null;
	}
	
	public int onChangeDepth(Element element, ArrayList<Object> subProcessed, int currentDepth) {
		return currentDepth;
	}
	
	public Element onChangeElement(Element element, ArrayList<Object> subProcessed) {
		return null;
	}
}