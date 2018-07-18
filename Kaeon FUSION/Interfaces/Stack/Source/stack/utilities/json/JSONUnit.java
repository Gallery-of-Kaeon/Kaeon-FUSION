package stack.utilities.json;

import java.util.ArrayList;

import stack.utilities.json.containers.JSONContainer;
import stack.utilities.json.io.read.JSONReader;
import stack.utilities.json.io.write.JSONWriter;

public class JSONUnit {
	
	private ArrayList<JSONContainer> containers;
	
	public JSONUnit() {
		containers = new ArrayList<JSONContainer>();
	}
	
	public JSONUnit(String json) {
		
		this();
		
		ArrayList<String> jsonList = new ArrayList<String>();
		String currentString = "";
		
		for(int i = 0; i < json.length(); i++) {
			
			if(json.charAt(i) != '\n')
				currentString += json.charAt(i);
			
			if(json.charAt(i) == '\n' || i == json.length() - 1) {
				jsonList.add(currentString);
				currentString = "";
			}
		}
		
		JSONReader.readJSON(this, jsonList);
	}
	
	public JSONUnit(ArrayList<String> json) {
		
		this();
		
		JSONReader.readJSON(this, json);
	}
	
	public void addContainer(JSONContainer container) {
		containers.add(container);
	}
	
	public ArrayList<JSONContainer> getContainers() {
		return containers;
	}
	
	public JSONContainer getContainer(int index) {
		return containers.get(index);
	}
	
	public JSONContainer removeContainer(int index) {
		return containers.remove(index);
	}
	
	public String toString() {
		return JSONWriter.writeJSON(this);
	}
}