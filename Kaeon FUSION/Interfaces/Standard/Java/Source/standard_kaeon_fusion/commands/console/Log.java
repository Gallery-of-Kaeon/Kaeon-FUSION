package standard_kaeon_fusion.commands.console;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Log extends FUSIONUnit {
	
	public Log() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Log");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		PhilosophersStoneUtilities.call(this, "Log", processed);
		
		return null;
	}
}