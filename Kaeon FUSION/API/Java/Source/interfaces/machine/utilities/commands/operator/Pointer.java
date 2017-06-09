package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Pointer extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Pointer");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "(*(" + processed.get(0) + "))";
	}
}