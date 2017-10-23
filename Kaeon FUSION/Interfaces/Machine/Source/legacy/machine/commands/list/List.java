package legacy.machine.commands.list;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class List extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("List");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String list = "{";
		
		for(int i = 0; i < processed.size(); i++)
			list += processed.get(i);
		
		return list + "}";
	}
}