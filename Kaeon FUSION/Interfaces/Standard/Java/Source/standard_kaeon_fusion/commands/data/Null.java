package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Null extends FUSIONUnit {
	
	public Null() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Null");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
}