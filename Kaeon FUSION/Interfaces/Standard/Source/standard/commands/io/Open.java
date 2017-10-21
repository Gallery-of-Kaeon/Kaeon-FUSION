package standard.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import io.IO;
import one.Element;

public class Open extends FUSIONUnit {
	
	public Open() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Open");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return IO.openAsString("" + processed.get(0));
	}
}