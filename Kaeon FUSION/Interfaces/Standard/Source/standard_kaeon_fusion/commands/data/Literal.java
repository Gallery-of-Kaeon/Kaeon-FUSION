package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Literal extends FUSIONUnit {
	
	public Literal() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Literal");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return element.children.get(0).content;
	}
}