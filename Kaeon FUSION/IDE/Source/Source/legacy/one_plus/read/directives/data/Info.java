package legacy.one_plus.read.directives.data;

import java.util.ArrayList;

import legacy.one_plus.read.ONEPlusElement;

public class Info {
	
	private String name;
	private ArrayList<ONEPlusElement> elements;
	
	public Info(ONEPlusElement directive) {
		
		name = directive.getElement(0).getContent();
		
		elements = new ArrayList<ONEPlusElement>();
		
		for(int i = 0; i < directive.getElements().size(); i++)
			elements.add(new ONEPlusElement((ONEPlusElement) directive.getElements().get(i)));
		
		elements.remove(0);
	}
	
	public Info(Info definition) {
		
		name = definition.getName();
		
		elements = new ArrayList<ONEPlusElement>();
		
		for(int i = 0; i < definition.getElements().size(); i++)
			elements.add(new ONEPlusElement(definition.getElements().get(i)));
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<ONEPlusElement> getElements() {
		return elements;
	}
}