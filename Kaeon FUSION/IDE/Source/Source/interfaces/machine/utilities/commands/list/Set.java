package interfaces.machine.utilities.commands.list;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Set extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Set");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return processed.get(0) + "[(" + processed.get(1) + ")-1]=" + processed.get(2) + ";";
	}
}