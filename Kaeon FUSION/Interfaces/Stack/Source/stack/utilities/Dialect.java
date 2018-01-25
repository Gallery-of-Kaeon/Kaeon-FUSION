package stack.utilities;

import java.util.ArrayList;

import io.IO;
import one.Element;
import philosophers_stone.PhilosophersStone;

public class Dialect extends PhilosophersStone {
	
	@SuppressWarnings({ "unchecked" })
	public Object onCall(ArrayList<Object> packet) {
		
		if(!((String) packet.get(0)).equalsIgnoreCase("Build") ||
				!((String) packet.get(1)).equalsIgnoreCase(getName())) {
			
			return null;
		}
		
		ArrayList<ArrayList<Object>> functionDefinitions = (ArrayList<ArrayList<Object>>) packet.get(2);
		ArrayList<ArrayList<Object>> functions = (ArrayList<ArrayList<Object>>) packet.get(3);
		ArrayList<String> arguments = (ArrayList<String>) packet.get(4);
		
		IO.save(
				build(functionDefinitions, functions, arguments),
				(arguments.size() > 0 ? arguments.get(0) + "/" : "") +
						((Element) functions.get(0).get(1)).content + '.' +
						getExtension());
		
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
			ArrayList<String> arguments) {
		
		return "";
	}
}