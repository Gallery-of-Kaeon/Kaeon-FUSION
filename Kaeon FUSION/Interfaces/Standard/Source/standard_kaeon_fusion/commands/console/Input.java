package standard_kaeon_fusion.commands.console;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Input extends FUSIONUnit {
	
	public Input() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Input");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return PhilosophersStoneUtilities.call(this, "Input", processed).get(0);
	}
}