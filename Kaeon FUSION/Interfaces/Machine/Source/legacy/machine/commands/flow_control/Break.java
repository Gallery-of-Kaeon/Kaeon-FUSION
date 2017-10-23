package legacy.machine.commands.flow_control;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

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