package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Vanish extends FUSIONUnit {
	
	public ArrayList<Element> toVanish;
	
	public Vanish() {
		
		tags.add("Standard");
		
		toVanish = new ArrayList<Element>();
	}
	
	public boolean verify(Element element) {
		
		for(int i = 0; i < toVanish.size(); i++) {
			
			int index = ElementUtilities.getIndex(toVanish.get(i));
			
			if(index >= 0)
				ElementUtilities.removeChild(toVanish.get(i).parent, index);
		}
		
		return element.content.equalsIgnoreCase("Vanish");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		toVanish.add(element);
		
		return null;
	}
}