package interfaces.web.utilities.process.commands.math;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Add extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Add");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "(";
		
		for(int i = 0; i < processed.size() - 1; i++)
			script += processed.get(i) + "+";
		
		return script + processed.get(processed.size() - 1) + ")";
	}
}