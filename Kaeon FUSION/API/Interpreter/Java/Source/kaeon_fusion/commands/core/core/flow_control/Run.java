package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Run extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Run");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {

		try {
			Runtime.getRuntime().exec("" + processed.get(0));
		}
		
		catch(Exception exception) {
			
		}
		
		return null;
	}
}