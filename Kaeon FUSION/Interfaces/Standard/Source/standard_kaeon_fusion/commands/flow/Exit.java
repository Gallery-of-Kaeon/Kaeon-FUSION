package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Exit extends FUSIONUnit {
	
	public Exit() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Exit");
	}
	
	public int changeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		return -1;
	}
}