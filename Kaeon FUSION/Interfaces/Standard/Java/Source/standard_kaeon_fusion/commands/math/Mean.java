package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Mean extends FUSIONUnit {
	
	public Mean() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Mean");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		double result = 0;
		
		for(int i = 0; i < list.size(); i++)
			result += Double.parseDouble("" + list.get(i));
		
		result /= list.size();
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}