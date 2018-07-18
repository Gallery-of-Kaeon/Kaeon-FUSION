package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Delete extends FUSIONUnit {
	
	public Delete() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Delete");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		new File("" + processed.get(0)).delete();
		
		return null;
	}
}