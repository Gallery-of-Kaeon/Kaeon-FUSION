package interfaces.web.utilities.process.commands.flow_control;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Return extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Return");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "return " + processed.get(0) + ";";
	}
}