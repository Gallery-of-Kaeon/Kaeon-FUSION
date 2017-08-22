package interfaces.web.utilities.process.function;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.script.variable.Variable;
import one_plus.element.Element;

public class Function extends FUSIONStone {
	
	private ArrayList<ArrayList<Element>> functions;
	
	public Function() {
		
		tag("Function");
		
		functions = new ArrayList<ArrayList<Element>>();
		
		push();
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Define") || getFunction(element.getContent()) != null;
	}
	
	public boolean onTrickleDown(Element element) {
		return element.getContent().equalsIgnoreCase("Define");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(getFunction(element.getContent()) != null) {
			
			String functionCall = element.getContent() + "(array(";
			
			for(int i = 0; i < element.getNumElements(); i++)
				functionCall += process(element.getElement(i));
			
			if(element.getParent().getContent() == null)
				return functionCall + "));";
			
			if(element.getParent().getParent().getContent().equalsIgnoreCase("Define") ||
					element.getParent().getContent().equalsIgnoreCase("Scope") ||
					element.getParent().getContent().equalsIgnoreCase("Process")) {
				
				return functionCall + ");";
			}
			
			return functionCall + ")";
		}
		
		String definitions = "";
		
		for(int i = 0; i < element.getNumElements(); i++) {
			
			functions.get(functions.size() - 1).add(element.getElement(i));
			
			definitions += "function " + element.getElement(i).getContent() + "($arguments){";
			
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("Variable");
			
			Variable variable = (Variable) get(tags).get(0);
			
			variable.push();
			
			tags = new ArrayList<String>();
			tags.add("Function");
			
			Function function = (Function) get(tags).get(0);
			
			function.push();
			
			for(int j = 0; j < element.getElement(i).getNumElements(); j++)
				definitions += process(element.getElement(i).getElement(j));
			
			variable.pop();
			function.pop();
			
			definitions += "}";
		}
		
		return definitions;
	}
	
	public Element getFunction(String function) {
		
		for(int i = 0; i < functions.size(); i++) {
			
			for(int j = 0; j < functions.get(i).size(); j++) {
				
				if(functions.get(i).get(j).getContent().equalsIgnoreCase(function))
					return functions.get(i).get(j);
			}
		}
		
		return null;
	}
	
	public void push() {
		functions.add(new ArrayList<Element>());
	}
	
	public void pop() {
		functions.remove(functions.size() - 1);
	}
}