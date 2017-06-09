package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Reference extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Reference");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return element.getElement(0).getContent();
	}
}