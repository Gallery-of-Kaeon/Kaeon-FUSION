package standard_kaeon_fusion.commands.data;

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
			
			ArrayList<Object> arguments = PhilosophersStoneUtilities.call(this, "Arguments");
			
			return arguments.get(arguments.size() - 1);
		}
		
		catch(Exception exception) {
			
		}
		
		return new ArrayList<Object>();
	}
}