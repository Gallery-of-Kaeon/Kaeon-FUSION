package stack.dialects;

import java.io.File;
import java.util.ArrayList;

import build_dialect.BuildDialect;
import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;

public class CSS extends BuildDialect {

	@SuppressWarnings("unchecked")
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		ArrayList<String> workspace = new ArrayList<String>();
		
		workspace.add("");
		
		ArrayList<Object> workspaces =
				PhilosophersStoneUtilities.call(this, "Get Workspace");
		
		for(int i = 0; i < workspaces.size(); i++) {
			
			try {
				workspace.addAll((ArrayList<String>) workspaces.get(i));
			}
			
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		
		Element element = code.get(0);
		
		Element tags = ElementUtilities.createElement("Tags");
		
		ArrayList<Element> use = ElementUtilities.getChildren(element, "Use");
		
		for(int i = 0; i < workspace.size(); i++) {
			
			for(int j = 0; j < use.size(); j++) {
				
				try {
					
					String path = workspace.get(i) + use.get(j).children.get(0).content;
					
					if(new File(path).exists()) {
						
						ElementUtilities.addChildren(
								tags,
								ElementUtilities.getChild(
										ONEPlus.parseONEPlus(
												IO.openAsString(path)),
										"Tags").children);
					}
				}
				
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		
		String css = "";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(element.children.get(i));
				
				if(injection != null)
					css += injection;
			}
			
			else if(element.children.get(i).content.equalsIgnoreCase("Style"))
				css += buildElement(element.children.get(i), tags);
		}
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "style";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".css");
		file.add(css);
		
		files.add(file);
	}
	
	public String buildElement(Element element, Element tags) {
		
		return
				buildDomain(ElementUtilities.getChild(element, "Domain"), tags) +
				"{" +
				buildData(ElementUtilities.getChild(element, "Data")) +
				"}";
	}
	
	public String buildDomain(Element element, Element tags) {
		
		String domain = "";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("On"))
				domain += "#" + element.children.get(i).children.get(0).content;
			
			else if(element.children.get(i).content.equalsIgnoreCase("For"))
				domain += "." + element.children.get(i).children.get(0).content;
			
			else {
				
				domain +=
						ElementUtilities.getChild(
								tags,
								element.children.get(i).content).children.get(0).content;
			}
		}
		
		return domain;
	}
	
	public String buildData(Element element) {
		
		String data = "";
		
		for(int i = 0; i < element.children.size(); i++)
			data += buildDataElement(element.children.get(i));
		
		return data;
	}
	
	public String buildDataElement(Element element) {
		return element.content + ":" + element.children.get(0).content + ";";
	}
}