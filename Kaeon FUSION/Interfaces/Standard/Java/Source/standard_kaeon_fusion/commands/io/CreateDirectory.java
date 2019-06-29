package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class CreateDirectory extends FUSIONUnit {
	
	public CreateDirectory() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Create Directory");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return new File("" + processed.get(0)).mkdirs();
	}
}