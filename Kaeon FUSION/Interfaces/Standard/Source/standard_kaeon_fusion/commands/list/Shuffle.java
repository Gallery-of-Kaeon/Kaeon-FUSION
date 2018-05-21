package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;
import java.util.Collections;

import fusion.FUSIONUnit;
import one.Element;

public class Shuffle extends FUSIONUnit {
	
	public Shuffle() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Shuffle");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		Collections.shuffle((ArrayList<Object>) processed.get(0));
		
		return processed.get(0);
	}
}