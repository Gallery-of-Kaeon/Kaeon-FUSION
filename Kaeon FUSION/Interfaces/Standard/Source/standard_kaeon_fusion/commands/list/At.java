package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class At extends FUSIONUnit {
	
	public At() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("At");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList)
			return ((ArrayList) processed.get(0)).get((Integer.parseInt("" + processed.get(1))) - 1);
		
		if(processed.get(0) instanceof String)
			return ((String) processed.get(0)).charAt((Integer.parseInt("" + processed.get(1))) - 1);
		
		return null;
	}
}