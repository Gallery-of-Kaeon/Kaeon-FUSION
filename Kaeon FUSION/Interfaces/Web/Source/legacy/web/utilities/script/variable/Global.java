package legacy.web.utilities.script.variable;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Global extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Global");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return processed.get(0);
	}
}