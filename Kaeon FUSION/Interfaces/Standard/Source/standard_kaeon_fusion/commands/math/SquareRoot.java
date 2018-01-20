package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class SquareRoot extends FUSIONUnit {
	
	public SquareRoot() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Square Root");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.sqrt(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (int) result;
	}
}