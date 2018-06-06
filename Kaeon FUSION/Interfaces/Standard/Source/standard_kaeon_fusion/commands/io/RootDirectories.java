package standard_kaeon_fusion.commands.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import fusion.FUSIONUnit;
import one.Element;

public class RootDirectories extends FUSIONUnit {
	
	public RootDirectories() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Root Directories");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<File> roots = new ArrayList<File>(Arrays.asList(File.listRoots()));
		ArrayList<String> rootPaths = new ArrayList<String>();
		
		for(int i = 0; i < roots.size(); i++)
			rootPaths.add("" + roots.get(i));
		
		return rootPaths;
	}
}