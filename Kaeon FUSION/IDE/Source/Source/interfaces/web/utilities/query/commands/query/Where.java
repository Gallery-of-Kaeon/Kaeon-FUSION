package interfaces.web.utilities.query.commands.query;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Where extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Where");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String query = "WHERE ";
		
		for(int i = 0; i < processed.size(); i++)
			query += processed.get(i) + " ";
		
		return query;
	}
}