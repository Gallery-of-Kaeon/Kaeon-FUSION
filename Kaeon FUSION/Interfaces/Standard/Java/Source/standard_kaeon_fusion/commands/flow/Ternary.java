package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Ternary extends FUSIONUnit {
	
	public Ternary() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Ternary");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Boolean.parseBoolean("" + processed.get(0)) ? processed.get(1) : processed.get(2);
	}
}