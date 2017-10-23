package legacy.kaeon_fusion_legacy.commands.core.core.flow_control;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Scope extends Command {
	
	public boolean onVerify(Element element) {
		
		return
				element.getContent().equalsIgnoreCase("Scope") ||
				element.getContent().equalsIgnoreCase("Null");
	}
}