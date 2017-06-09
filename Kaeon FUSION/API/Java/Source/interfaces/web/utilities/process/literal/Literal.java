package interfaces.web.utilities.process.literal;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.process.variable.Variable;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class Literal extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() != 0)
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
		return format(element.getContent());
	}
	
	public String format(String string) {
		
		boolean inQuotes = string.indexOf('\"') != -1;
		
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
		
		if(!inQuotes) {
			
			if(isVariable(string))
				return "$" + string;
			
			if(isNumber(string) || isBoolean(string) || isNull(string) || isVariable(string))
				return string;
		}
		
		return '\"' + string + '\"';
	}
	
	private boolean isNumber(String string) {
		
		try {
			
			Double.parseDouble(string);
			
			return true;
		}
		
		catch(Exception exception) {
			
		}
		
		return false;
	}
	
	private boolean isBoolean(String string) {
		
		if(string.equalsIgnoreCase("True"))
			return true;
		
		if(string.equalsIgnoreCase("False"))
			return true;
		
		return false;
	}
	
	private boolean isNull(String string) {
		
		if(string.equalsIgnoreCase("Null"))
			return true;
		
		return false;
	}
	
	private boolean isVariable(String string) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		return ((Variable) get(tags).get(0)).isVariable(string);
	}
}