package tokenizer;

import java.util.ArrayList;

public class Tokenizer {
	
	public static ArrayList<String> tokenize(ArrayList<String> tokens, String string) {
		
		ArrayList<String> tokenize = new ArrayList<String>();
		
		String undefined = "";
		
		for(int i = 0; i < string.length(); i++) {
			
			String token = getToken(tokens, string, i);
			
			if(token == null)
				undefined += string.charAt(i);
			
			else {
				
				if(undefined.length() > 0) {
					
					tokenize.add(undefined);
				
					undefined = "";
				}
				
				tokenize.add(token);
				
				i += token.length() - 1;
			}
		}
		
		if(undefined.length() > 0)
			tokenize.add(undefined);
		
		return tokenize;
	}
	
	public static String getToken(ArrayList<String> tokens, String string, int index) {
		
		ArrayList<String> validTokens = new ArrayList<String>();
		
		for(String token : tokens) {
			
			if(isToken(token, string, index))
				validTokens.add(token);
		}
		
		if(validTokens.size() == 0)
			return null;
		
		String validToken = validTokens.get(0);
		
		for(int i = 1; i < validTokens.size(); i++) {
			
			if(validTokens.get(i).length() > validToken.length())
				validToken = validTokens.get(i);
		}
		
		return validToken;
	}
		
	public static boolean isToken(String token, String string, int index) {
		
		for(int i = index; i < string.length() && i - index < token.length(); i++) {
			
			if(string.charAt(i) != token.charAt(i - index))
				return false;
		}
		
		return true;
	}
		
	public static boolean isToken(ArrayList<String> tokens, String string) {
		
		for(String token : tokens) {
			
			if(token.equals(string))
				return true;
		}
		
		return false;
	}
}