package standard_kaeon_fusion.commands.flow;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class FUSIONException extends FUSIONUnit {
	
	public Exception exception;
	
	public FUSIONException() {
		tags.add("Standard");
		tags.add("Exception");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Exception");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(exception == null)
			return null;
		
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));
		
		return errors.toString();
	}
	
	public void handleError(Element element, ArrayList<Object> processed, Exception exception) {
		this.exception = exception;
	}
}