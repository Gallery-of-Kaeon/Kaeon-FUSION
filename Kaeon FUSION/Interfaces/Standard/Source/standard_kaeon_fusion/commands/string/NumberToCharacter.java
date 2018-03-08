package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class NumberToCharacter extends FUSIONUnit {
	
	public NumberToCharacter() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Number To Character");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return (char) Integer.parseInt("" + processed.get(0));
	}
}