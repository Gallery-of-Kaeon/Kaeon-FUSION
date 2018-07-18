package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;
import java.util.Calendar;

import fusion.FUSIONUnit;
import one.Element;

public class Minute extends FUSIONUnit {
	
	public Minute() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Second");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Calendar.getInstance().get(Calendar.SECOND);
	}
}