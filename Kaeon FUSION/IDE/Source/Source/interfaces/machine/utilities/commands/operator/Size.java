package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Size extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Size");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "sizeof(" + processed.get(0) + ")";
	}
}