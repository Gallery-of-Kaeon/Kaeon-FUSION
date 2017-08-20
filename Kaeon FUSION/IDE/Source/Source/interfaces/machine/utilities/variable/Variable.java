package interfaces.machine.utilities.variable;

import java.util.ArrayList;

import interfaces.machine.utilities.function.Function;
import interfaces.machine.utilities.literal.Literal;
import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class Variable extends FUSIONStone {
	
	private ArrayList<ArrayList<String>> variables;
	private ArrayList<ArrayList<String>> pointers;
	
	public Variable() {
		
		tag("Variable");

		variables = new ArrayList<ArrayList<String>>();
		pointers = new ArrayList<ArrayList<String>>();
		
		push();
	}
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() == 0)
			return false;
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(element.getContent());
		
		if(has(tags))
			return true;
		
		ArrayList<PhilosophersStonePlus> atlas = getAtlas();
		
		for(int i = 0; i < atlas.size(); i++) {
			
			if((atlas.get(i) instanceof FUSIONStone)
					&& !(atlas.get(i) instanceof Literal)
					&& !(atlas.get(i) instanceof Variable)) {
					
				if(((FUSIONStone) atlas.get(i)).onVerify(element))
					return false;
			}
		}
		
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(element.getContent());
		
		if(has(tags) || isIn(element))
			return element.getContent() + "=" + processed.get(0) + ";";
		
		variables.get(variables.size() - 1).add(element.getContent());
		
		if(getMeta(element).hasElement("Pointer"))
			pointers.get(pointers.size() - 1).add(element.getContent());
		
		String variable = processMeta(element) + element.getContent() + postProcessMeta(element) ;
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(!element.getElement(i).getContent().equalsIgnoreCase("Meta") &&
					!element.getElement(i).getContent().equalsIgnoreCase("Default")) {
				
				variable += "=" + processed.get(i);
				
				break;
			}
		}
		
		variable += ";";
		
		return variable;
	}
	
	private String processMeta(Element element) {
		
		String code = "";
		
		Element meta = getMeta(element);
		
		if(meta == null)
			return "void* ";
		
		if(meta.hasElement("Automatic"))
			code += "auto ";
		
		if(meta.hasElement("External"))
			code += "extern ";
		
		if(meta.hasElement("Static"))
			code += "static ";
		
		if(meta.hasElement("Constant"))
			code += "const ";
		
		if(meta.hasElement("Volatile"))
			code += "volatile ";
		
		if(meta.hasElement("Signed"))
			code += "signed ";
		
		else if(meta.hasElement("Unsigned"))
			code += "unsigned ";
		
		if(meta.hasElement("Integer"))
			code += "int ";
		
		else if(meta.hasElement("Float"))
			code += "float ";
		
		else if(meta.hasElement("Double"))
			code += "double ";
		
		else if(meta.hasElement("Short"))
			code += "short ";
		
		else if(meta.hasElement("Long"))
			code += "long ";
		
		else if(meta.hasElement("Character"))
			code += "char ";
		
		else if(meta.hasElement("Void"))
			code += "void ";
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Functions");
		
		Function function = (Function) get(tags).get(0);
		
		for(int i = 0; i < meta.getNumElements(); i++) {
			
			if(function.isStructure(meta.getElement(i).getContent())) {
				
				function.addFunction(meta.getElement(i).getContent());
				
				code += "struct " + meta.getElement(i).getContent() + " ";
			}
		}
		
		if(meta.hasElement("Pointer")) {
			
			if(meta.getElement("Pointer").getNumElements() > 0) {
				
				for(int i = 0; i < Integer.parseInt(meta.getElement("Pointer").getElement(0).getContent()); i++)
					code += "*";
			}
			
			else
				code += "*";
		}
		
		if(meta.hasElement("Address")) {
			
			if(meta.getElement("Address").getNumElements() > 0) {
				
				for(int i = 0; i < Integer.parseInt(meta.getElement("Address").getElement(0).getContent()); i++)
					code += "&";
			}
			
			else
				code += "&";
		}
		
		return code;
	}
	
	private String postProcessMeta(Element element) {
		
		String code = "";
		
		Element list = getMeta(element).getElement("List");
		
		if(list != null) {
			
			if(list.getNumElements() == 0)
				return "[]";
			
			for(int i = 0; i < list.getNumElements(); i++)
				code += "[" + process(list.getElement(i)) + "]";
		}
		
		return code;
	}
	
	public boolean isIn(Element element) {
		
		int position = getElementPosition(element);
		
		while(position > -1) {
			
			if(element.getParent().getElement(position).getContent().equalsIgnoreCase("In"))
				return true;
			
			position--;
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
	
	public boolean isVariable(String variable) {
		
		for(int i = 0; i < variables.size(); i++) {
			
			for(int j = 0; j < variables.get(i).size(); j++) {
				
				if(variables.get(i).get(j).equalsIgnoreCase(variable))
					return true;
			}
		}
		
		return false;
	}
	
	public boolean isPointer(String pointer) {
		
		for(int i = 0; i < pointers.size(); i++) {
			
			for(int j = 0; j < pointers.get(i).size(); j++) {
				
				if(pointers.get(i).get(j).equalsIgnoreCase(pointer))
					return true;
			}
		}
		
		return false;
	}
	
	public void push() {
		variables.add(new ArrayList<String>());
		pointers.add(new ArrayList<String>());
	}
	
	public void pop() {
		variables.remove(variables.size() - 1);
		pointers.remove(pointers.size() - 1);
	}
}