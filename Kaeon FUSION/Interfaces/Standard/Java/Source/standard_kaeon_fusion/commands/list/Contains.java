package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Contains extends FUSIONUnit {
	
	public Contains() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Contains");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList)
			return ((ArrayList) processed.get(0)).contains(processed.get(1));
		
		return ("" + processed.get(0)).contains("" + processed.get(1));
	}
}