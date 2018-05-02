package stack.utilities.xml;

import java.util.ArrayList;

public class XMLElement {
	
	private XMLElement parent;
	private String id;
	
	private ArrayList<XMLAttribute> attributes;
	private ArrayList<Object> data;
	
	public XMLElement() {
		attributes = new ArrayList<XMLAttribute>();
		data = new ArrayList<Object>();
	}
	
	public XMLElement(XMLElement parent, String id) {
		
		this();
		
		setParent(parent);
		setID(id);
	}
	
	public void setParent(XMLElement parent) {
		this.parent = parent;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void addAttribute(XMLAttribute attribute) {
		
		if(attribute != null)
			attributes.add(attribute);
	}
	
	public void addData(Object data) {
		
		if(data == null)
			return;
		
		if(data instanceof XMLElement)
			((XMLElement) data).setParent(this);
		
		if(data instanceof XMLElement || data instanceof String)
			this.data.add(data);
	}
	
	public XMLElement getParent() {
		return parent;
	}
	
	public String getID() {
		return id;
	}
	
	public ArrayList<XMLAttribute> getAttributes() {
		return attributes;
	}
	
	public ArrayList<XMLAttribute> getAttributes(String id) {

		ArrayList<XMLAttribute> attributes = new ArrayList<XMLAttribute>();
		
		for(int i = 0; i < this.attributes.size(); i++) {
			
			if(this.attributes.get(i).getID() != null) {
				
				if(this.attributes.get(i).getID().equals(id))
					attributes.add(this.attributes.get(i));
			}
		}
		
		return attributes;
	}
	
	public XMLAttribute getAttribute(String id) {
		
		for(int i = 0; i < this.attributes.size(); i++) {
			
			if(this.attributes.get(i).getID() != null) {
				
				if(this.attributes.get(i).getID().equals(id))
					return this.attributes.get(i);
			}
		}
		
		return null;
	}
	
	public XMLAttribute getAttribute(int index) {
		
		if(index >= 0 && index < attributes.size())
			return attributes.get(index);
		
		return null;
	}
	
	public boolean hasAttribute(String id) {
		return getAttribute(id) != null;
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
}