package stack.utilities.json.containers.objects;

import stack.utilities.json.values.JSONValue;

public class JSONObjectField {
	
	private String id;
	private JSONValue value;
	
	public JSONObjectField() {
		
	}
	
	public JSONObjectField(String id, JSONValue value) {
		setID(id);
		setValue(value);
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setValue(JSONValue value) {
		this.value = value;
	}
	
	public String getID() {
		return id;
	}
	
	public JSONValue getValue() {
		return value;
	}
}