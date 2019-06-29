package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;
import java.util.Collections;

import fusion.FUSIONUnit;
import one.Element;
import standard_kaeon_fusion.utilities.SortList;

public class SortAlphabetical extends FUSIONUnit {
	
	public SortAlphabetical() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Sort Alphabetical");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			ArrayList<SortList> sortLists = new ArrayList<SortList>();
			
			if(processed.size() == 1) {
				
				for(int i = 0; i < list.size(); i++) {
					
					ArrayList<Object> sortListObjects = new ArrayList<Object>();
					sortListObjects.add(list.get(i));
					
					SortList sortList = new SortList();
					
					sortList.list = sortListObjects;
					sortList.key = 0;
					sortList.numerical = false;
					
					sortLists.add(sortList);
				}
				
				Collections.sort(sortLists);
				
				for(int i = 0; i < sortLists.size(); i++)
					((ArrayList<Object>) processed.get(0)).set(i, sortLists.get(i).list.get(0));
			}
			
			else {
				
				int givenKey = Integer.parseInt("" + processed.get(1)) - 1;
				
				for(int i = 0; i < list.size(); i++) {
					
					SortList sortList = new SortList();
					
					sortList.list = (ArrayList<Object>) list.get(i);
					sortList.key = givenKey;
					sortList.numerical = false;
					
					sortLists.add(sortList);
				}
				
				Collections.sort(sortLists);
				
				for(int i = 0; i < sortLists.size(); i++)
					((ArrayList<Object>) processed.get(0)).set(i, sortLists.get(i).list);
			}
			
			return processed.get(0);
		}
		
		return SortList.sortString("" + processed.get(0), false);
	}
}