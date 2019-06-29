package build_dialect;

import java.util.ArrayList;
import java.util.Arrays;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class BuildDialect extends PhilosophersStone {
	
	@SuppressWarnings({ "unchecked" })
	public Object onCall(ArrayList<Object> packet) {
		
		if(!(((String) packet.get(0)).equalsIgnoreCase("Build") ||
				((String) packet.get(0)).equalsIgnoreCase("Derive")) ||
				!((String) packet.get(1)).equalsIgnoreCase(getName())) {
			
			return null;
		}
		
		ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();

		ArrayList<Object> code = (ArrayList<Object>) packet.get(2);
		
		ArrayList<Object> arguments =
				packet.size() > 3 ?
						(ArrayList<Object>) packet.get(3) :
						new ArrayList<Object>();
		
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
		
		if(filePath.length() == 0) {
			
			try {
				workspace = "" + PhilosophersStoneUtilities.call(this, "Get Build Workspace").get(0);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		boolean export = true;
		
		for(int i = 0; i < arguments.size() && export; i++) {
			
			if(("" + arguments.get(i)).equalsIgnoreCase("Return"))
				export = false;
		}
		
		if(export) {
			
			for(int i = 0; i < files.size(); i++)
				IO.save(files.get(i).get(1), workspace + filePath + files.get(i).get(0));
		}
		
		return files;
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
	
	@SuppressWarnings("unchecked")
	public String getInjection(Element meta) {
		
		try {
			
			Element injection = ElementUtilities.getChild(meta, "Injection");
			Element use = ElementUtilities.getChild(meta, "Use");
			
			if(injection != null)
				return injection.children.get(0).content;
			
			if(use != null) {
				
				String dialect = ElementUtilities.getChild(use, "Dialect").children.get(0).content;
				
				Element codeElement = ElementUtilities.copyElement(ElementUtilities.getChild(use, "Code"));
				codeElement.content = "";
				
				ArrayList<Object> code = new ArrayList<Object>(Arrays.asList(codeElement));
				
				ArrayList<Object> arguments = new ArrayList<Object>();
				
				if(ElementUtilities.hasChild(use, "Arguments"))
					arguments = new ArrayList<Object>(ElementUtilities.getChild(use, "Arguments").children);

				arguments.add("");
				arguments.add(new ArrayList<Object>());
				arguments.add("Return");
				
				ArrayList<ArrayList<String>> files =
						(ArrayList<ArrayList<String>>)
							PhilosophersStoneUtilities.call(this,
									"Build",
									dialect,
									code,
									arguments).get(0);
				
				return files.get(0).get(1);
			}
		}
		
		catch(Exception exception) {
			
		}
		
		return null;
	}
}