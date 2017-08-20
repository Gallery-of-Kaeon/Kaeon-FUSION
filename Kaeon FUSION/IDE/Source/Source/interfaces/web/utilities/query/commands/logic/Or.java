package interfaces.web.utilities.query.commands.logic;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Or extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Or");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return processed.get(0) + " OR " + processed.get(1);
	}
}