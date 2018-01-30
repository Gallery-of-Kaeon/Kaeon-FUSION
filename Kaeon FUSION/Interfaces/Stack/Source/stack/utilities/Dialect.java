package stack.utilities;

import java.util.ArrayList;

import io.IO;
import one.Element;
import philosophers_stone.PhilosophersStone;

public class Dialect extends PhilosophersStone {
	
	@SuppressWarnings({ "unchecked" })
	public Object onCall(ArrayList<Object> packet) {
		
		if(!(((String) packet.get(0)).equalsIgnoreCase("Build") ||
				((String) packet.get(0)).equalsIgnoreCase("Derive")) ||
				!((String) packet.get(1)).equalsIgnoreCase(getName())) {
			
			return null;
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Build")) {
			
			ArrayList<ArrayList<Object>> functionDefinitions = (ArrayList<ArrayList<Object>>) packet.get(2);
			ArrayList<ArrayList<Object>> functions = (ArrayList<ArrayList<Object>>) packet.get(3);
			ArrayList<Element> arguments = (ArrayList<Element>) packet.get(4);
			
			IO.save(
					build(functionDefinitions, functions, arguments),
					(arguments.size() > 0 ? arguments.get(0).content + "/" : "") +
							((Element) functions.get(0).get(1)).content +
							'.' +
							getExtension());
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Derive")) {
			
			ArrayList<String> code = (ArrayList<String>) packet.get(2);
			ArrayList<Element> arguments = (ArrayList<Element>) packet.get(3);
			
			IO.save(
					"" + derive(code, arguments),
					(arguments.size() > 1 ? arguments.get(1).content + "/" : "") +
						(arguments.size() > 0 ? arguments.get(0).content : "code") +
						".op");
		}
		
		return null;
	}
	
	public String getName() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
	}
	
	public String getExtension() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1).toLowerCase();
	}
	
	public String build(
			ArrayList<ArrayList<Object>> functionDefintions,
			ArrayList<ArrayList<Object>> functions,
			ArrayList<Element> arguments) {
		
		return "";
	}
	
	public Element derive(
			ArrayList<String> code,
			ArrayList<Element> arguments) {
		
		return new Element();
	}
}