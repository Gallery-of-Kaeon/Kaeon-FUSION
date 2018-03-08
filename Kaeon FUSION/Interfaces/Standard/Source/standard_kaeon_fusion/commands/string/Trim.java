package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Trim extends FUSIONUnit {
	
	public Trim() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Trim");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).trim();
	}
}