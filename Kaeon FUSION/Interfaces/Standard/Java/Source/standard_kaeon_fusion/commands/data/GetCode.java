package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import standard_kaeon_fusion.commands.list.ElementToList;

public class GetCode extends FUSIONUnit {
	
	public GetCode() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Get Code");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		Element current = element;
		
		while(current.parent != null)
			current = current.parent;
		
		return ElementToList.elementToList(current);
	}
}