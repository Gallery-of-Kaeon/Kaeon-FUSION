package standard_kaeon_fusion.commands.system;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class OperatingSystem extends FUSIONUnit {
	
	public OperatingSystem() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Operating System");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return System.getProperty("os.name");
	}
}