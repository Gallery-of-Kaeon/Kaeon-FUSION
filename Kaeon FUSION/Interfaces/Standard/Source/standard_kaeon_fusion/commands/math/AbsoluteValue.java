package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class AbsoluteValue extends FUSIONUnit {
	
	public AbsoluteValue() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Absolute Value");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.abs(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}