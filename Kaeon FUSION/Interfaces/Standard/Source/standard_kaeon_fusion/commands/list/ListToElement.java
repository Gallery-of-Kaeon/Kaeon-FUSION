package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class ListToElement extends FUSIONUnit {
	
	public ListToElement() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("List to Element");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		return "" + listToElement((ArrayList<Object>) processed.get(0));
	}
	
	@SuppressWarnings("unchecked")
	public static Element listToElement(ArrayList<Object> list) {
		
		if(list.size() == 0)
			return new Element();
		
		Element element = ElementUtilities.createElement("" + list.get(0));
		
		for(int i = 1; i < list.size(); i++) {
			
			if(list.get(i) instanceof ArrayList)
				ElementUtilities.addChild(element, listToElement((ArrayList<Object>) list.get(i)));
			
			else
				ElementUtilities.addChild(element, ElementUtilities.createElement("" + list.get(i)));
		}
		
		return element;
	}
}