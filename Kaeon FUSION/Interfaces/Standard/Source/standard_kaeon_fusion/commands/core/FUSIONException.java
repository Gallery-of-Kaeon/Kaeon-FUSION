package standard_kaeon_fusion.commands.core;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class FUSIONException extends FUSIONUnit {
	
	public FUSIONException() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Exception");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
}