package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import fusion.FUSIONUnit;
import one.Element;

public class Directory extends FUSIONUnit {
	
	public Directory() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Directory");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		String directory = "" + processed.get(0);
		
		ArrayList<String> files = new ArrayList<String>(Arrays.asList(new File(directory).list()));
		ArrayList<String> directories = new ArrayList<String>();
		
		for(int i = 0; i < files.size(); i++) {
			
			if(new File(directory + File.separator + files.get(i)).isDirectory()) {
				
				directories.add(files.remove(i));
				
				i--;
			}
		}
		
		return new ArrayList<Object>(Arrays.asList(directories, files));
	}
}