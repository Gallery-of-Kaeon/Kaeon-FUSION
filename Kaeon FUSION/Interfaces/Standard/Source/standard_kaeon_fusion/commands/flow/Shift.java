package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import standard_kaeon_fusion.commands.list.ListToElement;

public class Shift extends FUSIONUnit {
	
	public Shift() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Shift");
	}
	
	@SuppressWarnings("unchecked")
	public Element jump(Element element, ArrayList<Object> processed) {
		
		Element jump = ListToElement.listToElement((ArrayList<Object>) processed.get(0));
		
		if(processed.size() > 1) {
			
			ArrayList<Object> indices = (ArrayList<Object>) processed.get(1);
			
			for(int i = 0; i < indices.size(); i++)
				jump = ElementUtilities.getChild(jump, Integer.parseInt("" + indices.get(i)) - 1);
		}
		
		return jump;
	}
}