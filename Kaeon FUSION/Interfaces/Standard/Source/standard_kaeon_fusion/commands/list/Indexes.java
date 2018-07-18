package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Indexes extends FUSIONUnit {
	
	public Indexes() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Indexes");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> indexes = new ArrayList<Object>();
		
		int start = 0;
		
		if(processed.size() >= 3)
			start = Integer.parseInt("" + processed.get(2));
		
		if(processed.get(0) instanceof ArrayList) {
			
			for(int i = start; i < ((ArrayList) processed.get(0)).size(); i++) {
				
				if(((ArrayList) processed.get(0)).get(i).equals(processed.get(1)))
					indexes.add(i + 1);
			}
		}
		
		else {
			
			String string = "" + processed.get(0);
			String token = "" + processed.get(1);
			
			while(true) {
				
				start = string.indexOf(token, start);
				
				if(start == -1)
					break;
				
				start++;
				indexes.add(start);
			}
		}
		
		return indexes;
	}
}