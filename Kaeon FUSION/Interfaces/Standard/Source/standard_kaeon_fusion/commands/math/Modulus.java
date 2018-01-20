package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Modulus extends FUSIONUnit {
	
	public Modulus() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Modulus");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = Double.parseDouble("" + processed.get(0));
		
		for(int i = 1; i < processed.size(); i++)
			result %= Double.parseDouble("" + processed.get(i));
		
		return result % 1 != 0 ? result : "" + (int) result;
	}
}