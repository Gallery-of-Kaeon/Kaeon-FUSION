package legacy.kaeon_fusion_legacy.commands.core.core.variable;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Meta extends Command {

	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Meta");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
}