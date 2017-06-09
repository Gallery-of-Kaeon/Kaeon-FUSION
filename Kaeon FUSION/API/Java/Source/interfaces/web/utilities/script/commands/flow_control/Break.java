package interfaces.web.utilities.script.commands.flow_control;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Break extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Break");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "if(!(" + processed.get(0) + "))break;";
	}
}