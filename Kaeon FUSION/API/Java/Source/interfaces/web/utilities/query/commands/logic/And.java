package interfaces.web.utilities.query.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class And extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("And");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return processed.get(0) + " AND " + processed.get(1);
	}
}