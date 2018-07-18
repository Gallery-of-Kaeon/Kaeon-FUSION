package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class ParentDirectory extends FUSIONUnit {
	
	public ParentDirectory() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Parent Directory");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		File file = new File("" + processed.get(0));
		int parentNest = 1;
		
		if(processed.size() > 1)
			parentNest = Integer.parseInt("" + processed.get(1));
		
		while(parentNest > 0) {
			
			File parent = file.getParentFile();
			
			if(parent != null)
				file = parent;
			
			else
				break;
			
			parentNest--;
		}
		
		return file.getAbsolutePath();
	}
}