package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class AbsolutePath extends FUSIONUnit {
	
	public AbsolutePath() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Absolute Path");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return new File("" + processed.get(0)).getAbsolutePath();
	}
}