package aether_test;

import fusion.FUSION;
import philosophers_stone.PhilosophersStoneUtilities;
import test.Test;

public class FUSIONInterface {

	public static void addInterface(FUSION fusion) {
		PhilosophersStoneUtilities.publiclyConnectMutually(fusion, new Test());
	}
}