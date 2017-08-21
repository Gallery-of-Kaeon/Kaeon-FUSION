package kaeon_fusion_legacy.commands;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import io.IO;
import kaeon_fusion.interfaces.KaeonFUSIONInterface;
import kaeon_fusion_legacy.KaeonFUSION;
import kaeon_fusion_legacy.interface_module.Interface;
import kaeon_fusion_legacy.state.State;
import kaeon_fusion_legacy.state.function_stone.FunctionStone;
import kaeon_fusion_legacy.super_mode.SuperMode;
import legacy.fusion.FUSIONStone;
import legacy.one_plus.ONEPlus;
import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

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
			
			else if(element.getElement(i).getContent().equalsIgnoreCase("Use")) {
				
				Element useElement = element.removeElement(i);
				i--;
				
				for(int j = 0; j < useElement.getNumElements(); j++) {
					
					String fusionInterface = useElement.getElement(j).getContent();
					
					if(fusionInterface.equalsIgnoreCase("Standard"))
						continue;
					
					try {
						
						try {
							
							URL url = new URL("file:" + fusionInterface + ".jar"); 
							URLClassLoader loader = new URLClassLoader (new URL[] {url});
							
							Class<?> moduleClass = Class.forName("kaeon_fusion_interface.KaeonFUSIONInterfaceModule", true, loader);
							
							Method getInterface = moduleClass.getMethod("getInterface");
							Object instance = moduleClass.getConstructor().newInstance();
							
							connectForeignInterface((KaeonFUSIONInterface) getInterface.invoke(instance));
							
							loader.close();
						}
						
						catch(Exception exception) {
							
							ArrayList<String> tags = new ArrayList<String>();
							
							tags.add("Interface");
							tags.add(fusionInterface);
							
							((Interface) get(tags).get(0)).onStart();
						}
					}
					
					catch(Exception exception) {
						
					}
				}
			}
		}
		
		boolean descend = false;
		
		if(!fusion.hasErrorOccured() || element.getContent().equalsIgnoreCase("Catch"))
			descend = onDescend(element);
		
		return descend;
	}
	
	private void connectForeignInterface(KaeonFUSIONInterface fusionInterface) {
		
		kaeon_fusion.KaeonFUSION kaeonFUSION = new kaeon_fusion.KaeonFUSION();
		
		fusionInterface.connect(kaeonFUSION);
		
		for(PhilosophersStone connection : PhilosophersStoneUtilities.getConnections(kaeonFUSION)) {
			
			kaeon_fusion.command.Command command = (kaeon_fusion.command.Command) connection;
			
			if(command instanceof kaeon_fusion.command.use.Use)
				continue;
			
			Command newCommand = new Command() {
				
				public boolean onVerify(Element element) {
					
					one.Element newElement = translateElement(element);
					Object object = command.onCommandVerify(newElement);
					
					return (boolean) object;
				}
				
				public boolean onDescend(Element element) {
					
					one.Element newElement = translateElement(element);
					Object object = command.onCommandTrickleDown(newElement);
					
					return (boolean) object;
				}
				
				public Object onCommand(Element element, ArrayList<Object> processed) {
					
					one.Element newElement = translateElement(element);
					Object object = command.onCommandProcess(newElement, processed);
					
					return object;
				}
			};
			
			getKaeonFUSION().publiclyConnectMutually(newCommand);
		}
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