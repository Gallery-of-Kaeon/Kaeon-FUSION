package legacy.kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

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