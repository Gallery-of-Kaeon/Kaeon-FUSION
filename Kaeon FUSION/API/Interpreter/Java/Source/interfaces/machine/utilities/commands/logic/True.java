package interfaces.machine.utilities.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class True extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("True");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return 1;
	}
}