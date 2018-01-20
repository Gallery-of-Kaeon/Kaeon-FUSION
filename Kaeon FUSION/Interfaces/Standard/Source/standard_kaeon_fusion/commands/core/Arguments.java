package standard_kaeon_fusion.commands.core;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Arguments extends FUSIONUnit {
	
	public Arguments() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Arguments");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		try {
			return PhilosophersStoneUtilities.call(this, "Arguments").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		return new ArrayList<Object>();
	}
}