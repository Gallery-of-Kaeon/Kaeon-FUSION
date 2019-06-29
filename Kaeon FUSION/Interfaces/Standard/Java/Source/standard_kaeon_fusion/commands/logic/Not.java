package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Not extends FUSIONUnit {
	
	public Not() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Not");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return !Boolean.parseBoolean("" + processed.get(0));
	}
}