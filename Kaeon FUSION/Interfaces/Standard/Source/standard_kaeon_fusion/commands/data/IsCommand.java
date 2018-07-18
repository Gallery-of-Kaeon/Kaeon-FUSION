package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.undefined.Functions;
import standard_kaeon_fusion.commands.undefined.Literals;
import standard_kaeon_fusion.commands.undefined.Variables;
import standard_kaeon_fusion.utilities.Priority;
import standard_kaeon_fusion.utilities.state.State;

public class IsCommand extends FUSIONUnit {
	
	public FUSION fusion;
	
	public IsCommand() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Is Command");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		Element verify = ElementUtilities.createElement("" + processed.get(0));
		
		for(FUSIONUnit command : fusion.fusionUnits) {
			
			if(!(command instanceof State) &&
					!(command instanceof Priority) &&
					!(command instanceof Literals) &&
					!(command instanceof Variables) &&
					!(command instanceof Functions)) {
				
				if(command.verify(verify))
					return true;
			}
		}
		
		return false;
	}
}