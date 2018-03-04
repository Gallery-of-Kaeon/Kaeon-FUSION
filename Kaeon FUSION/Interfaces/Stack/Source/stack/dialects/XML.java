package stack.dialects;

import java.util.ArrayList;

import one.Element;
import stack.utilities.Dialect;

public class XML extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		String xml = "";
		
		for(int i = 0; i < element.children.size(); i++)
			xml += processElement(element.children.get(i));
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "data";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".xml");
		file.add(xml);
		
		files.add(file);
	}
	
	public String processElement(Element element) {
		
		String xml = "<" + processLiteral(element) + " ";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Attributes")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++) {
					
					xml +=
							processLiteral(element.children.get(i).children.get(j)) +
							"=\"" +
							processLiteral(element.children.get(i).children.get(j).children.get(0)) +
							"\"";
				}
			}
		}
		
		xml += ">";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Data")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++)
					xml += processLiteral(element.children.get(i).children.get(j));
			}
			
			if(element.children.get(i).content.equalsIgnoreCase("Children")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++)
					xml += processElement(element.children.get(i).children.get(j));
			}
		}
		
		return xml + "</" + processLiteral(element) + ">";
	}
	
	public String processLiteral(Element element) {
		
		String literal = element.content;
		
		literal.replace("&", "&amp;");
		literal.replace("<", "&lt;");
		literal.replace(">", "&gt;");
		literal.replace("\"", "&quot;");
		literal.replace("\'", "&apos;");
		
		return literal;
	}
}