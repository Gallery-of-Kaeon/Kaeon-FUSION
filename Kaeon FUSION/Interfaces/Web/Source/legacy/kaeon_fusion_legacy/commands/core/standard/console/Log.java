package legacy.kaeon_fusion_legacy.commands.core.standard.console;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Log extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Log");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> packet = new ArrayList<Object>();
		
		packet.add("Console Out");
		packet.add(processed);
		
		call(packet);
		
		return null;
	}
}