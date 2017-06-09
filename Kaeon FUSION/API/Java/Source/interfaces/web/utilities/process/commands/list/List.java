package interfaces.web.utilities.process.commands.list;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class List extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("List");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "array(";
		
		for(int i = 0; i < processed.size(); i++) {
			
			script += processed.get(i);
			
			if(i < processed.size() - 1)
				script += ",";
		}
		
		return script + ")";
	}
}