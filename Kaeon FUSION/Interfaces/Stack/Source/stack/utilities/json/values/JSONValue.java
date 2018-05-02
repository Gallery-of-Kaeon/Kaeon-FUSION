package stack.utilities.json.values;

import stack.utilities.json.containers.arrays.JSONArray;
import stack.utilities.json.containers.objects.JSONObject;

public class JSONValue {

	public static String TYPE_NULL = "TYPE_NULL";
	
	public static String TYPE_STRING = "TYPE_STRING";

	public static String TYPE_NUMBER = "TYPE_NUMBER";
	public static String TYPE_BOOLEAN = "TYPE_BOOLEAN";
	
	public static String TYPE_OBJECT = "TYPE_OBJECT";
	public static String TYPE_ARRAY = "TYPE_ARRAY";
	
	private Object value;
	private String type;
	
	public JSONValue() {
		value = null;
		type = TYPE_NULL;
	}
	
	public JSONValue(Object value) {
		setValue(value);
	}
	
	public void setValue(Object value) {
		
		assignType(value);
		
		if(type != null)
			this.value = value;
		
		else {
			value = null;
			type = TYPE_NULL;
		}
	}
	
	private void assignType(Object value) {
		
		if(value == null)
			type = TYPE_NULL;
		
		else if(value instanceof String)
			type = TYPE_STRING;
		
		else if(value instanceof Double)
			type = TYPE_NUMBER;
		
		else if(value instanceof Boolean)
			type = TYPE_BOOLEAN;
		
		else if(value instanceof JSONObject)
			type = TYPE_OBJECT;
		
		else if(value instanceof JSONArray)
			type = TYPE_ARRAY;
		
		else
			type = null;
	}
	
	public Object getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}
}