package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;

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
		
		for(Object object : processed)
			fusion.internalProcess(ONEPlus.parseONEPlus("" + object), true);
		
		return null;
	}
}