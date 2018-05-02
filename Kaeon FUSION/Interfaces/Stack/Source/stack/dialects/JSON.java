package stack.dialects;

import java.util.ArrayList;

import build_dialect.BuildDialect;
import one.Element;
import one.ElementUtilities;
import stack.utilities.json.containers.arrays.JSONArray;
import stack.utilities.json.containers.objects.JSONObject;

public class JSON extends BuildDialect {
	
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
		
		if(element.content.equalsIgnoreCase("Meta")) {
			
			String injection = getInjection(element);
			
			if(injection != null)
				return injection;
		}
		
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
	
	public void derive(
			ArrayList<ArrayList<String>> files,
			ArrayList<String> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
//		Element derive = new Element();
//		
//		JSONUnit json = new JSONUnit(code.get(0));
//		
//		for(int i = 0; i < json.getContainers().size(); i++)
//			ElementUtilities.addChild(derive, deriveValue(json.getContainers().get(i)));
//		
//		ArrayList<String> file = new ArrayList<String>();
//		
//		if(name == null) {
//			
//			name = "json";
//			
//			if(index > 0)
//				name += "_" + index;
//		}
//		
//		file.add(name + ".op");
//		file.add("" + derive);
//		
//		files.add(file);
	}
	
	public Element deriveValue(Object value) {
		
		Element derive = new Element();
		
		if(value instanceof JSONArray) {
			
			JSONArray array = (JSONArray) value;
			
			derive.content = "List";
			
			for(int i = 0; i < array.getValues().size(); i++)
				ElementUtilities.addChild(derive, deriveValue(array.getValue(i)));
		}
		
		else if(value instanceof JSONObject) {
			
			JSONObject object = (JSONObject) value;
			
			derive.content = "Object";
			
			for(int i = 0; i < object.getFields().size(); i++) {
				
				Element field = new Element();
				field.content = object.getField(i).getID();
				
				ElementUtilities.addChild(field, deriveValue(object.getField(i).getValue()));
				ElementUtilities.addChild(derive, field);
			}
		}
		
		else
			derive.content = "" + value;
		
		return derive;
	}
}