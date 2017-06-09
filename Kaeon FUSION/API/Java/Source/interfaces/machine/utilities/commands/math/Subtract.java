package interfaces.machine.utilities.commands.math;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Subtract extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Subtract");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "(";
		
		for(int i = 0; i < processed.size() - 1; i++)
			script += processed.get(i) + "-";
		
		return script + processed.get(processed.size() - 1) + ")";
	}
}