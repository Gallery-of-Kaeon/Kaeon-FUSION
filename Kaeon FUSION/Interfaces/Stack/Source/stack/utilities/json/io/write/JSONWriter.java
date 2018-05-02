package stack.utilities.json.io.write;

import stack.utilities.json.JSONUnit;
import stack.utilities.json.containers.JSONContainer;
import stack.utilities.json.containers.arrays.JSONArray;
import stack.utilities.json.containers.objects.JSONObject;
import stack.utilities.json.containers.objects.JSONObjectField;
import stack.utilities.json.values.JSONValue;

public class JSONWriter {
	
	public static String writeJSON(JSONUnit json) {
		
		String jsonMarkup = "";
		
		for(int i = 0; i < json.getContainers().size(); i++) {
			
			if(i > 0)
				jsonMarkup += '\n';
			
			jsonMarkup += writeJSONContainer(json.getContainer(i));
		}
		
		return jsonMarkup;
	}
	
	public static String writeJSONContainer(JSONContainer container) {
		
		if(container instanceof JSONObject)
			return writeJSONObject((JSONObject) container, 0);
		
		else
			return writeJSONArray((JSONArray) container, 0);
	}
	
	public static String writeJSONObject(JSONObject object, int indent) {
		
		String jsonMarkup = indentLine(indent) + "{\n";
		
		for(int i = 0; i < object.getFields().size(); i++) {
			
			if(i > 0)
				jsonMarkup += ",\n";
			
			jsonMarkup += writeJSONObjectField(object.getField(i), indent);
		}
		
		jsonMarkup += '\n' + indentLine(indent) + '}';
		
		return jsonMarkup;
	}
	
	public static String writeJSONObjectField(JSONObjectField field, int indent) {
		return indentLine(indent + 1) + writeJSONString(field.getID()) + ": " + writeJSONValue(field.getValue(), indent);
	}
	
	public static String writeJSONArray(JSONArray array, int indent) {
		
		String jsonMarkup = indentLine(indent) + "[\n";
		
		for(int i = 0; i < array.getValues().size(); i++) {
			
			if(i > 0)
				jsonMarkup += ",\n";
			
			jsonMarkup += indentLine(indent + 1) + writeJSONValue(array.getValue(i), indent + 1);
		}
		
		jsonMarkup += '\n' + indentLine(indent) + ']';
		
		return jsonMarkup;
	}
	
	public static String writeJSONValue(JSONValue value, int indent) {

		if(value.getType().equals(JSONValue.TYPE_OBJECT))
			return '\n' + writeJSONObject((JSONObject) value.getValue(), indent + 2);

		else if(value.getType().equals(JSONValue.TYPE_ARRAY))
			return '\n' + writeJSONArray((JSONArray) value.getValue(), indent + 2);

		else if(value.getType().equals(JSONValue.TYPE_STRING))
			return writeJSONString((String) value.getValue());

		else
			return "" + value.getValue();
	}
	
	public static String writeJSONString(String string) {
		
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) == '\b')
				string = string.substring(0, i) + "\\b" + string.substring(i + 1);
			
			if(string.charAt(i) == '\f')
				string = string.substring(0, i) + "\\f" + string.substring(i + 1);
			
			if(string.charAt(i) == '\n')
				string = string.substring(0, i) + "\\n" + string.substring(i + 1);
			
			if(string.charAt(i) == '\r')
				string = string.substring(0, i) + "\\r" + string.substring(i + 1);
			
			if(string.charAt(i) == '\t')
				string = string.substring(0, i) + "\\t" + string.substring(i + 1);
		}
		
		return '"' + string + '"';
	}
	
	public static String indentLine(int indent) {
		
		String indentLine = "";
		
		for(int i = 0; i < indent; i++)
			indentLine += '\t';
		
		return indentLine;
	}
}