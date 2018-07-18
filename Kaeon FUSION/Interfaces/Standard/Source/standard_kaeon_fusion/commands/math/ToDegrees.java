package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class ToDegrees extends FUSIONUnit {
	
	public ToDegrees() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("To Degrees");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.toDegrees(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}