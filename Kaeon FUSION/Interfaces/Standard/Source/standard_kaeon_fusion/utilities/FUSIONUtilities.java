package standard_kaeon_fusion.utilities;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class FUSIONUtilities {
	
	public static FUSION copy(FUSION fusion) {
		
		FUSION copy = new FUSION();
		
		ArrayList<PhilosophersStone> atlas = PhilosophersStoneUtilities.getAtlas(fusion);
		
		for(PhilosophersStone stone : atlas) {
			
			if(stone instanceof FUSIONUnit) {
				
				try {
					
					PhilosophersStoneUtilities.
							publiclyConnectMutually(
									copy,
									stone.getClass().newInstance());
				}
				
				catch (Exception exception) {
					
				}
			}
			
			else if(!(stone instanceof FUSION)) {
				
				PhilosophersStoneUtilities.
						publiclyConnect(
								copy,
								stone);
			}
		}
		
		return copy;
	}
	
	public static FUSION clone(FUSION fusion) {
		
		FUSION copy = new FUSION();
		
		ArrayList<PhilosophersStone> atlas = PhilosophersStoneUtilities.getAtlas(fusion);
		
		for(PhilosophersStone stone : atlas) {
			
			if(PhilosophersStoneUtilities.isPubliclyConnected(fusion, stone))
				PhilosophersStoneUtilities.publiclyConnect(copy, stone);
			
			if(PhilosophersStoneUtilities.isPrivatelyConnected(fusion, stone))
				PhilosophersStoneUtilities.privatelyConnect(copy, stone);
			
			if(PhilosophersStoneUtilities.isPubliclyConnected(stone, fusion))
				PhilosophersStoneUtilities.publiclyConnect(stone, copy);
			
			if(PhilosophersStoneUtilities.isPrivatelyConnected(stone, fusion))
				PhilosophersStoneUtilities.privatelyConnect(stone, copy);
		}
		
		return copy;
	}
	
	public static boolean isIsolated(Element element) {
		
		Element current = element;
		
		while(true) {
			
			if(current.content.equalsIgnoreCase("Isolate"))
				return true;
			
			if(current.parent == null)
				break;
			
			current = current.parent;
		}
		
		return false;
	}
}