package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class Literal extends Command {
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() != 0)
			return false;
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(element.getContent());
		
		if(has(tags))
			return false;
		
		tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(element.getContent());
		
		if(has(tags))
			return false;
		
		tags = new ArrayList<String>();
		
		tags.add("Interface");
		tags.add(element.getContent());
		
		if(has(tags))
			return false;
		
		tags = new ArrayList<String>();
		
		tags.add("Native");
		tags.add(element.getContent());
		
		if(has(tags))
			return false;
		
		tags = new ArrayList<String>();
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
		
		return true;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return format(element.getContent());
	}
	
	public static String format(String string) {
		
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) == '\\') {

				string = string.substring(0, i) + string.substring(i + 1);
				
				if(i < string.length()) {
					
					if(string.charAt(i) == 'n')
						string = string.substring(0, i) + '\n' + string.substring(i + 1);
					
					if(string.charAt(i) == 't')
						string = string.substring(0, i) + '\t' + string.substring(i + 1);
				}
				
				continue;
			}
			
			if(string.charAt(i) == '\"') {
				
				string = string.substring(0, i) + string.substring(i + 1);
				
				i--;
				continue;
			}
		}
		
		return string;
	}
}