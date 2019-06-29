package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class List extends FUSIONUnit {
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("List");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return processed;
	}
}