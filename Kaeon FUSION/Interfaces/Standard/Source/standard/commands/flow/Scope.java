package standard.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Scope extends FUSIONUnit {
	
	public Scope() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Scope");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 1)
			return processed.get(0);
		
		if(processed.size() > 1)
			return processed;
		
		return null;
	}
}