package stack.utilities.json.io.read;

import java.util.ArrayList;

public class FileScanner {
	
	private ArrayList<String> file;
	
	private int row;
	private int column;
	
	public FileScanner() {
		
	}
	
	public FileScanner(ArrayList<String> file) {
		this.file = file;
	}
	
	public char nextCharacter() {
		return file.get(row).charAt(column);
	}
	
	public char popNextCharacter() {
		
		char character = file.get(row).charAt(column);
		column++;
		
		if(column == file.get(row).length()) {
			row++;
			column = 0;
		}
		
		return character;
	}
	
	public void popNextCharacter(int pops) {
		
		for(int i = 0; i < pops; i++)
			popNextCharacter();
	}
	
	public boolean hasNext() {
		return row < file.size();
	}
}