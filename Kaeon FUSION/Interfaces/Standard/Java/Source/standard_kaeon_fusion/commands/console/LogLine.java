package standard_kaeon_fusion.commands.console;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class LogLine extends FUSIONUnit {
	
	public LogLine() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Log Line");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		processed.add("\n");
		
		PhilosophersStoneUtilities.call(this, "Log", processed);
		
		return null;
	}
}