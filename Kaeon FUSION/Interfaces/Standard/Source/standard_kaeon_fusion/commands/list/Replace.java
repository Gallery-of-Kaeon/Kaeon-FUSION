package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Replace extends FUSIONUnit {
	
	public Replace() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Replace");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			
			for(int i = 0; i < list.size(); i++) {
				
				if(list.get(i).equals(processed.get(1)))
					list.set(i, processed.get(2));
			}
			
			return list;
		}
		
		return ("" + processed.get(0)).replace("" + processed.get(1), "" + processed.get(2));
	}
}