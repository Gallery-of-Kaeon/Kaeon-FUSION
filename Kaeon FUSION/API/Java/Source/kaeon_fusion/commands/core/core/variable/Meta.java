package kaeon_fusion.commands.core.core.variable;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Meta extends Command {

	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Meta");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
}