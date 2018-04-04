package fusion;

import java.util.ArrayList;
import java.util.Arrays;

import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class FUSION extends PhilosophersStone {

	public boolean running;
	
	public ArrayList<FUSIONUnit> fusionUnits;
	public Element currentElement;
	
	public FUSION() {
		
		tags.add("FUSION");
		
		fusionUnits = new ArrayList<FUSIONUnit>();
		running = false;
	}
	
	public void process(Element element) {
		
		running = true;
		
		currentElement = element;
		
		update();
		
		ArrayList<ArrayList<Object>> processed =
				new ArrayList<ArrayList<Object>>(
						Arrays.asList(
								new ArrayList<Object>(),
								new ArrayList<Object>()));
		
		boolean bubbleUp = false;
		
		while(running) {
			
			boolean denied = isDenied(currentElement);
			
			ArrayList<FUSIONUnit> verifiedFUSIONUnits =
					!denied ?
							getVerifiedFUSIONUnits(currentElement) :
							new ArrayList<FUSIONUnit>();
			
			if(!bubbleUp) {
				
				boolean trickleDown = trickleDown(verifiedFUSIONUnits, currentElement);
				
				if(trickleDown && currentElement.children.size() > 0) {
					
					currentElement.children = sort(currentElement.children);
					
					currentElement = currentElement.children.get(0);
					processed.add(new ArrayList<Object>());
					
					continue;
				}
			}

			ArrayList<Object> processedArguments = processed.get(processed.size() - 2);
			ArrayList<Object> arguments = processed.get(processed.size() - 1);
			
			Object object = processElement(verifiedFUSIONUnits, currentElement, arguments);
			boolean terminated = terminate(verifiedFUSIONUnits, currentElement, arguments);
			boolean added = isAdded(verifiedFUSIONUnits, currentElement, arguments);
			Element jumpElement = jump(verifiedFUSIONUnits, currentElement, arguments);
			
			if(!denied && added)
				processedArguments.add(object);
			
			processed.set(processed.size() - 1, new ArrayList<Object>());
			
			if(terminated)
				break;
			
			bubbleUp = false;
			
			if(jumpElement == null) {
				
				int index = ElementUtilities.getIndex(currentElement);
				
				if(currentElement.parent == null)
					break;
				
				if(index < currentElement.parent.children.size() - 1)
					currentElement = currentElement.parent.children.get(index + 1);
				
				else {
					
					currentElement = currentElement.parent;
					bubbleUp = true;
					
					processed.remove(processed.size() - 1);
				}
			}
			
			else {
				
				clearArgumentsOnJump(processed);
				currentElement = jumpElement;
				
				bubbleUp = false;
			}
		}
		
		fusionUnits = new ArrayList<FUSIONUnit>();
		running = false;
	}
	
	public void update() {
		
		ArrayList<PhilosophersStone> fusionStones =
				PhilosophersStoneUtilities.get(this, "FUSION Command");
		
		for(PhilosophersStone stone : fusionStones) {
			
			try {
				this.fusionUnits.add((FUSIONUnit) stone);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		clean();
		
		sortFutureCommands(currentElement);
	}
	
	public void clean() {
		
		for(int i = 0; i < fusionUnits.size(); i++) {
			
			for(int j = i + 1; j < fusionUnits.size(); j++) {
				
				if(fusionUnits.get(i).getClass().equals(fusionUnits.get(j).getClass())) {
					fusionUnits.remove(j);
					j--;
				}
			}
		}
	}
	
	public void sortFutureCommands(Element element) {
		
		Element current = element;
		
		while(current.parent != null) {
			
			ArrayList<Element> children = current.parent.children;
			int index = ElementUtilities.getIndex(current);
			
			ArrayList<Element> futureCommands = new ArrayList<Element>();
			
			while(children.size() > index + 1)
				futureCommands.add(children.remove(index + 1));
			
			children.addAll(sort(futureCommands));
			
			current = current.parent;
		}
	}
	
	public void clearArgumentsOnJump(ArrayList<ArrayList<Object>> arguments) {
		arguments = new ArrayList<ArrayList<Object>>(arguments.size());
	}
	
	private ArrayList<Element> sort(ArrayList<Element> input) {
		
		if(input.size() <= 1)
			return input;
		
		int middle = (int) Math.ceil((double) input.size() / 2);
		Element pivot = input.get(middle);
		
		ArrayList<Element> less = new ArrayList<Element>();
		ArrayList<Element> greater = new ArrayList<Element>();
		
		for (int i = 0; i < input.size(); i++) {
			
			double priority = getPriority(input.get(i));
			double pivotPriority = getPriority(pivot);
			
			if(priority == pivotPriority) {
				priority = i;
				pivotPriority = middle;
			}
			
			if(priority <= pivotPriority) {
				
				if(i == middle)
					continue;
				
				less.add(input.get(i));
			}
			
			else
				greater.add(input.get(i));
		}
		
		return concatenate(sort(less), pivot, sort(greater));
	}
	
	private ArrayList<Element> concatenate(
			ArrayList<Element> less,
			Element pivot,
			ArrayList<Element> greater){
		
		ArrayList<Element> list = new ArrayList<Element>();
		
		list.addAll(less);
		list.add(pivot);
		list.addAll(greater);
		
		return list;
	}
	
	public double getPriority(Element element) {
		
		for(FUSIONUnit unit : fusionUnits) {
			
			if(unit.verify(element)) {
				
				double priority = unit.getPriority(element);
				
				if(priority != 0)
					return priority;
			}
		}
		
		return 0;
	}
	
	public boolean isDenied(Element element) {
		
		if(element.content == null)
			return false;
		
		boolean denied = false;
		
		for(FUSIONUnit unit : fusionUnits) {
			
			try {
				
				if(unit.deny(element))
					denied = true;
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
		}
		
		return denied;
	}
	
	public ArrayList<FUSIONUnit> getVerifiedFUSIONUnits(Element element) {
		
		if(element.content == null)
			return new ArrayList<FUSIONUnit>();
		
		ArrayList<FUSIONUnit> verifiedFUSIONUnits = new ArrayList<FUSIONUnit>();
		
		for(FUSIONUnit unit : fusionUnits) {
			
			try {
				
				if(unit.verify(element))
					verifiedFUSIONUnits.add(unit);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
		}
		
		return verifiedFUSIONUnits;
	}
	
	public boolean trickleDown(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element element) {
		
		boolean trickleDown = true;
		
		for(int i = 0; i < verifiedFUSIONUnits.size(); i++) {
			
			boolean result = true;
			
			try {
				result = verifiedFUSIONUnits.get(i).trickleDown(element);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
			
			if(!result)
				trickleDown = false;
		}
		
		return trickleDown;
	}
	
	public Object processElement(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element element,
			ArrayList<Object> processed) {
		
		Object object = null;
		
		for(int i = 0; i < verifiedFUSIONUnits.size(); i++) {
			
			Object newObject = null;
			
			try {
				newObject = verifiedFUSIONUnits.get(i).process(element, processed);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
			
			if(newObject != null)
				object = newObject;
		}
		
		return object;
	}
	
	public boolean isAdded(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element element,
			ArrayList<Object> processed) {
		
		boolean isAdded = true;
		
		for(int i = 0; i < verifiedFUSIONUnits.size(); i++) {
			
			boolean result = true;
			
			try {
				result = verifiedFUSIONUnits.get(i).isAdded(element);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
			
			if(!result)
				isAdded = false;
		}
		
		return isAdded;
	}
	
	public boolean terminate(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element element,
			ArrayList<Object> processed) {
		
		boolean terminate = false;
		
		for(int i = 0; i < verifiedFUSIONUnits.size(); i++) {
			
			boolean result = false;
			
			try {
				result = verifiedFUSIONUnits.get(i).terminate(element);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
			
			if(result)
				terminate = true;
		}
		
		return terminate;
	}
	
	public Element jump(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element element,
			ArrayList<Object> processed) {

		Element defaultElement = null;
		Element jumpElement = defaultElement;
		
		for(int i = 0; i < verifiedFUSIONUnits.size(); i++) {
			
			Element newJumpElement = defaultElement;
			
			try {
				newJumpElement = verifiedFUSIONUnits.get(i).jump(element, processed);
			}
			
			catch(Exception exception) {
				handleError(element, exception);
			}
			
			if(defaultElement != newJumpElement)
				jumpElement = newJumpElement;
		}
		
		return jumpElement;
	}
	
	public void handleError(Element element, Exception exception) {
		
		for(FUSIONUnit unit : fusionUnits) {
			
			try {
				unit.handleError(element, exception);
			}
			
			catch(Exception innerException) {
				
			}
		}
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(packet.get(0) instanceof String) {
			
			if(((String) packet.get(0)).equalsIgnoreCase("Update"))
				update();
			
			if(((String) packet.get(0)).equalsIgnoreCase("Stop"))
				running = false;
		}
		
		return null;
	}
	
	public boolean isRunning() {
		return running;
	}
}