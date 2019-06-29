package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Power extends FUSIONUnit {
	
	public Power() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Power");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result =
				Math.pow(
						Double.parseDouble("" + processed.get(0)),
						Double.parseDouble("" + processed.get(1)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}