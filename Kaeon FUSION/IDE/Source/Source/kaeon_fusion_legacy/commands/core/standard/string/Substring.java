package kaeon_fusion_legacy.commands.core.standard.string;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Substring extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Substring");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		String string = "" + processed.get(0);
		
		if(processed.size() == 2)
			return string.substring(Integer.parseInt("" + processed.get(1)) - 1);
		
		return
				string.substring(
						Integer.parseInt("" + processed.get(1)) - 1,
						Integer.parseInt("" + processed.get(2)) - 1);
	}
}