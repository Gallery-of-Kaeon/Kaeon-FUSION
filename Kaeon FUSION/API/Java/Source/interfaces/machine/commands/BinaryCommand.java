package interfaces.machine.commands;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class BinaryCommand extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Binary");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		String binary = "";
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(processed.get(i) != null)
				binary += (char) Integer.parseInt("" + processed.get(i), 2);
		}
		
		return binary;
	}
}