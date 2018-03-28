package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Throw extends FUSIONUnit {
	
	public Throw() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Throw");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return processed.get(-1);
	}
}