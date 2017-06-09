package interfaces.audio;

import interfaces.audio.commands.Sound;
import kaeon_fusion.interface_module.Interface;

public class Audio extends Interface {
	
	public String getName() {
		return "Audio";
	}
	
	public void onStart() {
		publiclyConnectMutually(new Sound());
	}
	
	public Interface clone() {
		
		Audio audio = new Audio();
		audio.publiclyConnectMutually(new Sound());
		
		return audio;
	}
}