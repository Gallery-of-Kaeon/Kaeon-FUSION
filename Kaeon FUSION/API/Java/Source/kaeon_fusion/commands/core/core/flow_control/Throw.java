package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Throw extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Throw");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		getKaeonFUSION().triggerError(true);
		
		return null;
	}
}