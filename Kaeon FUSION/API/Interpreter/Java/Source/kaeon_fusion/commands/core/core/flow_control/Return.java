package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Return extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Return");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		getKaeonFUSION().returnObject(processed.get(0));
		
		return null;
	}
}