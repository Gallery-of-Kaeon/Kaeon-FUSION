package one_plus.parse;

import java.util.ArrayList;

public class Preprocessor {
	
	public static ArrayList<String> preprocess(ArrayList<String> tokens, ArrayList<String> tokenize, String nestToken) {

		ArrayList<String> preprocess = new ArrayList<String>();
		
		int inLiteralBlock = -1;
		boolean inCommentBlock = false;
		
		for(int i = 0; i < tokenize.size();) {
			
			ArrayList<String> line = getLine(tokenize, i);
			
			int shift = line.size() + 1;
			int nest = nest(line, nestToken);
			
			if(isLiteralBlock(line, nest) && !inCommentBlock) {
				
				if(inLiteralBlock == -1)
					inLiteralBlock = nest;
				
				else if(inLiteralBlock == nest)
					inLiteralBlock = -1;
			}
			
			else if(isBlankLine(line) && inLiteralBlock == -1) {
				
				i += shift;
				
				continue;
			}
			
			if(inLiteralBlock > -1) {
				
				preprocess.addAll(line);
				preprocess.add("\n");
				
				i += shift;
				
				continue;
			}
			
			boolean inLiteral = false;
			boolean inLiteralQuote = false;
			boolean inComment = false;
			
			for(int j = 0; j < line.size(); j++) {
				
				if(inLiteral && !line.get(j).equals("\'"))
					continue;
				
				if(inLiteralQuote && !line.get(j).equals("\""))
					continue;
				
				if(line.get(j).equals("]#")) {
					
					inCommentBlock = false;
					
					line.remove(j);
					j--;
					
					continue;
				}
				
				if(inComment || inCommentBlock) {
					
					line.remove(j);
					j--;
					
					continue;
				}
				
				if(line.get(j).equals("~")) {
					
					j++;
					
					continue;
				}
				
				if(line.get(j).equals("\"")) {
					
					inLiteralQuote = !inLiteralQuote;
					
					continue;
				}
				
				if(line.get(j).equals("\'")) {
					
					inLiteral = !inLiteral;
					
					continue;
				}
				
				if(line.get(j).equals("#")) {
					
					inComment = true;
					
					line.remove(j);
					j--;
					
					continue;
				}
				
				if(line.get(j).equals("#[")) {
					
					inCommentBlock = true;
					
					line.remove(j);
					j--;
					
					continue;
				}
				
				if(line.get(j).length() == 0) {
					
					line.remove(j);
					j--;
					
					continue;
				}
			}
			
			preprocess.addAll(line);
			preprocess.add("\n");
			
			i += shift;
		}
		
		if(preprocess.size() > 0)
			preprocess.remove(preprocess.size() - 1);
		
		return preprocess;
	}
	
	public static ArrayList<String> getLine(ArrayList<String> tokenize, int index) {
		
		ArrayList<String> line = new ArrayList<String>();
		
		for(int i = index; i < tokenize.size() && !tokenize.get(i).equals("\n"); i++)
			line.add(tokenize.get(i));
		
		return line;
	}
	
	public static int nest(ArrayList<String> line, String nestToken) {

		int nest = 0;
		
		for(String token : line) {
			
			if(!token.equals(nestToken))
				break;
			
			nest++;
		}
		
		return nest;
	}
	
	public static boolean isBlankLine(ArrayList<String> line) {
		
		boolean blank = true;
		
		for(String token : line) {
			
			if(token.trim().length() > 0) {
				
				blank = false;
				
				break;
			}
		}
		
		return blank;
	}
	
	public static boolean isLiteralBlock(ArrayList<String> line, int nest) {
		
		if(line.size() != nest + 1 || line.size() == 0)
			return false;
		
		return line.get(line.size() - 1).equals("-");
	}
}