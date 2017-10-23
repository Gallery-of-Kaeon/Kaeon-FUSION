package legacy.utilities.one_plus.element;

import java.util.ArrayList;

import legacy.utilities.one_plus.write.ONEPlusWriter;
import one.ElementUtilities;

public class Element {
	
	private String content;
	
	private Element parent;
	private ArrayList<Element> elements;
	
	public Element() {
		elements = new ArrayList<Element>();
	}
	
	public Element(String content) {
		
		this();
		
		this.content = content;
	}
	
	public Element(Element element) {
		
		this();
		
		this.content = element.getContent();
		
		for(int i = 0; i < element.getNumElements(); i++)
			addElement(new Element(element.getElement(i)));
	}
	
	public Element(one.Element element) {
		
		this();
		
		Element parent = copy(element.parent);
		
		this.content = element.content;
		
		for(int i = 0; i < element.children.size(); i++)
			addElement(copy(element.children.get(i)));
		
		parent.removeElement(ElementUtilities.getIndex(element));
		parent.addElement(this, ElementUtilities.getIndex(element));
	}
	
	public Element copy(one.Element element) {
		
		Element copy = new Element(element.content);
		
		copy.content = element.content;
		
		for(int i = 0; i < element.children.size(); i++)
			copy.addElement(copy(element.children.get(i)));
		
		return copy;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setParent(Element parent) {
		this.parent = parent;
	}
	
	public void addElement(Element element) {
		
		element.setParent(this);
		
		elements.add(element);
	}
	
	public void addElement(Element element, int index) {
		
		element.setParent(this);
		
		elements.add(index, element);
	}
	
	public void addElements(ArrayList<Element> elements) {
		
		for(int i = 0; i < elements.size(); i++)
			elements.get(i).setParent(this);
		
		this.elements.addAll(elements);
	}
	
	public void addElements(ArrayList<Element> elements, int index) {
		
		for(int i = 0; i < elements.size(); i++)
			elements.get(i).setParent(this);
		
		this.elements.addAll(index, elements);
	}
	
	public Element removeElement(int index) {
		
		if(index >= 0 && index < elements.size())
			return elements.remove(index);
		
		return null;
	}
	
	public Element removeElement(String content) {
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content))
				return elements.remove(i);
		}
		
		return null;
	}
	
	public ArrayList<Element> removeElements(String content) {
		
		ArrayList<Element> requestedElements = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content)) {
				requestedElements.add(elements.remove(i));
				i--;
			}
		}
		
		return requestedElements;
	}
	
	public String getContent() {
		return content;
	}
	
	public Element getParent() {
		return parent;
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public Element getElement(int index) {
		
		if(index >= 0 && index < elements.size())
			return elements.get(index);
		
		return null;
	}
	
	public Element getElement(String content) {
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content))
				return elements.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Element> getElements(String content) {
		
		ArrayList<Element> requestedElements = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content))
				requestedElements.add(elements.get(i));
		}
		
		return requestedElements;
	}
	
	public boolean hasElement(String content) {
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content))
				return true;
		}
		
		return false;
	}
	
	public int getNumElements() {
		return elements.size();
	}
	
	public int getNumElements(String content) {
		
		int numElements = 0;
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(elements.get(i).getContent().equalsIgnoreCase(content))
				numElements++;
		}
		
		return numElements;
	}
	
	public String toString() {
		return ONEPlusWriter.writeElement(this);
	}
}