package stack.utilities.json.containers.objects;

import java.util.ArrayList;

import stack.utilities.json.containers.JSONContainer;

public class JSONObject extends JSONContainer {

	private ArrayList<JSONObjectField> fields;
	
	public JSONObject() {
		fields = new ArrayList<JSONObjectField>();
	}
	
	public void addField(JSONObjectField field) {
		fields.add(field);
	}
	
	public ArrayList<JSONObjectField> getFields() {
		return fields;
	}
	
	public JSONObjectField getField(String id) {
		
		for(int i = 0; i < fields.size(); i++) {
			
			if(id.equals(fields.get(i).getID()))
				return fields.get(i);
		}
		
		return null;
	}
	
	public JSONObjectField getField(int i) {
		
		if(i < fields.size())
			return fields.get(i);
		
		return null;
	}
}