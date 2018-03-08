package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Upper extends FUSIONUnit {
	
	public Upper() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Upper");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).toUpperCase();
	}
}