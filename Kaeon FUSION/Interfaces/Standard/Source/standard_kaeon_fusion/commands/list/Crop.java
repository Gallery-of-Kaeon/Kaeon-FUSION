package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Crop extends FUSIONUnit {
	
	public Crop() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Crop");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		int from = Integer.parseInt("" + processed.get(1)) - 1;
		int to = Integer.parseInt("" + processed.get(2)) - 1;
		
		if(from <= to) {
			
			if(processed.get(0) instanceof ArrayList)
				return ((ArrayList<Object>) processed.get(0)).subList(from, to);
			
			return ("" + processed.get(0)).substring(from, to);
		}
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = ((ArrayList<Object>) processed.get(0));
			ArrayList<Object> crop = new ArrayList<Object>();
			
			for(int i = from; i > to; i--)
				crop.add(list.get(i));
			
			return crop;
		}
		
		String string = ("" + processed.get(0));
		String crop = "";
		
		for(int i = from; i > to; i--)
			crop += string.charAt(i);
		
		return crop;
	}
}