package stack.dialects;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import stack.dialects.cross.PHP;
import stack.utilities.Dialect;

public class HTML extends Dialect {
	
	public String build(
			ArrayList<ArrayList<Object>> functionDefintions,
			ArrayList<ArrayList<Object>> functions,
			ArrayList<String> arguments) {
		
		Element element = (Element) functions.get(0).get(1);
		
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
		
		return
				"<!DOCTYPE HTML><html><head>" +
				head +
				"</head><body>" +
				body +
				"</body></html>";
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
			return processResource(element, new CSS(), "<style>", "</style>");
		
		if(element.content.equalsIgnoreCase("PHP"))
			return processResource(element, new PHP(), "", "");
		
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
	
	public String processResource(Element element, Dialect dialect, String prefix, String postfix) {
		
		Element resource = ElementUtilities.copyElement(element);
		resource.content = null;
		
		ArrayList<ArrayList<Object>> functions = new ArrayList<ArrayList<Object>>();
		
		ArrayList<Object> function = new ArrayList<Object>();
		
		function.add("");
		function.add(resource);
		
		functions.add(function);
		
		return
				prefix +
				dialect.build(
						new ArrayList<ArrayList<Object>>(),
						functions,
						new ArrayList<String>()) +
				postfix;
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