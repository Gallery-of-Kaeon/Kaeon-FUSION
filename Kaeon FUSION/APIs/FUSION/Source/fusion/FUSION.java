package fusion;

import java.util.ArrayList;
import java.util.Arrays;

import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class FUSION extends PhilosophersStone {

	public boolean running = false;
	
	public ArrayList<FUSIONUnit> fusionUnits = new ArrayList<FUSIONUnit>();
	public boolean updated = false;
	
	public FUSION() {
		tags.add("FUSION");
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
	
	public void update() {
		
		ArrayList<FUSIONUnit> fusionUnitBuffer = new ArrayList<FUSIONUnit>();
		
		ArrayList<PhilosophersStone> newFusionUnits =
				PhilosophersStoneUtilities.get(this, "FUSION Unit");
		
		for(int i = 0; i < newFusionUnits.size(); i++) {
			
			try {
				fusionUnitBuffer.add((FUSIONUnit) newFusionUnits.get(i));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		for(int i = 0; i < fusionUnitBuffer.size(); i++) {
			
			for(int j = i + 1; j < fusionUnitBuffer.size(); j++) {
				
				if(fusionUnitBuffer.get(i).getClass().equals(fusionUnitBuffer.get(j).getClass())) {
					fusionUnitBuffer.remove(j);
					j--;
				}
			}
		}
		
		fusionUnits = fusionUnitBuffer;
		
		updated = true;
	}
	
	public void process(Element element) {
		
		running = true;
		
		update();
		
		internalProcess(element, true);
		
		fusionUnits = new ArrayList<FUSIONUnit>();
		running = false;
	}
	
	public void internalProcess(Element element, boolean internal) {
		
		Element currentElement = element;
		
		if(!internal) {
			
			running = true;
			
			update();
		}
		
		ArrayList<ArrayList<Object>> processed =
				new ArrayList<ArrayList<Object>>(
						Arrays.asList(
								new ArrayList<Object>(),
								new ArrayList<Object>()));
		
		boolean bubbleUp = false;
		
		while(running) {
			
			updated = false;
			
			boolean denied = isDenied(currentElement);
			
			ArrayList<FUSIONUnit> verifiedFUSIONUnits =
					!denied ?
							getVerifiedFUSIONUnits(currentElement) :
							new ArrayList<FUSIONUnit>();
			
			if(!bubbleUp) {
				
				boolean trickleDown = trickleDown(verifiedFUSIONUnits, currentElement);
				
				if(trickleDown && currentElement.children.size() > 0) {
					
					currentElement = currentElement.children.get(0);
					processed.add(new ArrayList<Object>());
					
					continue;
				}
			}

			ArrayList<Object> processedArguments = processed.get(processed.size() - 2);
			ArrayList<Object> arguments = processed.get(processed.size() - 1);
			
			Object object = processElement(verifiedFUSIONUnits, currentElement, arguments);
			verifiedFUSIONUnits = updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);
			
			boolean terminated = terminate(verifiedFUSIONUnits, currentElement, arguments);
			verifiedFUSIONUnits = updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);
			
			boolean added = isAdded(verifiedFUSIONUnits, currentElement, arguments);
			verifiedFUSIONUnits = updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);
			
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
				
				for(int i = 0; i < processed.size(); i++)
					processed.set(i, new ArrayList<Object>());
				
				currentElement = jumpElement;
				
				bubbleUp = false;
			}
		}
	}
	
	public ArrayList<FUSIONUnit> updateVerifiedUnits(
			ArrayList<FUSIONUnit> verifiedFUSIONUnits,
			Element currentElement,
			boolean denied) {
		
		boolean update = updated;
		updated = false;
		
		if(update) {
			
			return
				!denied ?
						getVerifiedFUSIONUnits(currentElement) :
						new ArrayList<FUSIONUnit>();
		}
		
		return verifiedFUSIONUnits;
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
				handleError(element, new ArrayList<Object>(), exception);
			}
		}
		
		return denied;
	}
	
	public ArrayList<FUSIONUnit> getVerifiedFUSIONUnits(Element element) {
		
		ArrayList<FUSIONUnit> verifiedFUSIONUnits = new ArrayList<FUSIONUnit>();
		
		for(int i = 0; i < fusionUnits.size(); i++) {
			
			try {
				
				if(fusionUnits.get(i).verify(element))
					verifiedFUSIONUnits.add(fusionUnits.get(i));
			}
			
			catch(Exception exception) {
				handleError(element, new ArrayList<Object>(), exception);
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
				handleError(element, new ArrayList<Object>(), exception);
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
				handleError(element, new ArrayList<Object>(), exception);
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
				result = verifiedFUSIONUnits.get(i).isAdded(element, processed);
			}
			
			catch(Exception exception) {
				handleError(element, new ArrayList<Object>(), exception);
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
				result = verifiedFUSIONUnits.get(i).terminate(element, processed);
			}
			
			catch(Exception exception) {
				handleError(element, new ArrayList<Object>(), exception);
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
				handleError(element, new ArrayList<Object>(), exception);
			}
			
			if(defaultElement != newJumpElement)
				jumpElement = newJumpElement;
		}
		
		return jumpElement;
	}
	
	public void handleError(Element element, ArrayList<Object> arguments, Exception exception) {
		
		for(FUSIONUnit unit : fusionUnits) {
			
			try {
				unit.handleError(element, arguments, exception);
			}
			
			catch(Exception innerException) {
				
			}
		}
	}
}