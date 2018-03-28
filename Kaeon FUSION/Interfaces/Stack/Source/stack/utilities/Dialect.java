package stack.utilities;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

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
		
		String filePath = "";
		
		if(arguments.size() > 0) {
			
			if(arguments.get(0) != null)
				filePath = "" + arguments.get(0);
			
			arguments.remove(0);
		}
		
		ArrayList<Object> names = new ArrayList<Object>();
		
		if(arguments.size() > 0) {
			
			try {
				names = (ArrayList<Object>) arguments.get(0);
			}
			
			catch(Exception exception) {
				
			}
			
			arguments.remove(0);
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Build")) {
			
			ArrayList<Element> codeElements = new ArrayList<Element>();
			
			for(int i = 0; i < code.size(); i++)
				codeElements.add(ONEPlus.parseONEPlus("" + code.get(i)));
			
			ArrayList<ArrayList<Element>> groups = getBuildGroups(codeElements);
			
			for(int i = 0; i < groups.size(); i++)
				build(files, groups.get(i), getGroupName(names, i), i, arguments);
		}
		
		else {
			
			ArrayList<String> codeStrings = new ArrayList<String>();
			
			for(int i = 0; i < code.size(); i++)
				codeStrings.add("" + code.get(i));
			
			ArrayList<ArrayList<String>> groups = getDeriveGroups(codeStrings);
			
			for(int i = 0; i < groups.size(); i++)
				derive(files, groups.get(i), getGroupName(names, i), i, arguments);
		}
		
		String workspace = "";
		
		try {
			workspace = "" + PhilosophersStoneUtilities.call(this, "Get Build Workspace").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		for(int i = 0; i < files.size(); i++)
			IO.save(files.get(i).get(1), workspace + filePath + files.get(i).get(0));
		
		return null;
	}
	
	public String getName() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
	}
	
	public ArrayList<ArrayList<Element>> getBuildGroups(ArrayList<Element> items) {
		
		ArrayList<ArrayList<Element>> groups = new ArrayList<ArrayList<Element>>();
		
		for(int i = 0; i < items.size(); i++) {
			
			ArrayList<Element> group = new ArrayList<Element>();
			group.add(items.get(i));
			
			groups.add(group);
		}
		
		return groups;
	}
	
	public ArrayList<ArrayList<String>> getDeriveGroups(ArrayList<String> items) {
		
		ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < items.size(); i++) {
			
			ArrayList<String> group = new ArrayList<String>();
			group.add(items.get(i));
			
			groups.add(group);
		}
		
		return groups;
	}
	
	public String getGroupName(ArrayList<Object> names, int index) {
		
		if(index < names.size())
			return "" + names.get(index);
		
		return null;
	}
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
	}
	
	public void derive(
			ArrayList<ArrayList<String>> files,
			ArrayList<String> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
	}
}