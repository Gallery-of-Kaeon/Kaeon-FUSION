package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Isolate extends FUSIONUnit {
	
	public Isolate() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Isolate");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
}