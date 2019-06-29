package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Rank extends FUSIONUnit {
	
	public Rank() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Rank");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {

		ArrayList<Integer> count = new ArrayList<Integer>();
		ArrayList<Object> unique = new ArrayList<Object>();
		
		ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
		
		for(int i = 0; i < list.size(); i++) {
			
			boolean found = false;
			
			for(int j = 0; j < unique.size(); j++) {
				
				if(list.get(i).equals(unique.get(j))) {
					
					count.set(j, count.get(j) + 1);
					found = true;
					
					break;
				}
			}
			
			if(!found) {
				unique.add(list.get(i));
				count.add(1);
			}
		}
		
		ArrayList<Object> lists = new ArrayList<Object>();
		
		for(int i = 0; i < unique.size(); i++) {
			
			int countNumber = count.get(i);
			
			while(lists.size() < countNumber)
				lists.add(new ArrayList<Object>());
			
			((ArrayList<Object>) lists.get(countNumber - 1)).add(unique.get(i));
		}
		
		for(int i = 0; i < lists.size(); i++) {
			
			if(((ArrayList<Object>) lists.get(i)).size() == 0) {
				
				lists.remove(i);
				
				i--;
			}
		}
		
		return lists;
	}
}