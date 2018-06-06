package standard_kaeon_fusion.commands.kaeon;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class SOULCore extends FUSIONUnit {
	
	public SOULCore() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("SOUL Core");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
}