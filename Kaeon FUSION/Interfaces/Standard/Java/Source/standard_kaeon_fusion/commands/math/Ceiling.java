package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Ceiling extends FUSIONUnit {
	
	public Ceiling() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Ceiling");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Math.ceil(Double.parseDouble("" + processed.get(0)));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}