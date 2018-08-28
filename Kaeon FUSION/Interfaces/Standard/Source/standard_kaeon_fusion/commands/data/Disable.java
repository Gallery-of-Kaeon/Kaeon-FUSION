package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Disable extends FUSIONUnit {
	
	public ArrayList<Object> block;
	
	public Disable() {
		
		tags.add("Standard");
		tags.add("Disable");
		
		block = new ArrayList<Object>();
	}
	
	public boolean deny(Element element) {
		
		for(int i = 0; i < block.size(); i++) {
			
			if(element.content.equalsIgnoreCase("" + block.get(i)))
				return true;
		}
		
		return false;
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Disable");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		block.addAll(processed);
		
		return null;
	}
}