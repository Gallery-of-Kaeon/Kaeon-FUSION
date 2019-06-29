package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;
import java.util.Collections;

import fusion.FUSIONUnit;
import one.Element;

public class Shuffle extends FUSIONUnit {
	
	public Shuffle() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Shuffle");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			Collections.shuffle((ArrayList<Object>) processed.get(0));
			
			return processed.get(0);
		}
		
		else {
			
			String string = "" + processed.get(0);
			
			ArrayList<String> list = new ArrayList<String>();
			
			for(int i = 0; i < string.length(); i++)
				list.add("" + string.charAt(i));
			
			Collections.shuffle(list);
			
			string = "";
			
			for(int i = 0; i < list.size(); i++)
				string += list.get(i);
			
			return string;
		}
	}
}