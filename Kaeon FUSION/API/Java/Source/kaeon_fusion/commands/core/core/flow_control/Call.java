package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Call extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Call");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return call(processed);
	}
}