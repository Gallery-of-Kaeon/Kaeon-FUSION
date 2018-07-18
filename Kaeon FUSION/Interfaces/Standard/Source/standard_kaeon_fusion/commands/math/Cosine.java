package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Cosine extends FUSIONUnit {
	
	public Cosine() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Cosine");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.cos(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}