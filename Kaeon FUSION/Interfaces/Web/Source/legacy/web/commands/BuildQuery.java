package legacy.web.commands;

import java.util.ArrayList;

import io.IO;
import legacy.kaeon_fusion_legacy.interface_module.build_stone.BuildStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.query.Query;

public class BuildQuery extends BuildStone {
	
	public BuildQuery() {
		tag("Query");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		Element source = functions.get(0);
		
		source = copyElement(source);
		source.setContent("Query");
		
		String output = "" + new Query().process(source);

		if(arguments.size() >= 1)
			IO.save(output, arguments.get(0).getContent() + source.getContent() + ".sql");

		else
			IO.save(output, source.getContent() + ".sql");
		
		return null;
	}
	
	private Element copyElement(Element element) {
		
		Element copy = new Element();
		
		copy.setContent(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			copy.addElement(copyElement(element.getElement(i)));
		
		return copy;
	}
}