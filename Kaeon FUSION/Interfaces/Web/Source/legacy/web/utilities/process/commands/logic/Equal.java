package legacy.web.utilities.process.commands.logic;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Equal extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Equal");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "(";
		
		for(int i = 0; i < processed.size() - 1; i++)
			script += processed.get(i) + "===";
		
		return script + processed.get(processed.size() - 1) + ")";
	}
}