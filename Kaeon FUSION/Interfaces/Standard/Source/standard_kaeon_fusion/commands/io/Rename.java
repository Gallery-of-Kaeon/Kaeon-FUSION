package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Rename extends FUSIONUnit {
	
	public Rename() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Rename");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		new File("" + processed.get(0)).renameTo(new File("" + processed.get(1)));
		
		return null;
	}
}