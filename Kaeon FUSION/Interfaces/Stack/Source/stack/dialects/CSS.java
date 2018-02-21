package stack.dialects;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import stack.utilities.Dialect;

public class CSS extends Dialect {

	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		Element tags = ONEPlus.parseONEPlus(IO.openAsString("Tags.op"));
		
		String css = "";
		
		for(int i = 0; i < element.children.size(); i++)
			css += buildElement(element.children.get(i), tags);
		
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
			
			else
				domain += ElementUtilities.getChild(tags, element.children.get(i).content).children.get(0).content;
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