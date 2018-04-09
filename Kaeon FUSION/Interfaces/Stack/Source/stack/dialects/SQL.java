package stack.dialects;

import java.util.ArrayList;

import one.Element;
import stack.utilities.Dialect;

public class SQL extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "query";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name + ".sql");

		String sql = "";
		
		ArrayList<String> statements = new ArrayList<String>();
		buildStatements(statements, "", code.get(0));
		
		for(int i = 0; i < statements.size(); i++)
			sql += statements.get(i) + ";";
		
		file.add(sql);
		
		files.add(file);
	}
	
	public void buildStatements(ArrayList<String> statements, String statement, Element command) {
		
		for(int i = 0; i < command.children.size(); i++) {
			
			if(command.content.equalsIgnoreCase("Scope")) {
				
				if(command.parent == null) {
					
					if(statement.length() > 0)
						statements.add(statement);
					
					statement += "";
				}
				
				else
					buildStatements(statements, statement, command.children.get(i));
			}
			
			else
				statement += " " + buildCommand(command.children.get(i));
		}
		
		if(statement.length() > 0)
			statements.add(statement);
	}
	
	public String buildCommand(Element command) {
		return command.content;
	}
}