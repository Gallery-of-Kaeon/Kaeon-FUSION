package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Size extends FUSIONUnit {
	
	public Size() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Size");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList)
			return ((ArrayList) processed.get(0)).size();
		
		if(processed.get(0) instanceof String)
			return ((String) processed.get(0)).length();
		
		return null;
	}
}