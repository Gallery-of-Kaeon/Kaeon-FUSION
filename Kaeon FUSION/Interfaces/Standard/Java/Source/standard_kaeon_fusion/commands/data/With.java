package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.list.ListToElement;
import standard_kaeon_fusion.utilities.Priority;
import standard_kaeon_fusion.utilities.state.State;

public class With extends FUSIONUnit {
	
	public FUSION fusion;
	
	public With() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("With");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		Element command = new Element();
		
		if(processed.get(0) instanceof ArrayList)
			command = ListToElement.listToElement((ArrayList<Object>) processed.get(0));
		
		else
			command.content = "" + processed.get(0);
		
		for(int i = 0; i < fusion.fusionUnits.size(); i++) {
			
			if(fusion.fusionUnits.get(i) instanceof State || fusion.fusionUnits.get(i) instanceof Priority)
				continue;
			
			if(fusion.fusionUnits.get(i).verify(command))
				return fusion.fusionUnits.get(i).process(command, (ArrayList<Object>) processed.get(1));
		}
		
		return null;
	}
}
