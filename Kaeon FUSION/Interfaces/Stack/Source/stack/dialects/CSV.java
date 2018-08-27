package stack.dialects;

import java.util.ArrayList;

import build_dialect.BuildDialect;
import one.Element;

public class CSV extends BuildDialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		String data = "";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(element.children.get(i));
				
				if(injection != null)
					data += injection;
			}
			
			else {
				
				for(int j = 0; j < element.children.get(i).children.size(); j++) {
					
					if(element.children.get(i).children.get(j).content.equalsIgnoreCase("Meta")) {
						
						String injection = getInjection(element.children.get(i).children.get(j));
						
						if(injection != null)
							data += injection;
					}
					
					else {
						
						data +=
								"\"" +
								element.children.get(i).children.get(j).content.
										replaceAll("\\\"", "\\\"\\\"") +
								"\"";
						
						if(j < element.children.get(i).children.size() - 1)
							data += ",";
					}
				}
				
				if(i < element.children.size() - 1)
					data += "\n";
			}
		}
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "data";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".csv");
		file.add(data);
		
		files.add(file);
	}
}