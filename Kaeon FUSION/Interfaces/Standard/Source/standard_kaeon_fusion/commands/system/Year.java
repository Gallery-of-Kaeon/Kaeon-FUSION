package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;
import java.util.Calendar;

import fusion.FUSIONUnit;
import one.Element;

public class Year extends FUSIONUnit {
	
	public Year() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Year");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}