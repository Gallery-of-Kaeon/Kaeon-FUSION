package standard.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Concatenate extends FUSIONUnit {
	
	public Concatenate() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Concatenate");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> concatenate = new ArrayList<Object>();
			
			for(Object object : processed) {
				
				if(object instanceof ArrayList)
					concatenate.addAll((ArrayList) object);
				
				if(object instanceof String)
					concatenate.addAll(StringToList.stringToList((String) object));
			}
			
			return concatenate;
		}
		
		if(processed.get(0) instanceof String) {
			
			String concatenate = "";
			
			for(Object object : processed) {
				
				if(object instanceof ArrayList)
					concatenate += ListToString.listToString((ArrayList) object);
				
				if(object instanceof String)
					concatenate += (String) object;
			}
			
			return concatenate;
		}
		
		return null;
	}
}