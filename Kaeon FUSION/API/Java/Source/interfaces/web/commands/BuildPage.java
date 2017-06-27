package interfaces.web.commands;

import java.util.ArrayList;

import interfaces.web.utilities.page.PageBuilder;
import kaeon_fusion.interface_module.build_stone.BuildStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildPage extends BuildStone {
	
	public BuildPage() {
		tag("Page");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> definitions = get(tags);
		
		Element page = functions.get(0);
		
		PageBuilder pageBuilder =
				new PageBuilder(
						"" +
						(arguments.size() >= 1 ?
								arguments.get(0).getContent() : ""));
		
		for(int i = 0; i < definitions.size(); i++)
			pageBuilder.publiclyConnect(definitions.get(i));
		
		pageBuilder.process(page);
		
		return null;
	}
}