package one;

import java.util.ArrayList;

public class ElementUtilities {
	
	public static Element copyElement(Element element) {
		
		Element newElement = new Element();
		
		newElement.content = element.content;
		
		for(int i = 0; i < element.children.size(); i++)
			addChild(newElement, copyElement(element.children.get(i)));
		
		return newElement;
	}
	
	public static void setContent(Element element, String content) {
		element.content = content;
	}
	
	public static void setParent(Element element, Element parent) {
		element.parent = parent;
	}
	
	public static void addChild(Element element, Element child) {
		
		child.parent = element;
		
		element.children.add(child);
	}
	
	public static void addChild(Element element, Element child, int index) {
		
		child.parent = element;
		
		element.children.add(index, child);
	}
	
	public static void addChildren(Element element, ArrayList<Element> children) {
		
		for(int i = 0; i < children.size(); i++)
			children.get(i).parent = element;
		
		element.children.addAll(children);
	}
	
	public static void addChildren(Element element, ArrayList<Element> children, int index) {
		
		for(int i = 0; i < children.size(); i++)
			children.get(i).parent = element;
		
		element.children.addAll(index, children);
	}
	
	public static Element removeChild(Element element, int index) {
		
		if(index >= 0 && index < element.children.size())
			return element.children.remove(index);
		
		return null;
	}
	
	public static Element removeChild(Element element, String content) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content))
				return element.children.remove(i);
		}
		
		return null;
	}
	
	public static ArrayList<Element> removeChildren(Element element, String content) {
		
		ArrayList<Element> requestedChildren = new ArrayList<Element>();
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content)) {
				requestedChildren.add(element.children.remove(i));
				i--;
			}
		}
		
		return requestedChildren;
	}
	
	public static String getContent(Element element) {
		return element.content;
	}
	
	public static Element getParent(Element element) {
		return element.parent;
	}
	
	public static ArrayList<Element> getChildren(Element element) {
		return element.children;
	}
	
	public static Element getChild(Element element, int index) {
		
		if(index >= 0 && index < element.children.size())
			return element.children.get(index);
		
		return null;
	}
	
	public static Element getChild(Element element, String content) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content))
				return element.children.get(i);
		}
		
		return null;
	}
	
	public static ArrayList<Element> getChildren(Element element, String content) {
		
		ArrayList<Element> requestedChildren = new ArrayList<Element>();
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content))
				requestedChildren.add(element.children.get(i));
		}
		
		return requestedChildren;
	}
	
	public static boolean hasChild(Element element, String content) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content))
				return true;
		}
		
		return false;
	}
	
	public static int getNumChildren(Element element) {
		return element.children.size();
	}
	
	public static int getNumElements(Element element, String content) {
		
		int numChildren = 0;
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase(content))
				numChildren++;
		}
		
		return numChildren;
	}
	
	public static int getIndex(Element element) {
		
		if(element.parent == null)
			return -1;
		
		for(int i = 0; i < element.parent.children.size(); i++) {
			
			if(element.parent.children.get(i) == element)
				return i;
		}
		
		return -1;
	}
}