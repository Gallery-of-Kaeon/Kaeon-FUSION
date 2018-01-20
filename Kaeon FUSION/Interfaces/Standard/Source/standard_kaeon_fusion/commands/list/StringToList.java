package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class StringToList extends FUSIONUnit {
	
	public StringToList() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("String to List");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return stringToList((String) processed.get(0));
	}
	
	public static ArrayList<Object> stringToList(String string) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		for(int i = 0; i < string.length(); i++)
			list.add(string.charAt(i));
		
		return list;
	}
}