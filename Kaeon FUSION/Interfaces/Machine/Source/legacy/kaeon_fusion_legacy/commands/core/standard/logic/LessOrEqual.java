package legacy.kaeon_fusion_legacy.commands.core.standard.logic;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class LessOrEqual extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Less or Equal");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		return
				Double.parseDouble("" + processed.get(0)) <=
				Double.parseDouble("" + processed.get(1));
	}
}