package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class GetCodeIndex extends FUSIONUnit {
	
	public GetCodeIndex() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Get Code");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> indexes = new ArrayList<Object>();
		
		Element current = element;
		
		while(current.parent != null) {
			
			indexes.add(ElementUtilities.getIndex(current));
			
			current = current.parent;
		}
		
		return indexes;
	}
}