package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class AppendAll extends FUSIONUnit {
	
	public AppendAll() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Append All");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {

			ArrayList<Object> list = (ArrayList) processed.get(0);
			
			for(int i = 1; i < processed.size(); i++) {
				
				if(processed.get(i) instanceof ArrayList)
					list.addAll((ArrayList<Object>) processed.get(i));
				
				else
					list.addAll(ConvertSequence.stringToList("" + processed.get(i)));
			}
			
			return list;
		}
		
		String string = "" + processed.get(0);
		
		for(int i = 1; i < processed.size(); i++) {
			
			if(processed.get(i) instanceof ArrayList)
				string += ConvertSequence.listToString((ArrayList<Object>) processed.get(i));
			
			else
				string += processed.get(i);
		}
		
		return string;
	}
}