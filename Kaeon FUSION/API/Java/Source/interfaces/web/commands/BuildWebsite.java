package interfaces.web.commands;

import java.util.ArrayList;

import interfaces.web.utilities.page.PageBuilder;
import kaeon_fusion.commands.Command;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildWebsite extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Build Website");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> definitions = get(tags);
		
		Element page = null;
		
		for(int i = 0; i < definitions.size() && page == null; i++) {
			
			if(definitions.get(i).isTagged(element.getElement(0).getContent()))
				page = ((FunctionStone) definitions.get(i)).getFunction();
		}
		
		PageBuilder pageBuilder =
				new PageBuilder(
						"" +
						(element.getNumElements() >= 2 ?
							element.getElement(1).getContent() : ""));
		
		for(int i = 0; i < definitions.size(); i++)
			pageBuilder.publiclyConnect(definitions.get(i));
		
		pageBuilder.process(page);
		
		return null;
	}
}