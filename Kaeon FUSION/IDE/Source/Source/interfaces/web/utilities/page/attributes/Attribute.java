package interfaces.web.utilities.page.attributes;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Attribute extends FUSIONStone {
	
	private String name;
	private String html;
	
	public Attribute(String name, String html) {
		this.name = name;
		this.html = html;
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase(name);
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return html + "=\"" + element.getElement(0).getContent() + "\"";
	}
}