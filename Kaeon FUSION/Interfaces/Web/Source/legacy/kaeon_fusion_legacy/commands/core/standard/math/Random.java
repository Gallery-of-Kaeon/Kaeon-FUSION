package legacy.kaeon_fusion_legacy.commands.core.standard.math;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Random extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Random");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return Math.random();
	}
}