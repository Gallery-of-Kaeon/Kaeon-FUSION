package standard_kaeon_fusion.commands.list;

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
					concatenate.addAll(ConvertSequence.stringToList((String) object));
			}
			
			return concatenate;
		}
		
		String concatenate = "";
		
		for(Object object : processed) {
			
			if(object instanceof ArrayList)
				concatenate += ConvertSequence.listToString((ArrayList) object);
			
			else
				concatenate += "" + object;
		}
		
		return concatenate;
	}
}