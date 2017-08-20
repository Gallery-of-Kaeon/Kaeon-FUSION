package kaeon_fusion_legacy.commands.core.standard.io;

import java.util.ArrayList;

import io.IO;
import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Save extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Save");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		IO.save("" + processed.get(1), "" + processed.get(0));
		
		return null;
	}
}