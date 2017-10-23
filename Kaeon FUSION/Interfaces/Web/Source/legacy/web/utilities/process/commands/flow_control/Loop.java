package legacy.web.utilities.process.commands.flow_control;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Loop extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Loop");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "if(" + processed.get(0) + ")continue;";
	}
}