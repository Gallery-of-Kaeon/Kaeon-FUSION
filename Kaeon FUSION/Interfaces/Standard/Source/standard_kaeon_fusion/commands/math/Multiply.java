package standard_kaeon_fusion.commands.math;

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
		
		if(processed.size() == 0)
			return 0;
		
		double result = Double.parseDouble("" + processed.get(0));
		
		for(int i = 1; i < processed.size(); i++)
			result *= Double.parseDouble("" + processed.get(i));
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}