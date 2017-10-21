package standard.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Append extends FUSIONUnit {
	
	public Append() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Append");
	}
	
	@SuppressWarnings("rawtypes")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> list = (ArrayList) processed.get(0);
			
			for(int i = 1; i < processed.size(); i++)
				list.add(processed.get(i));
		}
		
		if(processed.get(0) instanceof String) {
			
			Object stringObject = processed.get(0);
			
			for(int i = 1; i < processed.size(); i++)
				stringObject = ((String) stringObject) + processed.get(i);
		}
		
		return null;
	}
}