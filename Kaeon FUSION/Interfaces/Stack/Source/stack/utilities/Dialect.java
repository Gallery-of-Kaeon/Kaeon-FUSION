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
		
		ArrayList<Element> arguments = null;
		ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();
		
		if(((String) packet.get(0)).equalsIgnoreCase("Build")) {
				
			ArrayList<ArrayList<Object>> functionDefinitions = (ArrayList<ArrayList<Object>>) packet.get(2);
			ArrayList<ArrayList<Object>> functions = (ArrayList<ArrayList<Object>>) packet.get(3);
			arguments = (ArrayList<Element>) packet.get(4);
			
			build(files, functionDefinitions, functions, arguments);
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Derive")) {
			
			ArrayList<String> code = (ArrayList<String>) packet.get(2);
			arguments = (ArrayList<Element>) packet.get(3);
			
			derive(files, code, arguments);
		}
		
		for(int i = 0; i < files.size(); i++) {
			
			IO.save(
					files.get(i).get(1),
					(arguments.size() > 0 ? arguments.get(0).content + "/" : "") +
							files.get(i).get(0));
		}
		
		return null;
	}
	
	public String getName() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
	}
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<ArrayList<Object>> functionDefintions,
			ArrayList<ArrayList<Object>> functions,
			ArrayList<Element> arguments) {
		
	}
	
	public void derive(
			ArrayList<ArrayList<String>> files,
			ArrayList<String> code,
			ArrayList<Element> arguments) {
		
	}
}