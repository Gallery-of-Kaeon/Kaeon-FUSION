package kaeon_fusion.commands;

import java.util.ArrayList;

import fusion.FUSIONStone;
import io.IO;
import kaeon_fusion.KaeonFUSION;
import kaeon_fusion.interface_module.Interface;
import kaeon_fusion.state.State;
import kaeon_fusion.state.function_stone.FunctionStone;
import kaeon_fusion.super_mode.SuperMode;
import one_plus.ONEPlus;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

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
				
				try {
					
					Element useElement = element.removeElement(i);
					i--;

					for(int j = 0; j < useElement.getNumElements(); j++) {
						
						ArrayList<String> tags = new ArrayList<String>();
						
						tags.add("Interface");
						tags.add("" + useElement.getElement(j).getContent());
						
						((Interface) get(tags).get(0)).onStart();
					}
				}
				
				catch(Exception exception) {
					
				}
			}
		}
		
		boolean descend = false;
		
		if(!fusion.hasErrorOccured() || element.getContent().equalsIgnoreCase("Catch"))
			descend = onDescend(element);
		
		return descend;
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