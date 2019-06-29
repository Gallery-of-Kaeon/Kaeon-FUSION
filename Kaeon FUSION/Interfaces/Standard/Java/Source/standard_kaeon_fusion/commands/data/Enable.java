package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Enable extends FUSIONUnit {
	
	public Enable() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Enable");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> block = ((Disable) PhilosophersStoneUtilities.get(this, "Disable").get(0)).block;
		
		for(int i = 0; i < processed.size(); i++) {
			
			for(int j = 0; j < block.size(); j++) {
				
				if(("" + processed.get(i)).equalsIgnoreCase("" + block.get(j))) {
					
					block.remove(j);
					
					j--;
				}
			}
		}
		
		return null;
	}
}