package stack.utilities.xml;

import java.util.ArrayList;

import stack.utilities.xml.io.XMLReader;
import stack.utilities.xml.io.XMLWriter;

public class XMLUnit {
	
	public static String TYPE_XML = "TYPE_XML";
	
	private Object header;
	private ArrayList<Object> data;
	
	public XMLUnit() {
		data = new ArrayList<Object>();
	}
	
	public XMLUnit(String xml) {
		
		this();
		
		ArrayList<String> xmlList = new ArrayList<String>();
		String currentString = "";
		
		for(int i = 0; i < xml.length(); i++) {
			
			if(xml.charAt(i) != '\n')
				currentString += xml.charAt(i);
			
			if(xml.charAt(i) == '\n' || i == xml.length() - 1) {
				xmlList.add(currentString);
				currentString = "";
			}
		}
		
		XMLReader.readXML(this, xmlList);
	}
	
	public XMLUnit(ArrayList<String> xml) {
		
		this();
		
		XMLReader.readXML(this, xml);
	}
	
	public void setHeader(Object header) {
		
		if(header instanceof XMLElement || header instanceof String)
			this.header = header;
	}
	
	public void addData(Object data) {
		
		if(data == null)
			return;
		
		if(data instanceof XMLElement || data instanceof String)
			this.data.add(data);
	}
	
	public Object getHeader() {
		return header;
	}
	
	public ArrayList<Object> getData() {
		return data;
	}
	
	public ArrayList<XMLElement> getElements() {
		
		ArrayList<XMLElement> elements = new ArrayList<XMLElement>();
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) instanceof XMLElement)
				elements.add((XMLElement) data.get(i));
		}
		
		return elements;
	}
	
	public ArrayList<XMLElement> getElements(String id) {

		ArrayList<XMLElement> elements = new ArrayList<XMLElement>();
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) instanceof XMLElement) {
				
				if(((XMLElement) data.get(i)).getID().equals(id))
					elements.add((XMLElement) data.get(i));
			}
		}
		
		return elements;
	}
	
	public XMLElement getElement(String id) {
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) instanceof XMLElement) {
				
				if(((XMLElement) data.get(i)).getID().equals(id))
					return (XMLElement) data.get(i);
			}
		}
		
		return null;
	}
	
	public boolean hasElement(String id) {
		return getElement(id) != null;
	}
	
	public ArrayList<String> getContent() {
		
		ArrayList<String> content = new ArrayList<String>();
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) instanceof String)
				content.add((String) data.get(i));
		}
		
		return content;
	}
	
	public String getContent(int index) {
		
		ArrayList<String> content = getContent();
		
		return index < content.size() ? content.get(index) : null;
	}
	
	public String getAllContent() {
		
		String content = "";
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) instanceof String)
				content += (String) data.get(i);
		}
		
		return content;
	}
	
	public String toString() {
		return XMLWriter.writeAsXML(this);
	}
}