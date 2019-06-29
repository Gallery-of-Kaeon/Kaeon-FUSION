package standard_kaeon_fusion.commands.io;

import java.nio.file.Paths;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class LocalDirectory extends FUSIONUnit {
	
	public LocalDirectory() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Local Directory");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return "" + Paths.get("").toAbsolutePath();
	}
}