package interfaces.web.utilities.page.literal;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class Literal extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		
		if(element.getNumElements() != 0)
			return false;
		
		ArrayList<PhilosophersStonePlus> atlas = getAtlas();
		
		for(int i = 0; i < atlas.size(); i++) {
			
			if((atlas.get(i) instanceof FUSIONStone) && !(atlas.get(i) instanceof Literal)) {
					
				if(((FUSIONStone) atlas.get(i)).onVerify(element))
					return false;
			}
		}
		
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return format(element.getContent());
	}
	
	public String format(String string) {
		
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) == '\\') {

				string = string.substring(0, i) + string.substring(i + 1);
				
				if(i < string.length()) {
					
					if(string.charAt(i) == 'n')
						string = string.substring(0, i) + '\n' + string.substring(i + 1);
					
					if(string.charAt(i) == 't')
						string = string.substring(0, i) + '\t' + string.substring(i + 1);
				}
				
				continue;
			}
			
			if(string.charAt(i) == '\"') {
				
				string = string.substring(0, i) + string.substring(i + 1);
				
				i--;
				continue;
			}
		}
		
		return string;
	}
}