package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Lower extends FUSIONUnit {
	
	public Lower() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Lower");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).toLowerCase();
	}
}