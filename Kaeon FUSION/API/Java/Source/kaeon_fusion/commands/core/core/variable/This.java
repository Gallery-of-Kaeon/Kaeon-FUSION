package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class This extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("This");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return getKaeonFUSION();
	}
}