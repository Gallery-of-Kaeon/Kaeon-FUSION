package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Range extends FUSIONUnit {
	
	public Range() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Range");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		if(list.size() == 0)
			return 0;
		
		double minimum = Double.POSITIVE_INFINITY;
		
		for(int i = 0; i < list.size(); i++) {
			
			if(Double.parseDouble("" + list.get(i)) < minimum)
				minimum = Double.parseDouble("" + list.get(i));
		}
		
		double maximum = Double.POSITIVE_INFINITY;
		
		for(int i = 0; i < list.size(); i++) {
			
			if(Double.parseDouble("" + list.get(i)) < maximum)
				maximum = Double.parseDouble("" + list.get(i));
		}
		
		double result = Math.abs(maximum - minimum);
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}