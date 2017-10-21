package standard.commands.core;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.FUSIONUtilities;

public class Execute extends FUSIONUnit {
	
	public FUSION fusion;
	
	public Execute() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Execute");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		FUSION newFusion = FUSIONUtilities.clone(fusion);
		
		for(Object object : processed)
			newFusion.process(ONEPlus.parseONEPlus("" + object));
		
		PhilosophersStoneUtilities.destroy(newFusion);
		
		return null;
	}
}