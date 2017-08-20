package kaeon_fusion_legacy.commands.core.standard.system;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Time extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Time");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return System.currentTimeMillis() / 1000;
	}
}