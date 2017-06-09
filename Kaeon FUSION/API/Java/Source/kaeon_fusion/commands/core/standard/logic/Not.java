package kaeon_fusion.commands.core.standard.logic;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Not extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Not");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return !Boolean.parseBoolean("" + processed.get(0));
	}
}