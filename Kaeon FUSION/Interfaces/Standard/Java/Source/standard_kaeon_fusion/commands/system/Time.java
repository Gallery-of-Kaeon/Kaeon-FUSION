package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Time extends FUSIONUnit {
	
	public Time() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Time");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return (double) System.currentTimeMillis() / 1000;
	}
}