package dev;

import java.util.ArrayList;

import dev.ide.IDE;
import fusion_interface.ACEInterface;
import interfaces.StandardInterfacePack;
import io.IO;
import kaeon_fusion.KaeonFUSION;
import kaeon_fusion.interface_module.Interface;
import kaeon_fusion.super_mode.SuperMode;
import one_plus.ONEPlus;

public class KaeonDev {
	
	public static void main(String[] args) {
		
		if(args.length == 0)
			new IDE();

		else {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			ArrayList<Interface> standard = StandardInterfacePack.getInterfaces();
			
			for(int i = 0; i < standard.size(); i++)
				fusion.publiclyConnectMutually(standard.get(i));
			
			fusion.publiclyConnectMutually(new ACEInterface());
			
			try {
				
				String text = IO.openAsString(args[0]);
				ONEPlus code = new ONEPlus(text);
				
				if(text.indexOf("[SUPER]") != -1)
					SuperMode.superMode(code);
				
				ArrayList<Object> arguments = new ArrayList<Object>();
				
				for(int i = 1; i < args.length; i++)
					arguments.add(args[i]);
				
				fusion.process(code, arguments);
			}
			
			catch(Exception exception) {
				
			}
		}
	}
}