package interfaces.machine.utilities.function;

import java.util.ArrayList;

import fusion.FUSIONStone;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;

public class Function extends FUSIONStone {
	
	private ArrayList<String> functions;
	
	public Function() {
		
		tag("Functions");
		
		functions = new ArrayList<String>();
	}
	
	public boolean onVerify(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(element.getContent());
		
		return has(tags);
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		functions.add(element.getContent());
		
		String function = element.getContent() + "(";
		
		for(int i = 0; i < processed.size(); i++)
			function += processed.get(i) + (i < processed.size() - 1 ? "," : "");
		
		return function + ")" + (element.getParent().getParent() == null ? ";" : "");
	}
	
	public void addFunction(String function) {
		functions.add(function);
	}
	
	public ArrayList<String> getFunctions() {
		
		ArrayList<String> newFunctions = new ArrayList<String>();
		newFunctions.addAll(functions);
		
		functions = new ArrayList<String>();
		
		return newFunctions;
	}
	
	public boolean isStructure(String structure) {
		
		try {
			
			ArrayList<String> tags = new ArrayList<String>();
			
			tags.add("Function");
			tags.add(structure);
			
			return getMeta(((FunctionStone) get(tags).get(0)).getFunction()).hasElement("Structure");
		}
		
		catch(Exception exception) {
			
		}
		
		return false;
	}
	
	public Element getMeta(Element element) {
		
		Element current = element;
		int position = getElementPosition(current);
		
		while(position != -1) {
			
			current = element.getParent().getElement(position);
			
			if(current.getContent().equalsIgnoreCase("Meta"))
				return current;
			
			position--;
		}
		
		return null;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		for(int i = 0; i < parent.getNumElements(); i++) {
			
			if(parent.getElement(i) == element)
				return i;
		}
		
		return 0;
	}
}