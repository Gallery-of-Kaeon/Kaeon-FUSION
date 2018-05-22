package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class PatternMatch extends FUSIONUnit {
	
	public PatternMatch() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Pattern Match");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).matches("" + processed.get(1));
	}
}