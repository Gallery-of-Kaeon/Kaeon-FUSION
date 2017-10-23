package legacy.utilities.one_plus.read;

import java.util.ArrayList;

import legacy.utilities.one_plus.ONEPlus;

public class ONEPlusReader {
	
	public static void readONEPlus(ONEPlus onePlus, ArrayList<String> file) {
		
		while(file.size() > 0) {
			
			ONEPlusElement element = getElement(file, 0);
			element.setParent(onePlus);
			
			onePlus.addElement(element);
		}
	}
	
	private static ONEPlusElement getElement(ArrayList<String> file, int nest) {
		
		ONEPlusElement element = new ONEPlusElement();
		
		String definition = file.remove(0).trim();
		
		if(!definition.equals("-")) {
			
			element.setLineNumber(Integer.parseInt(definition.substring(0, definition.indexOf(' '))));
			definition = definition.substring(definition.indexOf(' ') + 1);
			
			element.setElementNumber(Integer.parseInt(definition.substring(0, definition.indexOf(' '))));
			definition = definition.substring(definition.indexOf(' ') + 1);
			
			element.setDefinition(definition);
		}
		
		String content = null;
		
		while(file.size() > 0) {
			
			String line = file.remove(0);
			
			if(getNumIndents(line) > nest) {
				
				if(content == null)
					content = "";
				
				if(content.length() > 0)
					content += '\n';
				
				content += line.substring(nest + 1);
			}
			
			else
				break;
		}
		
		element.setContent(content);
		
		while(file.size() > 0) {
			
			if(getNumIndents(file.get(0)) > nest) {
				
				ONEPlusElement newElement = getElement(file, nest + 1);
				newElement.setParent(element);
				
				element.addElement(newElement);
			}
			
			else
				break;
		}
		
		return element;
	}
	
	private static int getNumIndents(String line) {
		
		int indents = 0;
		
		for(int i = 0; i < line.length(); i++) {
			
			if(line.charAt(i) != '\t')
				break;
			
			indents++;
		}
		
		return indents;
	}
}