package legacy.kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Loop extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Loop");
	}
	
	public Element onChangeElement(Element element, ArrayList<Object> processed) {
		return null;
	}
}