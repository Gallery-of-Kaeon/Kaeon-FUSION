package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Interpreter extends FUSIONUnit {
	
	public Interpreter() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Interpreter");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return PhilosophersStoneUtilities.get(this, "FUSION").get(0);
	}
}