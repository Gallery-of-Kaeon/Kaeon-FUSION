package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Separator extends FUSIONUnit {
	
	public Separator() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Separator");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return File.separator;
	}
}