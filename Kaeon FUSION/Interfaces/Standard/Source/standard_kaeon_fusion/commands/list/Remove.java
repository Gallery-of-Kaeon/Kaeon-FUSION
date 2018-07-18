package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Remove extends FUSIONUnit {
	
	public Remove() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Remove");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		int index = (Integer.parseInt("" + processed.get(1))) - 1;
		
		if(processed.get(0) instanceof ArrayList) {
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> list = (ArrayList) processed.get(0);
			
			return list.remove(index);
		}
		
		String string = "" + processed.get(0);
		
		char remove = string.charAt(index);
		string = string.substring(0, index) + string.substring(index + 1);
		
		return remove;
	}
}