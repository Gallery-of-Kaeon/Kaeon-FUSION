package kaeon_fusion_legacy.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Insert extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Insert");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		int index = Integer.parseInt("" + processed.get(1)) - 1;
		
		while(list.size() < index)
			list.add(null);
		
		list.add(index, processed.get(2));
		
		return null;
	}
}