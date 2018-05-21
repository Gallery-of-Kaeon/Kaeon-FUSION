package standard_kaeon_fusion.utilities;

import java.util.ArrayList;
import java.util.Collections;

public class SortList implements Comparable<SortList> {
	
	public boolean numerical = true;
	
	public ArrayList<Object> list = new ArrayList<Object>();
	public int key = 0;
	
	public int compareTo(SortList sortList) {

		if(key >= list.size() || sortList.key >= sortList.list.size())
			return 0;
		
		if(numerical) {
			
			Double a = Double.parseDouble("" + list.get(key));
			Double b = Double.parseDouble("" + sortList.list.get(sortList.key));
			
			return a.compareTo(b);
		}
		
		else
			return ("" + list.get(key)).compareTo("" + sortList.list.get(sortList.key));
	}
	
	public static String sortString(String string, boolean numerical) {
		
		if(numerical) {
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int i = 0; i < string.length(); i++)
				list.add(Integer.parseInt("" + string.charAt(i)));
			
			string = "";
			
			Collections.sort(list);
			
			for(int i = 0; i < list.size(); i++)
				string += list.get(i);
			
			return string;
		}
		
		else {
			
			ArrayList<String> list = new ArrayList<String>();
			
			for(int i = 0; i < string.length(); i++)
				list.add("" + string.charAt(i));
			
			string = "";
			
			Collections.sort(list);
			
			for(int i = 0; i < list.size(); i++)
				string += list.get(i);
			
			return string;
		}
	}
}