package one_plus.read;

import one.Element;

public class ONEPlusElement extends Element {
	
	private int lineNumber;
	private int elementNumber;
	
	private String definition;
	
	public ONEPlusElement() {
		
		super();
		
		lineNumber = -1;
		elementNumber = -1;
		
		definition = "ONE";
	}
	
	public ONEPlusElement(String content) {
		
		this();
		
		this.content = content;
	}
	
	public ONEPlusElement(String content, String definition) {
		
		this();
		
		this.content = content;
		setDefinition(definition);
	}
	
	public ONEPlusElement(ONEPlusElement onePlusElement) {
		
		this();
		
		this.content = onePlusElement.content;
		
		for(int i = 0; i < onePlusElement.children.size(); i++)
			addElement(new ONEPlusElement((ONEPlusElement) onePlusElement.getElement(i)));
		
		setDefinition(onePlusElement.getDefinition());
	}
	
	public void addElement(ONEPlusElement element) {
		this.children.add(element);
	}
	
	public void addElement(ONEPlusElement element, int index) {
		this.children.add(index, element);
	}
	
	public ONEPlusElement getElement(int index) {
		return (ONEPlusElement) this.children.get(index);
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public int getElementNumber() {
		return elementNumber;
	}
	
	public String getDefinition() {
		return definition;
	}
}