package legacy.kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Exit extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Exit");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public int onChangeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		return 0;
	}
}