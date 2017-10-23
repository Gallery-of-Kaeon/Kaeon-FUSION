package legacy.utilities.one_plus.read;

import java.util.ArrayList;

import legacy.utilities.one_plus.element.Element;
import legacy.utilities.one_plus.read.directives.data.Declaration;
import legacy.utilities.one_plus.read.directives.data.Definition;
import legacy.utilities.one_plus.read.directives.data.Info;

public class ONEPlusElement extends Element {
	
	private ArrayList<Info> info;
	
	private int lineNumber;
	private int elementNumber;
	
	private String definition;
	
	private ArrayList<Declaration> declarations;
	private ArrayList<Definition> definitions;
	
	public ONEPlusElement() {
		
		super();
		
		info = new ArrayList<Info>();
		
		lineNumber = -1;
		elementNumber = -1;
		
		definition = "ONE";
		
		declarations = new ArrayList<Declaration>();
		definitions = new ArrayList<Definition>();
	}
	
	public ONEPlusElement(String content) {
		
		this();
		
		setContent(content);
	}
	
	public ONEPlusElement(String content, String definition) {
		
		this();
		
		setContent(content);
		setDefinition(definition);
	}
	
	public ONEPlusElement(ONEPlusElement onePlusElement) {
		
		this();
		
		setContent(onePlusElement.getContent());
		
		for(int i = 0; i < onePlusElement.getNumElements(); i++)
			addElement(new ONEPlusElement((ONEPlusElement) onePlusElement.getElement(i)));
		
		setDefinition(onePlusElement.getDefinition());
		
		for(int i = 0; i < onePlusElement.getDeclarations().size(); i++)
			addDeclaration(new Declaration(onePlusElement.getDeclarations().get(i)));
		
		for(int i = 0; i < onePlusElement.getDefinitions().size(); i++)
			addDefinition(new Definition(onePlusElement.getDefinitions().get(i)));
	}
	
	public void addElement(ONEPlusElement element) {
		super.addElement(element);
	}
	
	public void addElement(ONEPlusElement element, int index) {
		super.addElement(element, index);
	}
	
	public ONEPlusElement getElement(int index) {
		return (ONEPlusElement) super.getElement(index);
	}
	
	public void addInfo(Info info) {
		this.info.add(info);
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
	
	public void addDeclaration(Declaration declaration) {
		declarations.add(declaration);
	}
	
	public void addDefinition(Definition definition) {
		definitions.add(definition);
	}
	
	public ArrayList<Info> getInfo() {
		return info;
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
	
	public ArrayList<Declaration> getDeclarations() {
		return declarations;
	}
	
	public ArrayList<Definition> getDefinitions() {
		return definitions;
	}
}