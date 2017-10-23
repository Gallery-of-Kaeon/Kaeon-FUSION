package legacy.web.commands;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.interface_module.build_stone.BuildStone;
import legacy.utilities.one_plus.element.Element;
import legacy.utilities.philosophers_stone_plus.PhilosophersStonePlus;
import legacy.web.utilities.page.PageBuilder;

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