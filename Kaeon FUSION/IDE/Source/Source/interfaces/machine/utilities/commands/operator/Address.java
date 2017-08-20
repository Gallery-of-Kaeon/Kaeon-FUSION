package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Address extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Address");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "(&(" + processed.get(0) + "))";
	}
}