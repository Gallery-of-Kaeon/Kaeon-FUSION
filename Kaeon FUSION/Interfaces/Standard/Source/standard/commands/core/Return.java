package standard.commands.core;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Return extends FUSIONUnit {
	
	public Object toReturn;
	public boolean returning;
	
	public Return() {
		tags.add("Standard");
		tags.add("Return");
	}
	
	public boolean deny(Element element) {
		return returning;
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Return");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 1)
			toReturn = processed.get(0);
		
		if(processed.size() > 1)
			toReturn = processed;
		
		returning = true;
		
		return null;
	}
}