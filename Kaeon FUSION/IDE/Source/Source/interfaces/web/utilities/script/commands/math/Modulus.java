package interfaces.web.utilities.script.commands.math;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Modulus extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Modulus");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "(";
		
		for(int i = 0; i < processed.size() - 1; i++)
			script += processed.get(i) + "%";
		
		return script + processed.get(processed.size() - 1) + ")";
	}
}