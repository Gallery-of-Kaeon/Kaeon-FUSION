package stack.utilities.json.io.read;

import java.util.ArrayList;

import stack.utilities.json.JSONUnit;
import stack.utilities.json.containers.arrays.JSONArray;
import stack.utilities.json.containers.objects.JSONObject;
import stack.utilities.json.containers.objects.JSONObjectField;
import stack.utilities.json.values.JSONValue;

public class JSONReader {
	
	public static void readJSON(JSONUnit json, ArrayList<String> file) {
		pushContainers(json, new FileScanner(file));
	}
	
	private static void pushContainers(JSONUnit json, FileScanner file) {
		
		char character = file.popNextCharacter();

		while(file.hasNext()) {
			
			if(character == '{')
				json.addContainer(pushObject(file));
			
			else if(character == '[')
				json.addContainer(pushArray(file));
		}
	}
	
	private static JSONObject pushObject(FileScanner file) {
		
		JSONObject object = new JSONObject();
		
		while(true) {
			
			char character = file.popNextCharacter();
			
			if(character == '}')
				return object;
			
			else if(character == '\"')
				object.addField(pushField(file));
		}
	}
	
	private static JSONObjectField pushField(FileScanner file) {
		
		JSONObjectField field = new JSONObjectField();
		
		field.setID(pushString(file));
		field.setValue(pushValue(file));
		
		return field;
	}
	
	private static JSONArray pushArray(FileScanner file) {
		
		JSONArray array = new JSONArray();
		
		while(true) {
			
			char character = file.nextCharacter();
			
			if(character == ']')
				return array;
			
			else
				array.addValue(pushValue(file));
		}
	}
	
	private static JSONValue pushValue(FileScanner file) {
		
		while(true) {
			
			char character = file.nextCharacter();
			
			if(character == '{') {
				file.popNextCharacter();
				return new JSONValue(pushObject(file));
			}
			
			if(character == '[') {
				file.popNextCharacter();
				return new JSONValue(pushArray(file));
			}
			
			if(character == '\"') {
				file.popNextCharacter();
				return new JSONValue(pushString(file));
			}
			
			if(Character.isDigit(character)) {
				return new JSONValue(pushNumber(file));
			}
			
			if(character == 't') {
				file.popNextCharacter(3);
				return new JSONValue(new Boolean(true));
			}
			
			if(character == 'f') {
				file.popNextCharacter(4);
				return new JSONValue(new Boolean(false));
			}
			
			if(character == 'n') {
				file.popNextCharacter(3);
				return new JSONValue(new Boolean(null));
			}
			
			file.popNextCharacter();
		}
	}
	
	private static String pushString(FileScanner file) {
		
		String string = "";
		
		while(true) {
			
			char character = file.popNextCharacter();
			
			if(character == '\\') {
				
				char escapeCharacter = file.popNextCharacter();
				
				if(escapeCharacter == '\"')
					string += '\"';
				
				if(escapeCharacter == '\\')
					string += '\\';
				
				if(escapeCharacter == '/')
					string += '/';
				
				if(escapeCharacter == 'b')
					string += '\b';
				
				if(escapeCharacter == 'f')
					string += '\f';
				
				if(escapeCharacter == 'n')
					string += '\n';
				
				if(escapeCharacter == 'r')
					string += '\r';
				
				if(escapeCharacter == 't')
					string += '\t';
				
				if(escapeCharacter == 'u') {
					
				}
			}
			
			else if(character == '\"')
				return string;
			
			else
				string += character;
		}
	}
	
	private static Double pushNumber(FileScanner file) {
		
		String number = "";
		
		while(true) {
			
			char character = file.nextCharacter();
			
			if(!Character.isDigit(character)
					&& character != '.'
					&& character != 'e'
					&& character != 'E'
					&& character != '-'
					&& character != '+') {
				
				break;
			}
			
			else
				number += file.popNextCharacter();
		}
		
		return Double.parseDouble(number);
	}
}