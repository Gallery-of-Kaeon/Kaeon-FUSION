package kaeon_fusion_interface;

import kaeon_fusion.interfaces.KaeonFUSIONInterface;
import test_interface.TestInterface;

public class KaeonFUSIONInterfaceModule {
	
	public KaeonFUSIONInterface getInterface() {
		return new TestInterface();
	}
}