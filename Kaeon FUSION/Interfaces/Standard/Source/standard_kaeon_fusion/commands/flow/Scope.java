package standard_kaeon_fusion.commands.flow;

import fusion.FUSIONUnit;
import one.Element;

public class Scope extends FUSIONUnit {
	
	public Scope() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Scope");
	}
}