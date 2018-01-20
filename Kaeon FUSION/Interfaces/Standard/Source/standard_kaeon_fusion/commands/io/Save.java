package standard_kaeon_fusion.commands.io;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import io.IO;
import one.Element;

public class Save extends FUSIONUnit {
	
	public Save() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Save");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		IO.save("" + processed.get(1), "" + processed.get(0));
		
		return null;
	}
}