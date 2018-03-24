package kaeon_fusion.literal;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Literal extends FUSIONUnit {
	
	public FUSION fusion;
	
	public Literal() {
		tags.add("Kaeon FUSION");
		tags.add("Literal");
	}
	
	public boolean verify(Element element) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		for(FUSIONUnit command : fusion.fusionUnits) {
			
			if(command != this) {
				
				if(command.verify(element))
					return false;
			}
		}
		
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return element.content;
	}
}