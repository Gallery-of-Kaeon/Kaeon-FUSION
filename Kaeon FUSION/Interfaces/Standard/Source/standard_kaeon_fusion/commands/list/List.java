package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.build.Build;
import standard_kaeon_fusion.commands.build.Derive;

public class List extends FUSIONUnit {
	
	public Build build;
	public Derive derive;
	
	public boolean verify(Element element) {
		
		if(element.content.equalsIgnoreCase("List"))
			return true;
		
		if(build == null)
			build = (Build) PhilosophersStoneUtilities.get(this, "Build").get(0);
		
		for(int i = 0; i < build.dialects.size(); i++) {
			
			if(element == build.dialects.get(i))
				return true;
		}
		
		if(derive == null)
			derive = (Derive) PhilosophersStoneUtilities.get(this, "Derive").get(0);
		
		for(int i = 0; i < derive.dialects.size(); i++) {
			
			if(element == derive.dialects.get(i))
				return true;
		}
		
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < build.dialects.size(); i++) {
			
			if(element == build.dialects.get(i)) {
				
				build.dialects.remove(i);
				
				break;
			}
		}
		
		for(int i = 0; i < derive.dialects.size(); i++) {
			
			if(element == derive.dialects.get(i)) {
				
				derive.dialects.remove(i);
				
				break;
			}
		}
		
		return processed;
	}
}