package interfaces.web.utilities.process.commands.console;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class LogLine extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Log Line");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "echo ";
		
		for(int i = 0; i < processed.size(); i++) {
			
			script += processed.get(i);
			
			if(i < processed.size() - 1)
				script += ",";
		}
		
		script += ",\"\\n\"";
		
		return script + ";";
	}
}