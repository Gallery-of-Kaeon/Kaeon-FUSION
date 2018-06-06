package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class HyperbolicTangent extends FUSIONUnit {
	
	public HyperbolicTangent() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Hyperbolic Tangent");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.tanh(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}