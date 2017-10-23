package legacy.web.utilities.process.commands.console;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Log extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Log");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "echo ";
		
		for(int i = 0; i < processed.size(); i++) {
			
			script += processed.get(i);
			
			if(i < processed.size() - 1)
				script += ",";
		}
		
		return script + ";";
	}
}