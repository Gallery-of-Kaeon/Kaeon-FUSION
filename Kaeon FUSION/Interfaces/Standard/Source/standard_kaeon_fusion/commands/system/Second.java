package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;
import java.util.Calendar;

import fusion.FUSIONUnit;
import one.Element;

public class Second extends FUSIONUnit {
	
	public Second() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Hour");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Calendar.getInstance().get(Calendar.HOUR);
	}
}