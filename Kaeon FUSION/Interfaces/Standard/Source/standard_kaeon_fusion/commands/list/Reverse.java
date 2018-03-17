package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Reverse extends FUSIONUnit {
	
	public Reverse() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Reverse");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> reverse = new ArrayList<Object>();
			
			for(int i = ((ArrayList) processed.get(0)).size() - 1; i >= 0; i--)
				reverse.add(((ArrayList) processed.get(0)).get(i));
			
			return reverse;
		}
		
		String string = "" + processed.get(0);
		
		String reverse = "";
		
		for(int i = string.length() - 1; i >= 0; i--)
			reverse += string.charAt(i);
		
		return reverse;
	}
}