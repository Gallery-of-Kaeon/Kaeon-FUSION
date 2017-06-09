package kaeon_fusion.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

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