package kaeon_fusion_legacy.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class This extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("This");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return getKaeonFUSION();
	}
}