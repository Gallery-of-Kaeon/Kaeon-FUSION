package stack.dialects;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.Dialect;
import stack.utilities.parse.xml.XMLAttribute;
import stack.utilities.parse.xml.XMLElement;
import stack.utilities.parse.xml.XMLUnit;

public class XML extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		String xml = "";

		Element declaration = ElementUtilities.getChild(element, "Declaration");
		Element document = ElementUtilities.getChild(element, "Document");
		Element root = ElementUtilities.getChild(element, "Root");
		
		if(declaration != null)
			xml += buildDeclaration(declaration);
		
		if(document != null)
			xml += buildDocument(document);
		
		if(root != null)
			xml += buildElement(root.children.get(0));
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "data";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".xml");
		file.add(xml);
		
		files.add(file);
	}
	
	public String buildDeclaration(Element element) {
		
		Element encoding = ElementUtilities.getChild(element, "Encoding");
		Element standalone = ElementUtilities.getChild(element, "Standalone");
		
		return
				"<?xml version=\"1.0\"" +
				(encoding != null ? "encoding=\"" + encoding.children.get(0).content + "\"" : "") +
				(standalone != null ? "standalone=\"" + standalone.children.get(0).content + "\"" : "") +
				"?>";
	}
	
	public String buildDocument(Element element) {
		return element.children.get(0).content;
	}
	
	public String buildElement(Element element) {
		
		String xml = "<" + buildLiteral(element) + " ";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Attributes")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++) {
					
					xml +=
							buildLiteral(element.children.get(i).children.get(j)) +
							"=\"" +
							buildLiteral(element.children.get(i).children.get(j).children.get(0)) +
							"\"";
				}
			}
		}
		
		xml += ">";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Data")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++)
					xml += buildLiteral(element.children.get(i).children.get(j));
			}
			
			if(element.children.get(i).content.equalsIgnoreCase("Children")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++)
					xml += buildElement(element.children.get(i).children.get(j));
			}
		}
		
		return xml + "</" + buildLiteral(element) + ">";
	}
	
	public String buildLiteral(Element element) {
		
		String literal = element.content;
		
		literal.replace("&", "&amp;");
		literal.replace("<", "&lt;");
		literal.replace(">", "&gt;");
		literal.replace("\"", "&quot;");
		literal.replace("\'", "&apos;");
		
		return literal;
	}
	
	public void derive(
			ArrayList<ArrayList<String>> files,
			ArrayList<String> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element derive = new Element();
		
		XMLUnit xml = new XMLUnit(code.get(0));
		
		Object header = (Object) xml.getHeader();
		
		if(header != null)
			ElementUtilities.addChild(derive, deriveHeader((XMLElement) header));
		
		Element root = new Element();
		root.content = "Root";
		
		for(int i = 0; i <= xml.getElements().size() && i == 0; i++)
			ElementUtilities.addChild(root, deriveElement(xml.getElements().get(0)));
		
		ElementUtilities.addChild(derive, root);
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "xml";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".op");
		file.add("" + derive);
		
		files.add(file);
	}
	
	public Element deriveHeader(XMLElement header) {
		
		Element derive = new Element();
		derive.content = "Declaration";
		
		for(int i = 0; i < header.getAttributes().size(); i++)
			ElementUtilities.addChild(derive, deriveAttribute(header.getAttribute(i)));
		
		return derive;
	}
	
	public Element deriveElement(XMLElement element) {
		
		Element derive = new Element();
		derive.content = element.getID();
		
		Element attributes = new Element();
		attributes.content = "Attributes";
		
		for(int i = 0; i < element.getAttributes().size(); i++)
			ElementUtilities.addChild(attributes, deriveAttribute(element.getAttribute(i)));
		
		if(attributes.children.size() > 0)
			ElementUtilities.addChild(derive, attributes);
		
		String currentType = null;
		Element currentElement = null;
		
		for(int i = 0; i < element.getData().size(); i++) {
			
			if(element.getData().get(i) instanceof XMLElement) {
				
				boolean newElement = false;
				
				if(currentType == null)
					newElement = true;
				
				else if(currentType.equalsIgnoreCase("String"))
					newElement = true;
				
				if(newElement) {
					
					currentType = "Element";
					
					currentElement = new Element();
					currentElement.content = "Children";
					
					ElementUtilities.addChild(
							derive,
							currentElement);
				}
				
				ElementUtilities.addChild(
						currentElement,
						deriveElement((XMLElement) element.getData().get(i)));
			}
			
			else if(element.getData().get(i) instanceof String) {
				
				boolean newElement = false;
				
				if(currentType == null)
					newElement = true;
				
				else if(currentType.equalsIgnoreCase("Element"))
					newElement = true;
				
				if(newElement) {
					
					currentType = "String";
					
					currentElement = new Element();
					currentElement.content = "Data";
					
					ElementUtilities.addChild(
							derive,
							currentElement);
				}
				
				Element data = new Element();
				data.content = (String) element.getData().get(i);
				
				ElementUtilities.addChild(
						currentElement,
						data);
			}
		}
		
		return derive;
	}
	
	public Element deriveAttribute(XMLAttribute attribute) {
		
		Element derive = new Element();
		derive.content = attribute.getID();
		
		Element content = new Element();
		content.content = attribute.getContent();
		
		ElementUtilities.addChild(derive, content);
		
		return derive;
	}
}