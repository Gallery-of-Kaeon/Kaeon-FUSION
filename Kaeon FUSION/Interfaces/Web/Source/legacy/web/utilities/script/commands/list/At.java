package legacy.web.utilities.script.commands.list;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class At extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("At");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return processed.get(0) + "[" + processed.get(1) + "]";
	}
}