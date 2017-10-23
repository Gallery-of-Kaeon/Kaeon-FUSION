package legacy.utilities.one_plus.write;

import java.util.ArrayList;

import legacy.utilities.one_plus.element.Element;

public class ONEPlusWriter {

	public static String writeElement(Element element) {
		return writeElement(element, 0);
	}
	
	public static String writeElement(Element element, int nest) {
		
		String code = "";
		
		boolean isRoot = element.getParent() == null;
		
		if(!isRoot) {
			
			String content = element.getContent();
			
			code += indent(nest) + "-\n" + indent(nest + 1);
			
			for(int i = 0; i < content.length(); i++) {
				
				char character = content.charAt(i);
				
				if(character != '\n')
					code += character;
				
				else
					code += '\n' + indent(nest + 1);
			}
			
			code += '\n' + indent(nest) + '-';
		}
		
		ArrayList<Element> elements = element.getElements();
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(!isRoot || i > 0)
				code += '\n';
			
			code +=
				writeElement(
					elements.get(i),
					element.getParent() != null ? nest + 1 : nest);
		}
		
		return code;
	}
	
	private static String indent(int nest) {
		
		String indent = "";
		
		for(int i = 0; i < nest; i++)
			indent += '\t';
		
		return indent;
	}
}