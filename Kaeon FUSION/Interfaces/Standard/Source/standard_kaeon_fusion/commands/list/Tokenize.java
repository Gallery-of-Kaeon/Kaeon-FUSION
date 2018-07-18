package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;
import java.util.Arrays;

import fusion.FUSIONUnit;
import one.Element;
import tokenizer.Tokenizer;

public class Tokenize extends FUSIONUnit {
	
	public Tokenize() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Tokenize");
	}
	
	@SuppressWarnings({ "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> sub = new ArrayList<Object>();
			ArrayList<Object> current = new ArrayList<Object>();
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			
			for(int i = 0; i < list.size(); i++) {
				
				boolean token = false;
				
				for(int j = 1; j < processed.size(); j++) {
					
					if(list.get(i).equals(processed.get(j))) {
						
						token = true;
						
						break;
					}
				}
				
				if(token) {
					
					if(current.size() > 0) {
						
						sub.add(current);
						
						current = new ArrayList<Object>();
					}
					
					sub.add(new ArrayList<Object>(Arrays.asList(list.get(i))));
				}
				
				else
					current.add(list.get(i));
			}
			
			if(current.size() > 0)
				sub.add(current);
			
			return sub;
		}
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		for(int i = 1; i < processed.size(); i++)
			tokens.add("" + processed.get(i));
		
		return Tokenizer.tokenize(tokens, "" + processed.get(0));
	}
}