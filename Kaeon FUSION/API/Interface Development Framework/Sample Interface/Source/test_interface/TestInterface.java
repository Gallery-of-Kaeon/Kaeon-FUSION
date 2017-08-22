package test_interface;

import kaeon_fusion.KaeonFUSION;
import kaeon_fusion.command.Command;
import kaeon_fusion.interfaces.KaeonFUSIONInterface;
import philosophers_stone.PhilosophersStoneUtilities;
import test_interface.commands.TestCommand;

public class TestInterface extends KaeonFUSIONInterface {
	
	public void connect(KaeonFUSION kaeonFUSION) {
		add(kaeonFUSION, new TestCommand());
	}
	
	private void add(KaeonFUSION kaeonFUSION, Command command) {
		
		PhilosophersStoneUtilities.publiclyConnectMutually(kaeonFUSION, command);
		
		command.add();
	}
}