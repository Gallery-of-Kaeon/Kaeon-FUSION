package dev;

import java.util.ArrayList;

import dev.ide.DevKaeonFUSION;
import dev.ide.IDE;
import interfaces.StandardInterfacePack;
import io.IO;
import kaeon_fusion_legacy.KaeonFUSION;
import kaeon_fusion_legacy.interface_module.Interface;
import kaeon_fusion_legacy.super_mode.SuperMode;
import legacy.one_plus.ONEPlus;

public class KaeonDev {

	public static void main(String[] args) {

		if(args.length == 0) {
			
			String manifest = IO.openAsString("Manifest.op");
			
			if(manifest == null)
				new IDE();
			
			else {
				
				try {
					
					try {
						
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					    	
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					}
					
					catch (Exception exception) {
						
					}
					
					String main = IO.openAsString(one_plus.ONEPlus.parseONEPlus(manifest).children.get(0).content);
					ONEPlus code = new ONEPlus(main);
					
					if(main.indexOf("[SUPER]") != -1)
						SuperMode.superMode(code);
					
					DevKaeonFUSION fusion = new DevKaeonFUSION();
					
					ArrayList<Interface> standard = StandardInterfacePack.getInterfaces();
					
					for(int i = 0; i < standard.size(); i++)
						fusion.publiclyConnectMutually(standard.get(i));
					
					fusion.process(code);
				}
				
				catch(Exception exception) {
					
				}
			}
		}

		else {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			ArrayList<Interface> standard = StandardInterfacePack.getInterfaces();
			
			for(int i = 0; i < standard.size(); i++)
				fusion.publiclyConnectMutually(standard.get(i));
			
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