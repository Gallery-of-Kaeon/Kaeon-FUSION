package standard.utilities;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
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
				
				catch (Exception e) {
					
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
}