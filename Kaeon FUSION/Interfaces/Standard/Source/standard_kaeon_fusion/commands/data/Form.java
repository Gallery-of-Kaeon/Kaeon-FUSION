package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Form extends FUSIONUnit {
	
	public Form() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Form");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {

		if(("" + processed.get(0)).equalsIgnoreCase("True") ||
				("" + processed.get(0)).equalsIgnoreCase("False")) {
			
			return "Boolean";
		}

		try {
			
			Double.parseDouble("" + processed.get(0));
			
			return "Number";
		}
		
		catch(Exception exception) {
			
		}
		
		return "String";
	}
}
