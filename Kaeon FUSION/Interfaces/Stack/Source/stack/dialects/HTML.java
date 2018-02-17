package stack.dialects;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import stack.dialects.cross.PHP;
import stack.utilities.Dialect;

public class HTML extends Dialect {
	
	@SuppressWarnings("unchecked")
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		Element tags = ONEPlus.parseONEPlus(IO.openAsString("Tags.op"));
		Element attributes = ONEPlus.parseONEPlus(IO.openAsString("Attributes.op"));
		
		String head = "";
		String body = "";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Head"))
				head += processHead(element.children.get(i), tags, attributes);
			
			else
				body += processItem(element.children.get(i), tags, attributes);
		}
		
		String html =
				"<!DOCTYPE HTML><html><head>" +
				head +
				"</head><body>" +
				body +
				"</body></html>";
		
		ArrayList<String> file = new ArrayList<String>();
		
		String name = "index";
		
		if(arguments.size() >= 1) {
			
			ArrayList<Object> fileNames = (ArrayList<Object>) arguments.get(1);
			
			if(fileNames.size() > 0) {
				
				if(fileNames.get(0) != null)
					name = "" + fileNames.get(0);
			}
		}
		
		file.add(name + ".html");
		file.add(html);
		
		files.add(file);
	}
	
	public String processHead(
			Element element,
			Element tags,
			Element attributes) {
		
		String head = "";
		
		for(int i = 0; i < element.children.size(); i++)
			head += processItem(element.children.get(i), tags, attributes);
		
		return head;
	}
	
	public String processItem(
			Element element,
			Element tags,
			Element attributes) {
		
		if(element.content.equalsIgnoreCase("Style"))
			return "<style>" + processResource(element, new CSS()) + "</style>";
		
		if(element.content.equalsIgnoreCase("PHP"))
			return processResource(element, new PHP());
		
		if(ElementUtilities.hasChild(tags, element.content))
			return processTag(element, tags, attributes);
		
		if(ElementUtilities.hasChild(attributes, element.content))
			return processAttribute(element, attributes);
		
		return element.content;
	}
	
	public String processTag(
			Element element,
			Element tags,
			Element attributes) {

		boolean heading = element.content.equalsIgnoreCase("Heading");
		
		String tagName = "";
		
		if(heading)
			tagName = "h" + ElementUtilities.getChild(element, "Level").children.get(0).content;
		
		else
			tagName = ElementUtilities.getChild(tags, element.content).children.get(0).content;
		
		String tag = "<" + tagName;
		
		ArrayList<String> tagAttributes = new ArrayList<String>();
		ArrayList<String> tagItems = new ArrayList<String>();
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(heading && element.children.get(i).content.equalsIgnoreCase("Level"))
				continue;
			
			String item = processItem(element.children.get(i), tags, attributes);
			
			if(ElementUtilities.hasChild(attributes, element.children.get(i).content))
				tagAttributes.add(item);
			
			else
				tagItems.add(item);
		}
		
		for(int i = 0; i < tagAttributes.size(); i++)
			tag += " " + tagAttributes.get(i);
		
		tag += ">";
		
		for(int i = 0; i < tagItems.size(); i++)
			tag += tagItems.get(i);
		
		return tag + "</" + tagName + ">";
	}
	
	public String processResource(Element element, Dialect dialect) {
		
		ArrayList<Element> code = new ArrayList<Element>();
		
		Element resource = ElementUtilities.copyElement(element);
		resource.content = null;
		
		code.add(resource);
		
		ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();
		
		dialect.build(
				files,
				code,
				new ArrayList<Object>());
		
		return files.get(0).get(1);
	}
	
	public String processAttribute(
			Element element,
			Element attributes) {
		
		return
				ElementUtilities.getChild(attributes, element.content).children.get(0).content +
				"=\"" +
				element.children.get(0).content +
				"\"";
	}
}