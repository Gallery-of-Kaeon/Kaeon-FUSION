package stack.utilities.json.containers.arrays;

import java.util.ArrayList;

import stack.utilities.json.containers.JSONContainer;
import stack.utilities.json.values.JSONValue;

public class JSONArray extends JSONContainer {
	
	private ArrayList<JSONValue> values;
	
	public JSONArray() {
		values = new ArrayList<JSONValue>();
	}
	
	public void addValue(JSONValue value) {
		values.add(value);
	}
	
	public ArrayList<JSONValue> getValues() {
		return values;
	}
	
	public JSONValue getValue(int i) {
		
		if(i < values.size())
			return values.get(i);
		
		return null;
	}
	
	public JSONValue removeValue(int i) {
		
		if(i < values.size())
			return values.remove(i);
		
		return null;
	}
}