package kaeon_fusion.commands.core.standard.io;

import java.util.ArrayList;

import io.IO;
import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Open extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Open");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return IO.openAsString("" + processed.get(0));
	}
}