package interfaces.web.utilities.page;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.page.attributes.Attribute;
import interfaces.web.utilities.page.literal.Literal;
import interfaces.web.utilities.page.tags.Tag;
import interfaces.web.utilities.process.ProcessCommand;
import interfaces.web.utilities.script.Script;
import interfaces.web.utilities.style.Style;
import io.IO;
import one_plus.ONEPlus;
import one_plus.element.Element;

public class PageBuilder extends FUSIONStone {
	
	private String directory;
	
	private String head;
	private String body;
	
	public PageBuilder(String directory) {
		
		this.directory = directory;
		
		head = "";
		body = "";
		
		connectCommands();
	}
	
	public void connectCommands() {
		
		publiclyConnectMutually(new Style());
		publiclyConnectMutually(new Script());
		publiclyConnectMutually(new ProcessCommand());
		
		publiclyConnectMutually(new Literal());
		
		ONEPlus tags = new ONEPlus(IO.openAsString("dev/interfaces/web/data/Tags.op"));
		
		for(int i = 0; i < tags.getNumElements(); i++) {
			
			publiclyConnectMutually(
					new Tag(
							tags.getElement(i).getContent(),
							tags.getElement(i).getElement(0).getContent()));
		}
		
		ONEPlus attributes = new ONEPlus(IO.openAsString("dev/interfaces/web/data/Attributes.op"));
		
		for(int i = 0; i < attributes.getNumElements(); i++) {
			
			publiclyConnectMutually(
					new Attribute(
							attributes.getElement(i).getContent(),
							attributes.getElement(i).getElement(0).getContent()));
		}
	}
	
	public boolean onVerify(Element element) {
		return element.getParent().getContent().equalsIgnoreCase("Define");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		addToHead(processed);
		addToBody(processed);
		
		String html =
				"<!DOCTYPE HTML><html><head>" +
				head +
				"</head><body>" +
				body +
				"</body></html>";
		
		IO.save(html, directory + element.getContent() + ".html");
		
		return null;
	}
	
	private void addToHead(ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++) {
			
			String html = "" + processed.get(i);
			
			if(isHead(html)) {
				
				head += html;
				
				processed.remove(i);
				i--;
			}
		}
	}
	
	private boolean isHead(String html) {
		return html.indexOf("<head>") == 0;
	}
	
	private void addToBody(ArrayList<Object> processed) {

		for(int i = 0; i < processed.size(); i++)
			body += "" + processed.get(i);
	}
}