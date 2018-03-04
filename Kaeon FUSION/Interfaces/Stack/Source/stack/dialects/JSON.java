package stack.dialects;

import java.util.ArrayList;

import one.Element;
import stack.utilities.Dialect;

public class JSON extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		String json = "";
		
		for(int i = 0; i < element.children.size(); i++)
			json += processElement(element.children.get(i));
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "data";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".json");
		file.add(json);
		
		files.add(file);
	}
	
	public String processElement(Element element) {
		
		if(element.content.equalsIgnoreCase("List")) {
			
			String list = "[";
			
			for(int i = 0; i < element.children.size(); i++) {
				
				list += processElement(element.children.get(i));
				
				if(i < element.children.size() - 1)
					list += ",";
			}
			
			return list + "]";
		}
		
		if(element.content.equalsIgnoreCase("Object")) {
			
			String list = "{";
			
			for(int i = 0; i < element.children.size(); i++) {
				
				list +=
						processFieldName(element.children.get(i)) +
						":" +
						processElement(element.children.get(i).children.get(0));
				
				if(i < element.children.size() - 1)
					list += ",";
			}
			
			return list + "}";
		}
		
		return processLiteral(element);
	}
	
	public String processLiteral(Element element) {
		
		String literal = element.content;
		
		try {
			
			if(!(literal.startsWith("\"") && literal.endsWith("\""))) {
				
				Double.parseDouble(literal);
			
				return literal;
			}
		}
		
		catch(Exception exception) {
			
		}
		
		if(literal.equalsIgnoreCase("true") ||
				literal.equalsIgnoreCase("false") ||
				literal.equalsIgnoreCase("null")) {
			
			return literal;
		}
		
		if(literal.startsWith("\""))
			literal = literal.substring(1);
		
		if(literal.endsWith("\""))
			literal = literal.substring(0, literal.length() - 1);

		literal.replace("\\", "\\\\");
		literal.replace("\"", "\\\"");
		literal.replace("\b", "\\b");
		literal.replace("\f", "\\f");
		literal.replace("\n", "\\n");
		literal.replace("\r", "\\r");
		literal.replace("\t", "\\t");
		
		return "\"" + literal + "\"";
	}
	
	public String processFieldName(Element element) {
		
		String literal = element.content;
		
		if(literal.length() > 0) {
			
			if(literal.startsWith("\""))
				literal = literal.substring(1);
		}
		
		if(literal.length() > 0) {
			
			if(literal.endsWith("\""))
				literal = literal.substring(0, literal.length() - 1);
		}

		literal.replace("\\", "\\\\");
		literal.replace("\"", "\\\"");
		literal.replace("\b", "\\b");
		literal.replace("\f", "\\f");
		literal.replace("\n", "\\n");
		literal.replace("\r", "\\r");
		literal.replace("\t", "\\t");
		
		return "\"" + literal + "\"";
	}
}