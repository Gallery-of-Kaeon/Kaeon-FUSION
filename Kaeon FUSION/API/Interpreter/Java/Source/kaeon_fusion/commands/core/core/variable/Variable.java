package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import kaeon_fusion.state.variable_stone.VariableStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class Variable extends Command {
	
	public Variable() {
		tag("Variable Command");
	}
	
	public boolean onVerify(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Command");
		
		ArrayList<PhilosophersStonePlus> commands = get(tags);
		
		for(int i = 0; i < commands.size(); i++) {
			
			if(!(commands.get(i) instanceof Literal) &&
					!(commands.get(i) instanceof Variable) &&
					!(commands.get(i) instanceof Function)) {
				
				if(((Command) commands.get(i)).onVerify(element))
					return false;
			}
		}
		
		if(element.getNumElements() == 0) {
			
			tags = new ArrayList<String>();
			
			tags.add("Variable");
			tags.add(element.getContent());
			
			return has(tags);
		}
		
		else {
			
			tags = new ArrayList<String>();
			
			tags.add("Variable");
			tags.add(element.getContent());
			
			if(has(tags) && element.getNumElements() > 1)
				return false;
			
			tags = new ArrayList<String>();
			
			tags.add("Function");
			tags.add(element.getContent());
			
			if(has(tags))
				return false;
			
			tags = new ArrayList<String>();
			
			tags.add("Native");
			tags.add(element.getContent());
			
			if(has(tags))
				return false;
			
			tags = new ArrayList<String>();
			
			tags.add("Interface");
			tags.add(element.getContent());
			
			if(has(tags))
				return false;
		}
		
		return true;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(element.getContent());
		
		if(processed.size() > 0) {
			
			if(!has(tags))
				getStack(1).publiclyConnect(new VariableStone(element.getContent(), processed.get(0)));
			
			else {
				
				VariableStone stone = (VariableStone) get(tags).get(0);
				
				stone.setValue(processed.get(0));
			}
		}
		
		tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(element.getContent());
		
		return ((VariableStone) get(tags).get(0)).getValue();
	}
}