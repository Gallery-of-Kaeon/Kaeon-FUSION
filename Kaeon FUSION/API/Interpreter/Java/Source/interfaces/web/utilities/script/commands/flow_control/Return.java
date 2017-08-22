package interfaces.web.utilities.script.commands.flow_control;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Return extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Return");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "return " + processed.get(0) + ";";
	}
}