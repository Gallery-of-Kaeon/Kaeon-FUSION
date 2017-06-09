package interfaces.web.utilities.process.variable;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.process.function.Function;
import interfaces.web.utilities.process.literal.Literal;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class Variable extends FUSIONStone {
	
	private ArrayList<String> globalVariables;
	private ArrayList<ArrayList<String>> variables;
	
	public Variable() {
		
		tag("Variable");

		globalVariables = new ArrayList<String>();
		globalVariables.add("Arguments");
		
		variables = new ArrayList<ArrayList<String>>();
		
		push();
	}
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() != 1)
			return false;
		
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
		
		String variable = "$" + element.getContent() + "=";
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		Element function = ((Function) get(tags).get(0)).getFunction("" + processed.get(0));
		
		if(function == null)
			variable += processed.get(0) + ";";
		
		else {
			
			for(int i = 0; i < function.getNumElements(); i++)
				variable += function.getElement(i).getContent() + process(element.getElement(i).getElement(0));

			variable += ";";
		}
		
		if(!isVariable(element.getContent())) {
			
			if(!element.getParent().getContent().equalsIgnoreCase("Global"))
				variables.get(variables.size() - 1).add(element.getContent());
			
			else
				globalVariables.add(element.getContent());
			
			return variable;
		}
		
		else
			return variable;
	}
	
	public boolean isVariable(String variable) {
		
		for(int i = 0; i < variables.size(); i++) {
			
			for(int j = 0; j < variables.get(i).size(); j++) {
				
				if(variables.get(i).get(j).equalsIgnoreCase(variable))
					return true;
			}
		}
		
		for(int i = 0; i < globalVariables.size(); i++) {
			
			if(globalVariables.get(i).equalsIgnoreCase(variable))
				return true;
		}
		
		return false;
	}
	
	public void push() {
		variables.add(new ArrayList<String>());
	}
	
	public void pop() {
		variables.remove(variables.size() - 1);
	}
}