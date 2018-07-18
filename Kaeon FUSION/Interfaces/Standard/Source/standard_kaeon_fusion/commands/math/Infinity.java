package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Infinity extends FUSIONUnit {
	
	public Infinity() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Infinity");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Double.POSITIVE_INFINITY;
	}
}