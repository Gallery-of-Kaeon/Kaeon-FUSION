package interfaces.machine.utilities.commands.flow_control;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Break extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Break");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 0)
			return "break;";
		
		return "if(" + processed.get(0) + ")break;";
	}
}