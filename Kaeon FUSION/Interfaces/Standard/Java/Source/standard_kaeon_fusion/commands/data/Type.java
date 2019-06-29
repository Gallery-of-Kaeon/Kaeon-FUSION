package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Type extends FUSIONUnit {
	
	public Type() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Type");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return processed.get(0).getClass().getName();
	}
}