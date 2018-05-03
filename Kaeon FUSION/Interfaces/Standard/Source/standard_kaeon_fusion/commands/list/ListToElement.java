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
		return "" + listToElement((ArrayList<Object>) processed.get(0), false);
	}
	
	@SuppressWarnings("unchecked")
	public static Element listToElement(ArrayList<Object> list, boolean content) {
		
		Element element =
				content ?
						ElementUtilities.createElement(
								list.size() > 0 ?
										"" + list.get(0) :
										"") :
						new Element();
		
		for(int i = content ? 1 : 0; i < list.size(); i++) {
			
			if(list.get(i) instanceof ArrayList)
				ElementUtilities.addChild(element, listToElement((ArrayList<Object>) list.get(i), true));
			
			else
				ElementUtilities.addChild(element, ElementUtilities.createElement("" + list.get(i)));
		}
		
		return element;
	}
}