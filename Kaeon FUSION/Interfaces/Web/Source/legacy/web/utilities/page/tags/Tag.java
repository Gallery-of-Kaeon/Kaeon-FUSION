package legacy.web.utilities.page.tags;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;

public class Tag extends FUSIONStone {
	
	private String name;
	private String html;
	
	public Tag(String name, String html) {
		this.name = name;
		this.html = html;
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase(name);
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String html = this.html;
		
		if(name.equalsIgnoreCase("Heading")) {
			
			for(int i = 0; i < element.getNumElements(); i++) {
				
				if(element.getElement(i).getContent().equalsIgnoreCase("Level")) {
					
					html = 'h' + element.getElement(i).getElement(0).getContent();
					processed.remove(i);
					
					break;
				}
			}
		}
		
		if(processed.size() == 0)
			return "<" + html + "/>";
		
		ArrayList<Object> attributes = getAttributes(processed);
		
		String text = "<" + html;
		
		if(attributes.size() > 0) {
			
			text += " ";
			
			for(int i = 0; i < attributes.size(); i++) {
				
				text += attributes.get(i);
				
				if(i < attributes.size() - 1)
					text += " ";
			}
		}
		
		if(processed.size() > 0) {
			
			text += ">";
			
			for(int i = 0; i < processed.size(); i++)
				text += processed.get(i);
			
			text += "</" + html + ">";
		}
		
		else
			text += "/>";
		
		return text;
	}
	
	private ArrayList<Object> getAttributes(ArrayList<Object> processed) {
		
		ArrayList<Object> attributes = new ArrayList<Object>();
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(isAttribute("" + processed.get(i))) {
				
				attributes.add(processed.remove(i));
				
				i--;
			}
		}
		
		return attributes;
	}
	
	private boolean isAttribute(String html) {
		
		if(html.indexOf('=') == -1)
			return false;
		
		String string = html.substring(html.indexOf('=') + 1).trim();
		
		if(string.length() < 2)
			return false;
		
		if(string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"')
			return true;
		
		return false;
	}
}