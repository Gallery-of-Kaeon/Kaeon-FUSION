package stack.utilities;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;

public class Dialect extends PhilosophersStone {
	
	@SuppressWarnings({ "unchecked" })
	public Object onCall(ArrayList<Object> packet) {
		
		if(!(((String) packet.get(0)).equalsIgnoreCase("Build") ||
				((String) packet.get(0)).equalsIgnoreCase("Derive")) ||
				!((String) packet.get(1)).equalsIgnoreCase(getName())) {
			
			return null;
		}
		
		ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();
		
		ArrayList<Object> code = (ArrayList<Object>) packet.get(2);
		ArrayList<Object> arguments = (ArrayList<Object>) packet.get(3);
		
		if(((String) packet.get(0)).equalsIgnoreCase("Build")) {
			
			ArrayList<Element> codeElements = new ArrayList<Element>();
			
			for(int i = 0; i < code.size(); i++)
				codeElements.add(ONEPlus.parseONEPlus("" + code.get(i)));
			
			build(files, codeElements, arguments);
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Derive")) {
			
			ArrayList<String> codeStrings = new ArrayList<String>();
			
			for(int i = 0; i < code.size(); i++)
				codeStrings.add("" + code.get(i));
			
			derive(files, codeStrings, arguments);
		}
		
		for(int i = 0; i < files.size(); i++) {
			
			IO.save(
					files.get(i).get(1),
					(arguments.size() > 0 ? arguments.get(0) + "/" : "") +
							files.get(i).get(0));
		}
		
		return null;
	}
	
	public String getName() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
	}
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			ArrayList<Object> arguments) {
		
	}
	
	public void derive(
			ArrayList<ArrayList<String>> files,
			ArrayList<String> code,
			ArrayList<Object> arguments) {
		
	}
}