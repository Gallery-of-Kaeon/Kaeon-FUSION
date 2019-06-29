package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class FileSize extends FUSIONUnit {
	
	public FileSize() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("File Size");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		return
				processed.size() == 1 ?
						new File("" + processed.get(0)).length() :
						Boolean.parseBoolean("" + processed.get(1)) ?
								new File("" + processed.get(0)).length() :
								new File("" + processed.get(0)).length();
	}
}