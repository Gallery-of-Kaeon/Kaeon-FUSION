package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Swap extends FUSIONUnit {
	
	public Swap() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Swap");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		int a = Integer.parseInt("" + processed.get(1)) - 1;
		int b = Integer.parseInt("" + processed.get(2)) - 1;
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = ((ArrayList<Object>) processed.get(0));
			
			Object temporary = list.get(a);
			
			list.set(a, list.get(b));
			list.set(b, temporary);
			
			return list;
		}
		
		String string = "" + processed.get(0);
		
		char charA = string.charAt(a);
		char charB = string.charAt(b);
		
		string = string.substring(0, a) + charB + string.substring(a + 1);
		string = string.substring(0, b) + charA + string.substring(b + 1);
		
		return string;
	}
}