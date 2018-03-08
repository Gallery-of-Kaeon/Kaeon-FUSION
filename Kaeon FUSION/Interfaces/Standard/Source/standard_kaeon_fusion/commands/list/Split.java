package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Split extends FUSIONUnit {
	
	public Split() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Split");
	}
	
	@SuppressWarnings({ "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> sub = new ArrayList<Object>();
			ArrayList<Object> current = new ArrayList<Object>();
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			
			for(int i = 0; i < list.size(); i++) {
				
				if(list.get(i).equals(processed.get(1)) && current.size() > 0) {
					sub.add(current);
					current = new ArrayList<Object>();
				}
			}
			
			if(current.size() > 0)
				sub.add(current);
			
			return sub;
		}
		
		return ("" + processed.get(0)).split("" + processed.get(1));
	}
}