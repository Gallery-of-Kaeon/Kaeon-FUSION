package legacy.kaeon_fusion_legacy.commands.core.standard.io;

import java.util.ArrayList;

import io.IO;
import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Open extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Open");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return IO.openAsString("" + processed.get(0));
	}
}