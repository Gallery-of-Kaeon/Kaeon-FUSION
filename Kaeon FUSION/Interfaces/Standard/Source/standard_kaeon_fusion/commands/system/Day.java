package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;
import java.util.Calendar;

import fusion.FUSIONUnit;
import one.Element;

public class Day extends FUSIONUnit {
	
	public Day() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Day");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
}