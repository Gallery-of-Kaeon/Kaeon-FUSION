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
		
		Object object = processed.get(0);
		
		if(object instanceof ArrayList) {
			
			for(int i = 1; i < processed.size() && object instanceof ArrayList; i++)
				object = ((ArrayList) object).get((Integer.parseInt("" + processed.get(i))) - 1);
			
			return object;
		}
		
		return ("" + processed.get(0)).charAt((Integer.parseInt("" + processed.get(1))) - 1);
	}
}