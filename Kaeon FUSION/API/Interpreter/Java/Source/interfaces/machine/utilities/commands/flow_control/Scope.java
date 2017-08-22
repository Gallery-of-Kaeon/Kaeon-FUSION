package interfaces.machine.utilities.commands.flow_control;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.machine.utilities.variable.Variable;
import one_plus.element.Element;

public class Scope extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Scope");
	}
	
	public boolean onTrickleDown() {
		
		push();
		
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String scope = "do{";
		
		boolean isIf = false;
		boolean isIn = false;
		
		if(processed.size() > 0) {
			
			if(element.getElement(0).getContent().equalsIgnoreCase("In")) {
				
				isIn = true;
				
				if(!element.getElement(0).getElement(0).getContent().equalsIgnoreCase("Default"))
					scope = element.getElement(0).getElement(0).getContent() + ".";
				
				else
					scope = "";
			}
		}
		
		if(processed.size() > 0) {
			
			isIf = element.getElement(0).getContent().equalsIgnoreCase("Break");
			
			if(isIf && element.getElement(0).getNumElements() > 0)
				scope = "if(" + process(element.getElement(0).getElement(0)) + "){do{";
		}
		
		for(int i = (isIf || isIn) ? 1 : 0; i < processed.size(); i++) {
			
			if(isIn) {
				
				if(("" + processed.get(i)).indexOf("return ") == 0)
					scope += ("" + processed.get(i)).substring(7, ("" + processed.get(i)).length() - 1);
				
				else {
					
					String function = element.getElement(i).getContent() + "(";
					
					for(int j = 0; j < element.getElement(i).getNumElements(); j++) {
						
						function += process(element.getElement(i).getElement(j));
						
						if(j < element.getElement(i).getNumElements() - 1)
							function += ",";
					}
					
					scope += function + ")";
					
					if(element.getParent().getParent() == null)
						scope += ";";
				}
			}
			
			else
				scope += processed.get(i);
		}
		
		pop();
		
		if(isIf)
			return scope + "}while(0);}";
		
		if(isIn)
			return scope;
		
		return scope + "}while(0);";
	}
	
	public void push() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		((Variable) get(tags).get(0)).push();
	}
	
	public void pop() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		((Variable) get(tags).get(0)).pop();
	}
}