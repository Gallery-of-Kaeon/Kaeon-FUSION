package interfaces.web.utilities.process.commands.flow_control;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Break extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Break");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "if(!(" + processed.get(0) + "))break;";
	}
}