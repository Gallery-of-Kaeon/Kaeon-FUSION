package interfaces.web.utilities.query.commands.core;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class InsertInto extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Insert Into");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "INSERT INTO ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}