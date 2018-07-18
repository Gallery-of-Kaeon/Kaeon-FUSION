package stack.dialects;

import java.io.File;
import java.util.ArrayList;

import build_dialect.BuildDialect;
import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;
import stack.utilities.xml.XMLAttribute;
import stack.utilities.xml.XMLElement;
import stack.utilities.xml.XMLUnit;

public class XML extends BuildDialect {
	
	@SuppressWarnings("unchecked")
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		boolean html = false;
		
		for(int i = 0; i < arguments.size(); i++) {
			
			if(("" + arguments.get(i)).equalsIgnoreCase("HTML")) {
				
				html = true;
				
				break;
			}
		}
		
		ArrayList<String> workspace = new ArrayList<String>();
		
		workspace.add("");
		
		ArrayList<Object> workspaces = PhilosophersStoneUtilities.call(this, "Get Workspace");
		
		for(int i = 0; i < workspaces.size(); i++) {
			
			try {
				workspace.addAll((ArrayList<String>) workspaces.get(i));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		Element element = code.get(0);
		
		Element tags = new Element();
		Element attributes = new Element();

		ArrayList<Element> use = ElementUtilities.getChildren(element, "Use");
		
		if(html) {
			
			Element useHTML = ElementUtilities.createElement("Use");
			ElementUtilities.addChild(useHTML, ElementUtilities.createElement("HTML.op"));
			
			use.add(useHTML);
		}
		
		for(int i = 0; i < use.size(); i++) {
			
			for(int j = 0; j < use.get(i).children.size(); j++) {
				
				String path = null;
				
				for(int k = 0; k < workspace.size(); k++) {
					
					if(new File(workspace.get(k) + use.get(i).children.get(j).content).exists()) {
						
						path = workspace.get(j) + use.get(i).children.get(j).content;
						
						break;
					}
				}
				
				if(path != null) {
					
					Element useData = ONEPlus.parseONEPlus(IO.openAsString(path));
					
					ArrayList<Element> tagData = ElementUtilities.getChildren(useData, "Tags");
					
					for(int k = 0; k < tagData.size(); k++)
						ElementUtilities.addChildren(tags, tagData.get(k).children);
					
					ArrayList<Element> attributeData = ElementUtilities.getChildren(useData, "Attributes");
					
					for(int k = 0; k < attributeData.size(); k++)
						ElementUtilities.addChildren(attributes, attributeData.get(k).children);
				}
			}
		}
		
		String xml = "";

		Element declaration = ElementUtilities.getChild(element, "Declaration");
		Element document = ElementUtilities.getChild(element, "Document");
		
		Element root = null;
		
		if(declaration != null)
			xml += buildDeclaration(declaration);
		
		if(document != null)
			xml += buildDocument(document);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(element.children.get(i));
				
				if(injection != null)
					xml += injection;
			}
			
			else if(!element.children.get(i).content.equalsIgnoreCase("Use") &&
					!element.children.get(i).content.equalsIgnoreCase("Declaration") &&
					!element.children.get(i).content.equalsIgnoreCase("Document")) {

				if(root == null) {
					
					root = element.children.get(i);
					
					xml += buildElement(root, tags, attributes);
				}
			}
		}
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "data";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + (html ? ".html" : ".xml"));
		
		file.add(html ? "<!DOCTYPE html>" + xml : xml);
		
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
	
	public String buildElement(Element element, Element tags, Element attributes) {
		
		String xml = "";
		
		if(ElementUtilities.hasChild(tags, element.content))
			xml += "<" + ElementUtilities.getChild(tags, element.content).children.get(0).content + " ";
		
		else
			xml += "<" + buildLiteral(element) + " ";
		
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
			
			if(ElementUtilities.hasChild(attributes, element.children.get(i).content)) {
				
				xml +=
						ElementUtilities.getChild(attributes, element.children.get(i).content).children.get(0).content +
						"=\"" +
						element.children.get(i).children.get(0).content +
						"\"";
			}
		}
		
		xml += ">";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(element.children.get(i));
				
				if(injection != null)
					xml += injection;
			}
			
			else if(element.children.get(i).content.equalsIgnoreCase("Children")) {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++)
					xml += buildElement(element.children.get(i).children.get(j), tags, attributes);
			}
			
			else if(ElementUtilities.hasChild(tags, element.children.get(i).content))
				xml += buildElement(element.children.get(i), tags, attributes);
			
			else if(!ElementUtilities.hasChild(attributes, element.children.get(i).content))
				xml += buildLiteral(element.children.get(i));
		}
		
		if(ElementUtilities.hasChild(tags, element.content))
			xml += "</" + ElementUtilities.getChild(tags, element.content).children.get(0).content + ">";
		
		else
			xml += "</" + buildLiteral(element) + ">";
		
		return xml;
	}
	
	public String buildLiteral(Element element) {
		
		String literal = element.content;
		
		if(literal.startsWith("\"") && literal.endsWith("\"") && literal.length() >= 2)
			literal = literal.substring(1, literal.length() - 1);
		
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
				
				Element data = new Element();
				data.content = (String) element.getData().get(i);
				
				if(data.content.equalsIgnoreCase("Attributes") ||
						data.content.equalsIgnoreCase("Children")) {
					
					data.content = "\"" + data.content + "\"";
				}
				
				ElementUtilities.addChild(
						derive,
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