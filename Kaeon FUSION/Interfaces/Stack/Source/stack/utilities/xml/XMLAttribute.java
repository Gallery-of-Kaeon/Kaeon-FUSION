package stack.utilities.xml;

public class XMLAttribute {
	
	private String id;
	private String content;
	
	public XMLAttribute() {
		
	}
	
	public XMLAttribute(String content) {
		setContent(content);
	}
	
	public XMLAttribute(String id, String content) {
		setID(id);
		setContent(content);
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getID() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
}