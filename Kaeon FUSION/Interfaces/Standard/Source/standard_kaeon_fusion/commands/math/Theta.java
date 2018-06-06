package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Theta extends FUSIONUnit {
	
	public Theta() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Theta");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result =
				Math.atan2(
						Double.parseDouble("" + processed.get(1)),
						Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}