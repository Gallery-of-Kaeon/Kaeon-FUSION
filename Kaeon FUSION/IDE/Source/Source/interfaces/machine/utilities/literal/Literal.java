package interfaces.machine.utilities.literal;

import java.util.ArrayList;

import interfaces.machine.utilities.variable.Variable;
import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class Literal extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() != 0)
			return false;
		
		ArrayList<PhilosophersStonePlus> atlas = getAtlas();
		
		for(int i = 0; i < atlas.size(); i++) {
			
			if((atlas.get(i) instanceof FUSIONStone)
					&& !(atlas.get(i) instanceof Literal)) {
					
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
		
		boolean inQuotes =
				string.charAt(0) == '\"' &&
				string.charAt(string.length() - 1) == '\"';
		
		if(!inQuotes && isString(string))
			string = '\"' + string + '\"';
		
		return string;
	}
	
	private boolean isString(String string) {
		return !(isNumber(string) || isBoolean(string) || isNull(string) || isVariable(string));
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