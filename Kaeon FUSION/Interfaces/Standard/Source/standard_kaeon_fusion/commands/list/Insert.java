package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Insert extends FUSIONUnit {
	
	public Insert() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Insert");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		int index = (Integer.parseInt("" + processed.get(1))) - 1;
		Object object = processed.get(2);
		
		if(processed.get(0) instanceof ArrayList) {
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> list = (ArrayList) processed.get(0);
			
			while(list.size() < index)
				list.add(null);
			
			list.add(index, object);
		}
		
		String string = "" + processed.get(0);
		
		while(string.length() < index)
			string += " ";
		
		return string.substring(0, index) + object + string.substring(index);
	}
}