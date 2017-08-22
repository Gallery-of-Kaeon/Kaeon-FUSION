package kaeon_fusion.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Set extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Set");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		int index = Integer.parseInt("" + processed.get(1)) - 1;

		while(list.size() < index)
			list.add(null);
		
		list.set(index, processed.get(2));
		
		return null;
	}
}