package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class ConvertSequence extends FUSIONUnit {
	
	public ConvertSequence() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("List to String");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList)
			return listToString((ArrayList) processed.get(0));
		
		return stringToList("" + processed.get(0));
	}
	
	public static String listToString(ArrayList<Object> list) {

		String string = "";
		
		for(Object object : list)
			string += object;
		
		return string;
	}
	
	public static ArrayList<Object> stringToList(String string) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		for(int i = 0; i < string.length(); i++)
			list.add(string.charAt(i));
		
		return list;
	}
}