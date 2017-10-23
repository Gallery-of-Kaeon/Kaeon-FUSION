package legacy.utilities.one_plus.read.format;

import java.util.ArrayList;

public class ONEPlusCropper {
		
	public static void cropONEPlus(ArrayList<String> file) {
		
		String indent = "\t";
		
		try {
			indent = ONEPlusIndenter.getIndent(file);
		}
		
		catch (Exception exception) {
			
		}
		
		boolean inComment = false;
		String elementDefinition = null;
		
		for(int i = 0; i < file.size(); i++) {
			
			String line = file.get(i);
			String trim = line.trim();
			
			if(trim.length() == 0) {
				
				if(elementDefinition == null) {
					file.remove(i);
					i--;
				}
				
				else
					file.set(i, ONEPlusIndenter.getIndent(file) + file.get(i));
				
				continue;
			}
			
			int numIndents = ONEPlusIndenter.getNumIndents(line, indent);
			
			String lineIndent = "";
			
			for(int j = 0; j < numIndents; j++)
				lineIndent += indent;
			
			String newLine = "";
			
			if(!inComment) {
				
				if(elementDefinition == null) {
					
					if(trim.equals("-"))
						elementDefinition = line;
				}
				
				else {
					
					if(elementDefinition.equals(line))
						elementDefinition = null;
				}
			}
			
			if(elementDefinition == null) {
				
				boolean inQuote = false;
				
				for(int j = 0; j < line.length(); j++) {
					
					if(!inQuote && !inComment && line.charAt(j) == '~') {
						
						if(j < line.length() - 1) {
							
							newLine += line.charAt(j + 1);
							j++;
							
							continue;
						}
						
						else
							break;
					}
					
					if(!inComment && line.charAt(j) == '\'')
						inQuote = !inQuote;
					
					if(!inQuote) {
						
						if(!inComment && line.charAt(j) == '#') {
							
							if(j < line.length() - 1) {
								
								if(line.charAt(j + 1) == '[')
									inComment = true;
								
								else
									break;
							}
							
							else
								break;
						}
						
						if(inComment && line.charAt(j) == ']' && j < line.length() - 1) {
							
							if(j < line.length() - 1) {
								
								if(line.charAt(j + 1) == '#') {
									
									inComment = false;
									j += 2;
									
									if(j >= line.length())
										break;
								}
							}
						}
					}
					
					if(!inComment)
						newLine += line.charAt(j);
				}
			}
			
			if(newLine.length() > 0)
				file.set(i, lineIndent + newLine.trim());
			
			else if(elementDefinition == null) {
				file.remove(i);
				i--;
			}
		}
	}
}