package kaeon_fusion_legacy.commands.core.standard.string;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Length extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Length");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).length();
	}
}