package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Cast extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Cast");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "((" + processed.get(1) + ")(" + processed.get(0) + "))";
	}
}