package legacy.machine.commands.flow_control;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Loop extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Loop");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 0)
			return "continue;";
		
		return "if(" + processed.get(0) + ")continue;";
	}
}