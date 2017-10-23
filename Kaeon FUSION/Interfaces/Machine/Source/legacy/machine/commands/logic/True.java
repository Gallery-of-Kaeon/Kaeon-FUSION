package legacy.machine.commands.logic;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class True extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("True");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return 1;
	}
}