package kaeon_fusion_legacy.commands.core.standard.string;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Character extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Character");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).charAt(Integer.parseInt("" + processed.get(1)) - 1);
	}
}