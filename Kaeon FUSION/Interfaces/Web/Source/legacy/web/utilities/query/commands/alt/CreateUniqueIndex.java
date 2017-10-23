package legacy.web.utilities.query.commands.alt;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class CreateUniqueIndex extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Create Unique Index");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "CREATE UNIQUE INDEX ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}