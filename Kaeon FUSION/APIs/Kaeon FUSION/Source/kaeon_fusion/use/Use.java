package kaeon_fusion.use;

import java.util.ArrayList;

import aether_kaeon_fusion.Aether;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class Use extends FUSIONUnit {
	
	public Use() {
		tags.add("Kaeon FUSION");
		tags.add("Use");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Use");
	}
	
	public double getPriority(Element element) {
		return Double.NEGATIVE_INFINITY;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		PhilosophersStone fusion = PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			Aether.call("" + processed.get(i), 0, fusion);
			
			PhilosophersStoneUtilities.call(fusion, "Update");
		}
		
		return element.content;
	}
}