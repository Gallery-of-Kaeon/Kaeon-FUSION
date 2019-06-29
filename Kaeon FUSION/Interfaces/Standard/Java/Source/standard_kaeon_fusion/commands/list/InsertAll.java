package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class InsertAll extends FUSIONUnit {
	
	public InsertAll() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Insert All");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		int index = (Integer.parseInt("" + processed.get(1))) - 1;
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = (ArrayList) processed.get(0);
			
			while(list.size() < index)
				list.add(null);
			
			ArrayList<Object> insert = new ArrayList<Object>();
			
			for(int i = 2; i < processed.size(); i++) {
				
				if(processed.get(i) instanceof ArrayList)
					insert.addAll((ArrayList<Object>) processed.get(i));
				
				else
					insert.addAll(ConvertSequence.stringToList("" + processed.get(i)));
			}
			
			list.addAll(index, insert);
			
			return list;
		}
		
		String string = "" + processed.get(0);
		
		while(string.length() < index)
			string += " ";
		
		String insert = "";
		
		for(int i = 2; i < processed.size(); i++) {
			
			if(processed.get(i) instanceof ArrayList)
				insert += ConvertSequence.listToString((ArrayList<Object>) processed.get(i));
			
			else
				insert += processed.get(i);
		}
		
		return string.substring(0, index) + insert + string.substring(index);
	}
}