package interfaces.machine.utilities.commands.logic;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class False extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("False");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return 0;
	}
}