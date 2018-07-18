package one_plus.parse;

import java.util.ArrayList;

public class TokenGenerator {
	
	public static ArrayList<String> getTokens(String string) {
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		tokens.add("-");
		tokens.add("\n");
		tokens.add(",");
		tokens.add(":");
		tokens.add(";");
		tokens.add("(");
		tokens.add(")");
		tokens.add("{");
		tokens.add("}");
		tokens.add("~");
		tokens.add("\"");
		tokens.add("\'");
		tokens.add("[");
		tokens.add("]");
		tokens.add("#");
		tokens.add("#[");
		tokens.add("]#");
		
		tokens.add(getIndentToken(string));
		
		return tokens;
	}
	
	public static String getIndentToken(String string) {
		
		try {
			
			boolean newLine = true;
			
			for(int i = 0; i < string.length(); i++) {
				
				if(!newLine && string.charAt(i) != '\n')
					continue;
				
				if(string.charAt(i) == ' ' || string.charAt(i) == '\t') {
					
					String token = "" + string.charAt(i);
					i++;
					
					while(i < string.length()) {
						
						if(string.charAt(i) == token.charAt(0))
							token += string.charAt(i);
						
						else
							break;
						
						i++;
					}
					
					if(i == string.length() - 1)
						continue;
					
					if(string.charAt(i) != '\n' && string.charAt(i) != ' ' && string.charAt(i) != '\t')
						return token;
				}
				
				else
					newLine = string.charAt(i) == '\n';
			}
		}
		
		catch(Exception exception) {
			
		}
		
		return "\t";
	}
}