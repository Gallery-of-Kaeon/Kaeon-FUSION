package kaeon_fusion.commands.core.standard.logic;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class GreaterOrEqual extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Greater or Equal");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		return
				Double.parseDouble("" + processed.get(0)) >=
				Double.parseDouble("" + processed.get(1));
	}
}