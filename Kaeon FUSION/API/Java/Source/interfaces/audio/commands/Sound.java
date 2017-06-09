package interfaces.audio.commands;

import java.util.ArrayList;

import interfaces.audio.utilities.AudioUtil;
import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Sound extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Sound");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 1)
			new AudioUtil("" + processed.get(0)).play();

		else
			new AudioUtil("" + processed.get(0)).play(Integer.parseInt("" + processed.get(1)));
		
		return null;
	}
}