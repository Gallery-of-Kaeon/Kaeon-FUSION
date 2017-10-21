package standard.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class ListToString extends FUSIONUnit {
	
	public ListToString() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("List to String");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object process(Element element, ArrayList<Object> processed) {
		return listToString((ArrayList) processed.get(0));
	}
	
	public static String listToString(ArrayList<Object> list) {

		String string = "";
		
		for(Object object : list)
			string += object;
		
		return string;
	}
}