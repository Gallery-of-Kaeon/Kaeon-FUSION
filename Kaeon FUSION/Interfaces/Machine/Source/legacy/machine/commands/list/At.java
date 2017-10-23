package legacy.machine.commands.list;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class At extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("At");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(element.getElement(0).getContent().equalsIgnoreCase("Arguments"))
			return "arg" + processed.get(1);
		
		return processed.get(0) + "[(" + processed.get(1) + ")-1]";
	}
}