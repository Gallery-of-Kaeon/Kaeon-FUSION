package standard_kaeon_fusion.commands.exporting;

import fusion.FUSIONUnit;
import one.Element;

public class Meta extends FUSIONUnit {
	
	public Meta() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Meta");
	}
}