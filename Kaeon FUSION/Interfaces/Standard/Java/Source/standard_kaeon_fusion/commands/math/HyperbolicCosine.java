package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class HyperbolicCosine extends FUSIONUnit {
	
	public HyperbolicCosine() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Hyperbolic Cosine");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.cosh(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}