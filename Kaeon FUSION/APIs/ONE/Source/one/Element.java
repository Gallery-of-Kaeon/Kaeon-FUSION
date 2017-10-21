package one;

import java.util.ArrayList;

public class Element {
	
	public String content;
	
	public Element parent;
	public ArrayList<Element> children = new ArrayList<Element>();

	public String toString() {
		return writeElement(this, 0);
	}
	
	private String writeElement(Element element, int nest) {
		
		String code = "";
		
		boolean isRoot = element.parent == null;
		
		if(!isRoot) {
			
			String content = element.content;
			
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
		
		ArrayList<Element> elements = element.children;
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(!isRoot || i > 0)
				code += '\n';
			
			code +=
				writeElement(
					elements.get(i),
					element.parent != null ? nest + 1 : nest);
		}
		
		return code;
	}
	
	private String indent(int nest) {
		
		String indent = "";
		
		for(int i = 0; i < nest; i++)
			indent += '\t';
		
		return indent;
	}
}