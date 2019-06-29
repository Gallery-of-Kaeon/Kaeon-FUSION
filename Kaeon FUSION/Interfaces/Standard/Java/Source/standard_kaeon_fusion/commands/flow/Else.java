package standard_kaeon_fusion.commands.flow;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Else extends FUSIONUnit {
	
	public Break broke;
	
	public Else() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Else");
	}
	
	public boolean trickleDown(Element element) {
		
		if(broke == null)
			broke = (Break) PhilosophersStoneUtilities.get(this, "Break").get(0);
		
		boolean trickleDown = broke.broke;
		broke.broke = false;
		
		return trickleDown;
	}
}