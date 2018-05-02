package stack.utilities.xml.io;

import java.util.ArrayList;

import stack.utilities.xml.XMLAttribute;
import stack.utilities.xml.XMLElement;
import stack.utilities.xml.XMLUnit;

public class XMLReader {
	
	private static XMLElement currentElement;
	private static String currentTag;
	
	public static void readXML(XMLUnit metadata, ArrayList<String> file) {
		parseFile(metadata, file);
	}
	
	public static void parseFile(XMLUnit metadata, ArrayList<String> file) {
		
		cropFile(file);
		
		currentElement = null;
		
		boolean inCDATA = false;
		
		for(int i = 0; i < file.size(); i++) {
			
			String line = file.get(i);
			
			if(inCDATA) {
				
				if(file.indexOf("]]>") == -1) {
					
					currentElement.addData(line);
					
					continue;
				}
				
				currentElement.addData(
						line.substring(
								0,
								line.indexOf("]]>")));
				
				line = line.substring(line.indexOf("]]>") + 3);
				
				inCDATA = false;
			}
			
			while(line.indexOf("<![CDATA[") != -1) {
				
				if(line.indexOf("]]>") != -1) {
					
					currentElement.addData(
							line.substring(
									line.indexOf("<![CDATA[") + 9,
									line.indexOf("]]>")));
					
					line = line.substring(line.indexOf("]]>") + 3);
				}
				
				else {
					
					currentElement.addData(
							line.substring(
									line.indexOf("<![CDATA[") + 9));
					
					line = null;
					inCDATA = true;
					
					break;
				}
			}
			
			if(line != null)
				parseLine(metadata, line.trim());
		}
	}
	
	private static void cropFile(ArrayList<String> file) {
		
		boolean inComment = false;
		
		for(int i = 0; i < file.size(); i++) {
			
			String line = file.get(i).trim();
			
			if(inComment) {
				
				if(line.indexOf("-->") == -1)
					continue;
				
				line = line.substring(line.indexOf("-->") + 3);
				
				inComment = false;
			}
			
			while(line.indexOf("<!--") != -1) {
				
				if(line.indexOf("-->") != -1)
					line = line.substring(line.indexOf("-->") + 3);
				
				else {
					
					line = null;
					inComment = true;
					
					file.remove(i);
					i--;
					
					break;
				}
			}
			
			if(line != null) {
				
				if(line.trim().length() > 0)
					file.set(i, line);
				
				else {
					file.remove(i);
					i--;
				}					
			}
		}
	}
	
	private static void parseLine(XMLUnit metadata, String line) {
		
		if(line.indexOf('<') != -1) {
			
			addContent(metadata, line.substring(0, line.indexOf('<')));
			
			if(line.indexOf('>') != -1) {
				
				parseTag(
						metadata,
						line.substring(
								line.indexOf('<') + 1,
								line.indexOf('>'))
						);
				
				line = line.substring(line.indexOf('>') + 1);
				
				parseLine(metadata, line);
			}
			
			else
				currentTag = line.substring(line.indexOf('<') + 1);
		}
		
		else if(currentTag != null) {
			
			currentTag += line;
			
			if(currentTag.indexOf('>') != -1) {
				
				parseTag(
						metadata,
						currentTag.substring(
								0,
								currentTag.indexOf('>'))
						);

				line = currentTag.substring(currentTag.indexOf('>') + 1);
				currentTag = null;
				
				parseLine(metadata, line);
			}
		}
		
		else
			addContent(metadata, line);
	}
	
	private static void addContent(XMLUnit metadata, String content) {
		
		if(content.length() > 0) {
			
			if(currentElement == null)
				metadata.addData(formatContent(content));
			
			else
				currentElement.addData(formatContent(content));
		}
	}
	
	private static void parseTag(XMLUnit metadata, String tag) {

		boolean terminating = tag.charAt(0) == '/';
		boolean selfTerminating = tag.charAt(tag.length() - 1) == '/';
		
		if(terminating)
			tag = tag.substring(1);
		
		else if(selfTerminating)
			tag = tag.substring(0, tag.length() - 1);
		
		else if(tag.charAt(0) == '!') {
			metadata.setHeader(tag);
			return;
		}
		
		if(!terminating) {
			
			boolean isHeader = tag.charAt(0) == '?';
			
			if(isHeader)
				tag = tag.substring(1, tag.length() - 1);
			
			String id = null;
			
			if(tag.indexOf(' ') != -1) {
				id = tag.substring(0, tag.indexOf(' '));
				tag = tag.substring(tag.indexOf(' ') + 1);
			}
			
			else {
				id = tag;
				tag = null;
			}
			
			XMLElement element = new XMLElement(null, id);
			
			while(tag != null) {
				
				int quoteIndex = tag.indexOf('\"');
				
				if(tag.indexOf('\"', quoteIndex + 1) != -1) {
					
					element.addAttribute(
							getAttribute(
									tag.substring(0, tag.indexOf('\"', quoteIndex + 1) + 1)));
					
					tag = tag.substring(tag.indexOf('\"', quoteIndex + 1) + 1);
				}
				
				else {
					
					element.addAttribute(getAttribute(tag));
					
					tag = null;
				}
			}
			
			if(isHeader)
				metadata.setHeader(element);
			
			else {
				
				if(currentElement != null)
					currentElement.addData(element);
				
				else
					metadata.addData(element);
				
				if(!selfTerminating)
					currentElement = element;
			}
			
			return;
		}
		
		while(!currentElement.getID().equals(tag))
			currentElement = currentElement.getParent();
		
		currentElement = currentElement.getParent();
	}
	
	private static XMLAttribute getAttribute(String attribute) {
		
		attribute = attribute.trim();
		
		if(attribute.length() == 0)
			return null;
		
		return new XMLAttribute(
				attribute.substring(0, attribute.indexOf('=')),
				formatContent(
					attribute.substring(
							attribute.indexOf('"') + 1,
							attribute.lastIndexOf('"'))));
	}
	
	private static String formatContent(String content) {
		
		for(int i = 0; i < content.length(); i++) {
			
			if(content.charAt(i) == '&') {
				
				String temp = content.substring(0, i);
				
				content = content.substring(i);
				char escape = escape(content.substring(1, content.indexOf(';')));
				
				content = temp + escape + content.substring(content.indexOf(';') + 1);
			}
		}
		
		return content;
	}
	
	private static char escape(String escapeSequence) {
		
		if(escapeSequence.equals("amp"))
			return '&';
		
		if(escapeSequence.equals("apos"))
			return '\'';
		
		if(escapeSequence.equals("gt"))
			return '>';
		
		if(escapeSequence.equals("lt"))
			return '<';
		
		return '\"';
	}
}