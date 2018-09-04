package stack.dialects;

import java.util.ArrayList;

import build_dialect.BuildDialect;
import one.Element;

public class SQL extends BuildDialect {
	
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
			sql += statements.get(i) + (statements.get(i).endsWith(":") ? "" : ";");
		
		file.add(sql);
		
		files.add(file);
	}
	
	public void buildStatements(ArrayList<String> statements, String statement, Element command) {
		
		for(int i = 0; i < command.children.size(); i++) {
			
			if(command.content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(command);
				
				if(injection != null)
					statement += injection;
			}
			
			else if(command.content.equalsIgnoreCase("Scope")) {
				
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
		
		ArrayList<String> arguments = new ArrayList<String>();
		
		for(int i = 0; i < command.children.size(); i++) {
			
			if(command.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(command.children.get(i));
				
				if(injection != null)
					arguments.add(injection);
			}
			
			else if(command.children.get(i).content.equalsIgnoreCase("Scope")) {
				
				ArrayList<String> statements = new ArrayList<String>();
				buildStatements(statements, "", command.children.get(i));
				
				arguments.addAll(statements);
			}
			
			else
				arguments.add(buildCommand(command.children.get(i)));
		}
		
		if(command.content.equalsIgnoreCase("Fields")) {
			
			String build = "(";
			
			for(int i = 0; i < arguments.size(); i++)
				build += arguments.get(i) + " ";
			
			return build + ")";
		}
		
		String prefix = getPrefix(command);
		
		if(prefix != null) {
			
			String build = prefix + " ";
			
			for(int i = 0; i < arguments.size(); i++)
				build += arguments.get(i) + " ";
			
			return build;
		}
		
		String infix = getInfix(command);
		
		if(infix != null)
			return arguments.get(0) + infix + arguments.get(1) + " ";
		
		String alias = getAlias(command);
		
		if(alias != null)
			return alias + " ";
		
		return command.content;
	}
	
	public String getPrefix(Element command) {
		
		if(command.content.equalsIgnoreCase("Select"))
			return "SELECT";
		
		if(command.content.equalsIgnoreCase("From"))
			return "FROM";
		
		if(command.content.equalsIgnoreCase("Where"))
			return "WHERE";
		
		return null;
	}
	
	public String getInfix(Element command) {
		return null;
	}
	
	public String getAlias(Element command) {
		
		if(command.content.equalsIgnoreCase("All"))
			return "*";
		
		return null;
	}
}