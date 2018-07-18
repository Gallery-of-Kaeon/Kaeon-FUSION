package one;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementUtilities {
	
	public static Element createElement(String content) {
		
		if(content == null)
			content = "";
		
		Element element = new Element();
		element.content = content;
		
		return element;
	}
	
	public static Element copyElement(Element element) {
		
		Element newElement = createElement(element.content);
		
		for(int i = 0; i < element.children.size(); i++)
			addChild(newElement, copyElement(element.children.get(i)));
		
		return newElement;
	}
	
	public static void setContent(Element element, String content) {
		
		if(content == null)
			content = "";
		
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
		
		if(element.content == null)
			return "";
		
		return element.content;
	}
	
	public static Element getParent(Element element) {
		return element.parent;
	}
	
	public static ArrayList<Element> getChildren(Element element) {
		return element.children;
	}
	
	public static Element getChild(Element element, Object... indices) {
		
		if(indices.length == 0)
			return null;
		
		Element currentElement = element;
		
		for(int i = 0; i < indices.length; i++) {
			
			if(indices[i] instanceof Integer) {
				
				int index = (Integer) indices[i];
				
				if(index >= 0 && index < currentElement.children.size())
					currentElement = currentElement.children.get(index);
				
				else
					return null;
			}
			
			else {
				
				String content = "" + indices[i];
				
				boolean found = false;
				
				for(int j = 0; j < currentElement.children.size() && !found; j++) {
					
					if(currentElement.children.get(j).content.equalsIgnoreCase(content)) {
						
						currentElement = currentElement.children.get(j);
						
						found = true;
					}
				}
				
				if(!found)
					return null;
			}
		}
		
		return currentElement;
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
	
	public static int getNumberOfChildren(Element element) {
		return element.children.size();
	}
	
	public static int getNumberOfChildren(Element element, String content) {
		
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
	
	public static boolean elementsEqual(Element a, Element b) {
		
		if(!a.content.equals(b.content) || a.children.size() != b.children.size())
			return false;
		
		for(int i = 0; i < a.children.size(); i++) {
			
			if(!a.children.get(i).equals(b.children.get(i)))
				return false;
		}
		
		return true;
	}
	
	public static Element readElement(String string) {
		return readElement(string, new ArrayList<String>(Arrays.asList("-", "\n", "\t")), false);
	}
	
	public static Element readElement(String string, ArrayList<String> tokens, boolean reduced) {
		
		Element element = new Element();
		
		try {
			
			ArrayList<ArrayList<String>> elements =
					getElements(
							new ArrayList<String>(
									Arrays.asList(
											string.split(tokens.get(1)))));
			
			Element currentElement = element;
			int currentNest = 0;
			
			for(int i = 0; i < elements.size(); i++) {
				
				int nest = cropElement(elements.get(i), tokens.get(2));
				
				Element newElement = createElement(getElementContent(elements.get(i), tokens.get(1)));
				
				for(int j = currentNest; j > nest - 1 && currentElement.parent != null; j--)
					currentElement = currentElement.parent;
				
				if(nest > currentNest && currentElement.children.size() > 0)
					currentElement = currentElement.children.get(currentElement.children.size() - 1);
				
				addChild(currentElement, newElement);
				
				if(nest > currentNest)
					currentElement = newElement;
				
				currentNest = nest;
			}
		}
		
		catch(Exception exception) {
			
			exception.printStackTrace();
			
			element = new Element();
		}
		
		return element;
	}
	
	public static ArrayList<ArrayList<String>> getElements(ArrayList<String> lines) {
		
		ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < lines.size(); i++) {
			
			String line = lines.get(i);
			
			ArrayList<String> element = new ArrayList<String>();
			element.add(lines.get(i));
			
			for(i++; i < lines.size() && !lines.get(i).equals(line); i++)
				element.add(lines.get(i));
			
			elements.add(element);
		}
		
		return elements;
	}
	
	public static int cropElement(ArrayList<String> element, String nesting) {
		
		int nestIndex = 0;
		int nest = 0;
		
		for(; element.get(0).indexOf(nesting, nestIndex) != -1; nestIndex += nesting.length())
			nest++;
		
		element.remove(0);
		
		for(int i = 0; i < element.size(); i++)
			element.set(i, element.get(i).substring((nest + 1) * nesting.length()));
		
		return nest;
	}
	
	public static String getElementContent(ArrayList<String> element, String breaking) {
		
		String content = "";
		
		for(int i = 0; i < element.size(); i++) {
			
			content += element.get(i);
			
			if(i < element.size() - 1)
				content += breaking;
		}
		
		return content;
	}
	
	public static String writeElement(Element element) {
		return writeElement(element, new ArrayList<String>(Arrays.asList("-", "\n", "\t")), false);
	}
	
	public static String writeElement(Element element, ArrayList<String> tokens, boolean reduced) {
		
		Element write = element;
		
		if(!element.content.equals("")) {
			
			write = new Element();
			
			addChild(write, copyElement(element));
		}
		
		return writeElement(tokens, write, 0, true, reduced);
	}
	
	public static String writeElement(
			ArrayList<String> tokens,
			Element element,
			int nest,
			boolean isRoot,
			boolean reduced) {
		
		String code = "";
		
		if(!isRoot) {
			
			String content = element.content;
			
			code +=
					indent(
							nest,
							tokens.get(2)) +
					(!reduced ? tokens.get(0) : "") +
					tokens.get(1) +
					indent(
							nest + 1,
							tokens.get(2));
			
			ArrayList<String> lines = new ArrayList<String>(Arrays.asList(content.split(tokens.get(1))));
			
			for(int i = 0; i < lines.size(); i++) {
				
				code += lines.get(i);
				
				if(i < lines.size() - 1)
					code += tokens.get(1) + indent(nest + 1, tokens.get(2));
			}
			
			code += tokens.get(1) + indent(nest, tokens.get(2)) + (!reduced ? tokens.get(0) : "");
		}
		
		ArrayList<Element> elements = element.children;
		
		for(int i = 0; i < elements.size(); i++) {
			
			if(!isRoot || i > 0)
				code += tokens.get(1);
			
			code +=
					writeElement(
							tokens,
							elements.get(i),
							(!isRoot ? nest + 1 : nest),
							false,
							reduced);
		}
		
		return code;
	}
	
	public static String indent(int nest, String token) {
		
		String indent = "";
		
		for(int i = 0; i < nest; i++)
			indent += token;
		
		return indent;
	}
}