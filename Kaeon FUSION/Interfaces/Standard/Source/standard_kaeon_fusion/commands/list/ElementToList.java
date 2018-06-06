package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one_plus.ONEPlus;

public class ElementToList extends FUSIONUnit {
	
	public ElementToList() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Element to List");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return elementToList(ONEPlus.parseONEPlus("" + processed.get(0)));
	}
	
	public static ArrayList<Object> elementToList(Element element) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		if(element.content != null)
			list.add(element.content);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).children.size() == 0)
				list.add(element.children.get(i).content);
			
			else
				list.add(elementToList(element.children.get(i)));
		}
		
		return list;
	}
}