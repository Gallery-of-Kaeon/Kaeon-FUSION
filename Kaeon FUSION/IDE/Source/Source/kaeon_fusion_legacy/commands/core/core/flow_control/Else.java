package kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Else extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Else");
	}
	
	public boolean onDescend(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Break");
		
		return ((Break) (get(tags).get(0))).broke();
	}
}