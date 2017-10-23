package legacy.kaeon_fusion_legacy;

import java.util.ArrayList;

import io.IO;
import legacy.kaeon_fusion_legacy.commands.core.CoreCommandConnector;
import legacy.kaeon_fusion_legacy.console.Console;
import legacy.kaeon_fusion_legacy.interface_module.Interface;
import legacy.kaeon_fusion_legacy.state.State;
import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.kaeon_fusion_legacy.super_mode.SuperMode;
import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.ONEPlus;
import legacy.utilities.one_plus.element.Element;
import legacy.utilities.philosophers_stone_plus.PhilosophersStonePlus;
import one.ElementUtilities;

public class KaeonFUSION extends FUSIONStone {
	
	private ArrayList<Object> arguments;
	
	private boolean errorOccured;
	
	private boolean returning;
	private Object toReturn;
	
	private int stack;
	
	public KaeonFUSION() {
		
		tag("Kaeon FUSION");
		
		arguments = new ArrayList<Object>();

		CoreCommandConnector.connect(this);
	}
	
	public Object process(Element element) {
		
		errorOccured = false;
		
		returning = false;
		toReturn = null;
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Console");
		
		if(!has(tags))
			publiclyConnect(getConsole());
		
		return super.process(element);
	}
	
	public Object process(Element element, int depth) {
		
		errorOccured = false;
		
		returning = false;
		toReturn = null;
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Console");
		
		if(!has(tags))
			publiclyConnect(getConsole());
		
		return super.process(element, depth);
	}
	
	public Object process(Element element, ArrayList<Object> arguments) {
		
		this.arguments = arguments;
		
		return process(element);
	}
	
	public Object process(Element element, int depth, ArrayList<Object> arguments) {
		
		this.arguments = arguments;
		
		return process(element, depth);
	}
	
	public boolean onVerify(Element element) {
		return element.getContent() == null;
	}
	
	public boolean onTrickleDown(Element element) {
		
		State state = new State(this);
		
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
						
						ArrayList<PhilosophersStonePlus> functions = get(tags);
						
						for(int k = 0; k < functions.size(); k++)
							disconnectMutually(functions.get(k));
						
						publiclyConnect(new FunctionStone(functionElement.getElement(j)));
					}
				}
			}
		}
		
		return true;
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
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(returning)
			return toReturn;
		
		return processed;
	}
	
	public void triggerError(boolean error) {
		errorOccured = error;
	}
	
	public void returnObject(Object object) {
		
		returning = true;
		
		toReturn = object;
	}
	
	public void pushStack() {
		stack++;
	}
	
	public void popStack() {
		stack++;
	}
	
	public boolean hasErrorOccured() {
		return errorOccured;
	}
	
	public boolean isReturning() {
		return returning;
	}
	
	public int getStack() {
		return stack;
	}
	
	public ArrayList<Object> getArguments() {
		return arguments;
	}
	
	public Console getConsole() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Console");
		
		if(has(tags))
			return (Console) get(tags).get(0);
		
		return new Console();
	}
	
	public static Object process(String filePath) {
		
		try {
			
			String file = IO.openAsString(filePath);
			ONEPlus onePlus = new ONEPlus(file);
			
			if(file.indexOf("[SUPER]") != -1)
				SuperMode.superMode(onePlus);
			
			return new KaeonFUSION().process(onePlus);
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public static Object process(String filePath, ArrayList<Object> arguments) {
		
		try {
			
			String file = IO.openAsString(filePath);
			ONEPlus onePlus = new ONEPlus(file);
			
			if(file.indexOf("[SUPER]") != -1)
				SuperMode.superMode(onePlus);
			
			return new KaeonFUSION().process(onePlus, arguments);
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public static Object process(ArrayList<Interface> interfaces, String filePath) {
		
		try {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			for(int i = 0; i < interfaces.size(); i++)
				fusion.publiclyConnectMutually(interfaces.get(i));
			
			String file = IO.openAsString(filePath);
			ONEPlus onePlus = new ONEPlus(file);
			
			if(file.indexOf("[SUPER]") != -1)
				SuperMode.superMode(onePlus);
			
			return fusion.process(onePlus);
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public static Object process(ArrayList<Interface> interfaces, String filePath, ArrayList<Object> arguments) {
		
		try {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			for(int i = 0; i < interfaces.size(); i++)
				fusion.publiclyConnectMutually(interfaces.get(i));
			
			String file = IO.openAsString(filePath);
			ONEPlus onePlus = new ONEPlus(file);
			
			if(file.indexOf("[SUPER]") != -1)
				SuperMode.superMode(onePlus);
			
			return fusion.process(onePlus, arguments);
		}
		
		catch(Exception exception) {
			return null;
		}
	}
}