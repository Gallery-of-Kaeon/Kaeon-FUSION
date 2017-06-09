package interfaces.machine.utilities.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Not extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Not");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "!(" + processed.get(0) + ")";
	}
}