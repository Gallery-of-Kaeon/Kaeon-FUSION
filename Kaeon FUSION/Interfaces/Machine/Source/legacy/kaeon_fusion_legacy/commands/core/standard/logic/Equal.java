package legacy.kaeon_fusion_legacy.commands.core.standard.logic;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Equal extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Equal");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return ("" + processed.get(0)).equals("" + processed.get(1));
	}
}