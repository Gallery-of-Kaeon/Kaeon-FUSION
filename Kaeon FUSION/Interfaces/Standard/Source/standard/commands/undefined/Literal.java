package standard.commands.undefined;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.state.State;

public class Literal extends FUSIONUnit {
	
	FUSION fusion;
	
	public Literal() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(element.children.size() > 0)
			return false;
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		for(FUSIONUnit command : fusion.fusionUnits) {
			
			if(command != this && !(command instanceof State)) {
				
				if(command.verify(element))
					return false;
			}
		}
		
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return format(element.content);
	}
	
	public String format(String string) {
		
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) == '\"') {
				
				string = string.substring(0, i) + string.substring(i + 1);
				
				i--;
				continue;
			}
			
			if(string.charAt(i) == '\\') {
				
				if(i < string.length() - 1) {
					
					if(string.charAt(i + 1) == 'n')
						string = string.substring(0, i) + '\n' + string.substring(i + 2);
					
					else if(string.charAt(i + 1) == 't')
						string = string.substring(0, i) + '\t' + string.substring(i + 2);
					
					else if(string.charAt(i + 1) == '\\')
						i++;
					
					else
						string = string.substring(0, i) + string.substring(i + 1);
				}
				
				else
					string = string.substring(0, i) + string.substring(i + 1);
				
				continue;
			}
		}
		
		return string;
	}
}