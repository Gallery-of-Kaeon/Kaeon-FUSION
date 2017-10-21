package standard.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Multiply extends FUSIONUnit {
	
	public Multiply() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Multiply");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double result = 0;
		
		for(Object object : processed)
			result *= Double.parseDouble("" + object);
		
		return result % 1 != 0 ? result : "" + (int) result;
	}
}