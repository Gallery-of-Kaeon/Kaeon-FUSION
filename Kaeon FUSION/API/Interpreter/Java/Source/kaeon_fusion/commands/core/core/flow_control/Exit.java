package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

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