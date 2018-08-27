package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;
import java.util.Collections;

import fusion.FUSIONUnit;
import one.Element;

public class Median extends FUSIONUnit {
	
	public Median() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Median");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		ArrayList<Double> numbers = new ArrayList<Double>();
		
		for(int i = 0; i < list.size(); i++)
			numbers.add(Double.parseDouble("" + list.get(i)));
		
		Collections.sort(numbers);
		
		double result = numbers.get(numbers.size() / 2);
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}