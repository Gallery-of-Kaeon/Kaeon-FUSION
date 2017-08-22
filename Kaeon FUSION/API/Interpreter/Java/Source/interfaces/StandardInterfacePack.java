package interfaces;

import java.util.ArrayList;

import interfaces.audio.Audio;
import interfaces.machine.Machine;
import interfaces.web.Web;
import kaeon_fusion.interface_module.Interface;

public class StandardInterfacePack {
	
	public static ArrayList<Interface> getInterfaces() {
		
		ArrayList<Interface> interfaces = new ArrayList<Interface>();
		
		interfaces.add(new Audio());
		interfaces.add(new Machine());
		interfaces.add(new Web());
		
		return interfaces;
	}
}