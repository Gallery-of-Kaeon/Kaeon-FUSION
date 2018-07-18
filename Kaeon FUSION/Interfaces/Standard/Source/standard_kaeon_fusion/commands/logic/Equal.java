package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Equal extends FUSIONUnit {
	
	public Equal() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Equal");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 1; i < processed.size(); i++) {
			
			if(!("" + processed.get(0)).equals("" + processed.get(i)))
				return false;
		}
		
		return true;
	}
}