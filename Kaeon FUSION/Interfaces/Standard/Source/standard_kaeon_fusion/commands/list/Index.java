package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Index extends FUSIONUnit {
	
	public Index() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Index");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		int start = 0;
		
		if(processed.size() >= 3)
			start = Integer.parseInt("" + processed.get(2));
		
		if(processed.get(0) instanceof ArrayList) {
			
			for(int i = start; i < ((ArrayList) processed.get(0)).size(); i++) {
				
				if(((ArrayList) processed.get(0)).get(i).equals(processed.get(1)))
					return i + 1;
			}
			
			return 0;
		}
		
		return ("" + processed.get(0)).indexOf("" + processed.get(1), start) + 1;
	}
}