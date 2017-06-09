package interfaces.web.utilities.query;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.query.commands.alt.CreateUniqueIndex;
import interfaces.web.utilities.query.commands.core.AlterDatabase;
import interfaces.web.utilities.query.commands.core.AlterTable;
import interfaces.web.utilities.query.commands.core.CreateDatabase;
import interfaces.web.utilities.query.commands.core.CreateTable;
import interfaces.web.utilities.query.commands.core.Delete;
import interfaces.web.utilities.query.commands.core.DropIndex;
import interfaces.web.utilities.query.commands.core.DropTable;
import interfaces.web.utilities.query.commands.core.InsertInto;
import interfaces.web.utilities.query.commands.core.Select;
import interfaces.web.utilities.query.commands.core.Update;
import interfaces.web.utilities.query.commands.logic.And;
import interfaces.web.utilities.query.commands.logic.Or;
import interfaces.web.utilities.query.commands.query.From;
import interfaces.web.utilities.query.commands.query.Where;
import interfaces.web.utilities.query.literal.Literal;
import one_plus.element.Element;

public class Query extends FUSIONStone {
	
	private Query query;
	
	public Query() {
		query = new Query(true);
	}
	
	public Query(boolean isScript) {
		
		publiclyConnectMutually(new Literal());

		publiclyConnectMutually(new AlterDatabase());
		publiclyConnectMutually(new AlterTable());
		publiclyConnectMutually(new CreateDatabase());
		publiclyConnectMutually(new CreateTable());
		publiclyConnectMutually(new Delete());
		publiclyConnectMutually(new DropIndex());
		publiclyConnectMutually(new DropTable());
		publiclyConnectMutually(new InsertInto());
		publiclyConnectMutually(new Select());
		publiclyConnectMutually(new Update());

		publiclyConnectMutually(new CreateUniqueIndex());
		
		publiclyConnectMutually(new From());
		publiclyConnectMutually(new Where());

		publiclyConnectMutually(new And());
		publiclyConnectMutually(new Or());
		
		// STUB
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Query");
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		Element queryElement = element;
		
		String query = "";
		String currentQuery = "";
		
		for(int i = 0; i < queryElement.getNumElements(); i++) {
			
			if(queryElement.getElement(i).getContent().equalsIgnoreCase("Scope")) {
				
				if(currentQuery.length() > 0)
					query += currentQuery + ";";
				
				currentQuery = "";
				
				query += onProcess(queryElement.getElement(i), processed) + " ";
			}
			
			else if(queryElement.getElement(i).getContent().equalsIgnoreCase("Split")) {
				
				String split = "" + onProcess(queryElement.getElement(i), processed);
				
				if(split.charAt(split.length() - 1) == ';')
					split = split.substring(0, split.length() - 1);
				
				query += split + " " + currentQuery;
				query = query.trim();

				if(query.charAt(query.length() - 1) != ';')
					query += ";";
			}
			
			else
				currentQuery = this.query.process(queryElement.getElement(i)) + currentQuery;
		}
		
		if(!queryElement.getElement(queryElement.getNumElements() - 1).getContent().equalsIgnoreCase("Split"))
			query += currentQuery;
		
		query = query.trim();
		
		if(query.charAt(query.length() - 1) != ';')
			query += ";";
		
		return query;
	}
}