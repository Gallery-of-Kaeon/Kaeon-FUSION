package interfaces.web.utilities.query.commands.core;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class DropTable extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Drop Table");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "DROP TABLE ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}