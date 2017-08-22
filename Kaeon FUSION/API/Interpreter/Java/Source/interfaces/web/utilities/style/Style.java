package interfaces.web.utilities.style;

import java.util.ArrayList;

import fusion.FUSIONStone;
import io.IO;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.ONEPlus;
import one_plus.element.Element;

public class Style extends FUSIONStone {
	
	private ONEPlus tags;
	
	public Style() {
		tags = new ONEPlus(IO.openAsString("dev/interfaces/web/data/Tags.op"));
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Style");
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		Element styleElement = element;
		
		if(styleElement.getNumElements() == 1) {
			
			if(styleElement.getElement(0).getNumElements() == 0) {
				
				ArrayList<String> tags = new ArrayList<String>();
				
				tags.add("Function");
				tags.add(styleElement.getElement(0).getContent());
				
				styleElement = ((FunctionStone) get(tags).get(0)).getFunction();
				styleElement.setContent("Style");
			}
		}
		
		return "<style>" + generateStyle(styleElement) + "</style>";
	}
	
	private String generateStyle(Element element) {
		
		String style = "";
		
		for(int i = 0; i < element.getNumElements(); i++)
			style += generateStyleBlock(element.getElement(i));
		
		return style;
	}
	
	private String generateStyleBlock(Element element) {
		
		return
				generateStyleBlockTag(element) +
				"{" +
				generateStyleBlockLines(element) +
				"}";
	}
	
	private String generateStyleBlockTag(Element element) {
		
		String style = "";
		
		if(element.getContent().equals("On"))
			return "#" + element.getElement(0).getContent();
		
		Element currentElement = element;
		
		while(currentElement.getNumElements() > 0)
			currentElement = currentElement.getElement(0);
		
		currentElement = currentElement.getParent().getParent();
		
		while(!currentElement.getContent().equalsIgnoreCase("Style")) {
			
			style = generateStyleBlockTagElement(currentElement) + style;
			
			currentElement = currentElement.getParent();
		}
		
		return style;
	}
	
	private String generateStyleBlockTagElement(Element element) {
		
		String tagElement = element.getContent();
		
		if(isPageTag(tagElement))
			return tags.getElement(tagElement).getElement(0).getContent();
		
		return "." + tagElement;
	}
	
	private boolean isPageTag(String tag) {
		
		for(int i = 0; i < tags.getNumElements(); i++) {
			
			if(tags.getElement(i).getContent().equals(tag))
				return true;
		}
		
		return false;
	}
	
	private String generateStyleBlockLines(Element element) {
		
		String style = "";
		
		Element currentElement = element;
		
		while(currentElement.getNumElements() > 0)
			currentElement = currentElement.getElement(0);
		
		currentElement = currentElement.getParent().getParent();
		
		for(int i = 0; i < currentElement.getNumElements(); i++)
			style += generateStyleBlockLine(currentElement.getElement(i));
		
		return style;
	}
	
	private String generateStyleBlockLine(Element element) {
		
		return 
				element.getContent() +
				":" +
				element.getElement(0).getContent() +
				";";
	}
}