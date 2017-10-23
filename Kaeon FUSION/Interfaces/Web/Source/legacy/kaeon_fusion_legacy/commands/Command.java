package legacy.kaeon_fusion_legacy.commands;

import java.util.ArrayList;

import io.IO;
import legacy.kaeon_fusion_legacy.KaeonFUSION;
import legacy.kaeon_fusion_legacy.state.State;
import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.kaeon_fusion_legacy.super_mode.SuperMode;
import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.ONEPlus;
import legacy.utilities.one_plus.element.Element;
import legacy.utilities.philosophers_stone_plus.PhilosophersStonePlus;
import one.ElementUtilities;

public class Command extends FUSIONStone {
	
	public Command() {
		tag("Command");
	}
	
	public boolean onTrickleDown(Element element) {
		
		KaeonFUSION fusion = getKaeonFUSION();
		
		if(fusion.isReturning())
			return false;
		
		State state = new State(fusion);
		
		publiclyConnectMutually(state);
		
		for(int i = 0; i < element.getNumElements(); i++) {
			
			if(element.getElement(i).getContent().equalsIgnoreCase("Import")) {
				
				Element importElement = element.removeElement(i);
				i--;

				for(int j = 0; j < importElement.getNumElements(); j++) {
					
					String file = IO.openAsString(importElement.getElement(j).getContent());
					
					ONEPlus onePlus = new ONEPlus(file);
					
					if(file.indexOf("[SUPER]") != -1)
						SuperMode.superMode(onePlus);
					
					ArrayList<Element> imports = onePlus.getElements("Import");
					ArrayList<Element> functions = onePlus.getElements("Define");
					
					element.addElements(imports);
					element.addElements(functions);
				}
			}
			
			else if(element.getElement(i).getContent().equalsIgnoreCase("Define")) {
				
				Element functionElement = element.removeElement(i);
				i--;
				
				for(int j = 0; j < functionElement.getNumElements(); j++) {
					
					if(!functionElement.getElement(j).getContent().equalsIgnoreCase("Meta")) {
						
						ArrayList<String> tags = new ArrayList<String>();
						
						tags.add("Function");
						tags.add(functionElement.getElement(j).getContent());
						
						ArrayList<PhilosophersStonePlus> functions = state.get(tags);
						
						for(int k = 0; k < functions.size(); k++)
							state.disconnectMutually(functions.get(k));
						
						state.publiclyConnect(new FunctionStone(functionElement.getElement(j)));
					}
				}
			}
		}
		
		boolean descend = false;
		
		if(!fusion.hasErrorOccured() || element.getContent().equalsIgnoreCase("Catch"))
			descend = onDescend(element);
		
		return descend;
	}
	
	public one.Element translateElement(Element element) {
		
		Element currentElement = element;
		ArrayList<Integer> indexStack = new ArrayList<Integer>();
		
		one.Element newElement = translateElementChildren(currentElement);
		
		for(int i : indexStack)
			newElement = newElement.children.get(i);
		
		return newElement;
	}
	
	public one.Element translateElementChildren(Element element) {
		
		one.Element newElement = new one.Element();
		
		newElement.content = element.getContent();
		
		for(Element child : element.getElements())
			newElement.children.add(translateElementChildren(child));
		
		return newElement;
	}
	
	public int getElementIndex(Element element) {
		
		Element parent = element.getParent();
		
		for(int i = 0; i < parent.getNumElements(); i++) {
			
			if(parent.getElement(i) == element)
				return i;
		}
		
		return -1;
	}
	
	public Element translateElementLegacy(one.Element element) {
		
		one.Element currentElement = element;
		ArrayList<Integer> indexStack = new ArrayList<Integer>();
		
		while(currentElement.parent != null) {
			indexStack.add(ElementUtilities.getIndex(currentElement));
			currentElement = currentElement.parent;
		}
		
		Element newElement = translateElementChildrenLegacy(currentElement);
		
		for(int i : indexStack)
			newElement = newElement.getElement(i);
		
		return newElement;
	}
	
	public Element translateElementChildrenLegacy(one.Element element) {
		
		Element newElement = new Element();
		
		newElement.setContent(element.content);
		
		for(one.Element child : element.children)
			newElement.addElement(translateElementChildrenLegacy(child));
		
		return newElement;
	}
	
	public boolean onDescend(Element element) {
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		KaeonFUSION fusion = getKaeonFUSION();
		
		if(fusion.isReturning())
			return null;
		
		Object object = null;
		
		if(!fusion.hasErrorOccured() || element.getContent().equalsIgnoreCase("Catch")) {
			
			try {
				object = onCommand(element, processed);
			}
			
			catch(Exception exception) {
				fusion.triggerError(true);
			}
		}
		
		State stackTop = getStack(0);
		
		if(stackTop != null)
			stackTop.pop();
		
		return object;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return null;
	}
	
	public KaeonFUSION getKaeonFUSION() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Kaeon FUSION");
		
		return (KaeonFUSION) (get(tags).get(0));
	}
	
	public State getStack(int space) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("State");
		
		ArrayList<PhilosophersStonePlus> stateStack = get(tags);
		
		if(stateStack.size() == 0)
			return null;
		
		int maxIndex = 0;
		
		for(int i = 0; i <= space; i++) {
			
			maxIndex = 0;
			
			for(int j = 0; j < stateStack.size(); j++) {
				
				if(Integer.parseInt(stateStack.get(j).getTags().get(1)) >
						Integer.parseInt(stateStack.get(maxIndex).getTags().get(1))) {
					
					maxIndex = j;
				}
			}
			
			if(i < space)
				stateStack.remove(maxIndex);
		}
		
		return (State) stateStack.get(maxIndex);
	}
}