package interfaces.machine;

import interfaces.machine.commands.BinaryCommand;
import interfaces.machine.commands.BuildNativeCode;
import kaeon_fusion_legacy.interface_module.Interface;

public class Machine extends Interface {
	
	public String getName() {
		return "Machine";
	}
	
	public void onStart() {
		
		publiclyConnectMutually(new BinaryCommand());
		
		publiclyConnectMutually(new BuildNativeCode());
	}
	
	public Interface clone() {
		
		Machine machine = new Machine();
		
		machine.publiclyConnectMutually(new BinaryCommand());
		
		publiclyConnectMutually(new BuildNativeCode());
		
		return machine;
	}
}