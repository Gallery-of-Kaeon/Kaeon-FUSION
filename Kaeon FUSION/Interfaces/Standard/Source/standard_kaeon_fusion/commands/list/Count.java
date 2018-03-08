package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Count extends FUSIONUnit {
	
	public Count() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Count");
	}
	
	@SuppressWarnings({ "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		int count = 0;
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			
			for(int i = 0; i < list.size(); i++) {
				
				if(list.get(i).equals(processed.get(1)))
					count++;
			}
			
			return count;
		}
	
		String string = "" + processed.get(0);
		String item = "" + processed.get(1);
		
		int from = 0;
		
		while(string.indexOf(item, from) != -1) {
			
			from = string.indexOf(item) + item.length();
			
			count++;
		}
		
		return count;
	}
}