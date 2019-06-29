package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class KeyIndexes extends FUSIONUnit {
	
	public KeyIndexes() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Key Indexes");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> lists = (ArrayList<Object>) processed.get(0);
		Object object = processed.get(1);
		int index = processed.size() > 2 ? Integer.parseInt("" + processed.get(2)) - 1 : -1;
		
		ArrayList<Object> keys = new ArrayList<Object>();
		
		for(int i = 0; i < lists.size(); i++) {
			
			ArrayList<Object> list = ((ArrayList<Object>) lists.get(i));
			
			if(index != -1) {
				
				Object key = list.get(index);
				
				if(object.equals(key))
					keys.add(i + 1);
			}
			
			else if(list.contains(object))
				keys.add(i + 1);
		}
		
		return keys;
	}
}