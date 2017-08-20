package interfaces.web.utilities.query.commands.core;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class CreateDatabase extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Create Database");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "CREATE DATABASE ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}