package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Minimum extends FUSIONUnit {
	
	public Minimum() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Minimum");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		if(list.size() == 0)
			return 0;
		
		double result = Double.POSITIVE_INFINITY;
		
		for(int i = 0; i < list.size(); i++) {
			
			if(Double.parseDouble("" + list.get(i)) < result)
				result = Double.parseDouble("" + list.get(i));
		}
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}