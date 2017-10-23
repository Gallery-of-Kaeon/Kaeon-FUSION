package legacy.web.utilities.query.commands.core;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class CreateIndex extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Create Index");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "CREATE INDEX ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}