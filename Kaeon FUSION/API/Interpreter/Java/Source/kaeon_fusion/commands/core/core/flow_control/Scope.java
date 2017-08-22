package kaeon_fusion.commands.core.core.flow_control;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Scope extends Command {
	
	public boolean onVerify(Element element) {
		
		return
				element.getContent().equalsIgnoreCase("Scope") ||
				element.getContent().equalsIgnoreCase("Null");
	}
}