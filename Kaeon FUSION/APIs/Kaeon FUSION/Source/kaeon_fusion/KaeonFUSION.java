package kaeon_fusion;

import fusion.FUSION;
import kaeon_fusion.literal.Literal;
import kaeon_fusion.use.Use;
import philosophers_stone.PhilosophersStoneUtilities;

public class KaeonFUSION extends FUSION {

	public KaeonFUSION() {
		
		tags.add("Kaeon FUSION");

		PhilosophersStoneUtilities.publiclyConnectMutually(this, new Literal());
		PhilosophersStoneUtilities.publiclyConnectMutually(this, new Use());
	}
}