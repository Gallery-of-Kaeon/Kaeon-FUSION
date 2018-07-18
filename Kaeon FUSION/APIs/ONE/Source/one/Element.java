package one;

import java.util.ArrayList;

public class Element {
	
	public String content = "";
	
	public Element parent;
	public ArrayList<Element> children = new ArrayList<Element>();
	
	public String toString() {
		return ElementUtilities.writeElement(this);
	}
}