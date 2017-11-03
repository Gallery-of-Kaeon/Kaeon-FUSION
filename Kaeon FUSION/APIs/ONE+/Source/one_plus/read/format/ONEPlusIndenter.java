package one_plus.read.format;

import java.util.ArrayList;

public class ONEPlusIndenter {
	
	public static String getIndent(ArrayList<String> file) {
		
		for(int i = 0; i < file.size(); i++) {
			
			if(file.get(i).charAt(0) == '\t')
				return "\t";
			
			else if(file.get(i).charAt(0) == ' ') {
				
				String indent = " ";
				
				for(int j = 1; j < file.get(i).length(); j++) {
					
					if(file.get(i).charAt(j) == ' ')
						indent += ' ';
					
					else
						break;
				}
				
				return indent;
			}
		}
		
		return "\t";
	}
	
	public static int getNumIndents(String line, String indent) {
		
		int indents = 0;
		int index = 0;
		
		if(line.length() < indent.length())
			return 0;
		
		while(line.substring(index, index + indent.length()).equals(indent)) {
			
			indents++;
			index += indent.length();
			
			if(index >= line.length() - 1)
				return indents;
		}
		
		return indents;
	}
	
	public static String indentLine(String indent, int numIndents) {
		
		String indents = "";
		
		for(int i = 0; i < numIndents; i++)
			indents += indent;
		
		return indents;
	}
}