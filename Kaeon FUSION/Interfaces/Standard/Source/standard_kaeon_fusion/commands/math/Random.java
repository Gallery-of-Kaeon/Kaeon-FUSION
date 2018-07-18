package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Random extends FUSIONUnit {
	
	public Random() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Random");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Math.random();
	}
}