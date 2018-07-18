package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Tangent extends FUSIONUnit {
	
	public Tangent() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Tangent");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.tan(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}