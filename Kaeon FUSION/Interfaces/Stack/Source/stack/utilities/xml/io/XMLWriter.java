package stack.utilities.xml.io;

import stack.utilities.xml.XMLAttribute;
import stack.utilities.xml.XMLElement;
import stack.utilities.xml.XMLUnit;

public class XMLWriter {
	
	public static String writeAsXML(XMLUnit metadata) {
		
		String xml = "";
		
		if(metadata.getHeader() != null)
			xml += writeHeader(metadata.getHeader());
		
		for(int i = 0; i < metadata.getData().size(); i++) {
			
			if(xml.length() > 0)
				xml += '\n';
			
			xml += writeData(metadata.getData().get(i), 0);
		}
		
		return xml;
	}
	
	public static String writeHeader(Object header) {
		
		if(header == null)
			return "";
		
		String xml = "";
		
		if(header instanceof XMLElement) {
			
			xml += "<?";
			
			xml += ((XMLElement) header).getID();
			
			for(int i = 0; i < ((XMLElement) header).getAttributes().size(); i++)
				xml += ' ' + writeAttribute(((XMLElement) header).getAttributes().get(i));
			
			xml += "?>";
		}
		
		if(header instanceof String) {
			
			if(((String) header).length() > 0)
				xml += ((String) header);
		}
		
		return xml;
	}
	
	public static String writeData(Object data, int indent) {
		
		String xml = "";
		
		for(int i = 0; i < indent; i++)
			xml += '\t';
		
		if(data instanceof XMLElement) {
			
			xml += '<';
			
			xml += ((XMLElement) data).getID();
			
			for(int i = 0; i < ((XMLElement) data).getAttributes().size(); i++)
				xml += ' ' + writeAttribute(((XMLElement) data).getAttributes().get(i));
			
			if(((XMLElement) data).getData().size() == 0)
				xml += "/>";
			
			else {
				
				xml += '>';
				
				for(int i = 0; i < ((XMLElement) data).getData().size(); i++)
					xml += '\n' + writeData(((XMLElement) data).getData().get(i), indent + 1);
				
				xml += '\n';
				
				for(int i = 0; i < indent; i++)
					xml += '\t';
				
				xml += "</" + ((XMLElement) data).getID() + '>';
			}
		}
		
		if(data instanceof String) {
			
			String xmlData = ((String) data);
			
			for(int i = 0; i < xmlData.length(); i++) {
				
				if(xmlData.charAt(i) == '\n') {
					
					String beginOffset = xmlData.substring(0, i + 1);
					String endOffset = xmlData.substring(i + 1);
					
					xmlData = beginOffset;
					
					for(int j = 0; j < indent; j++)
						xmlData += '\t';
					
					xmlData += endOffset;
					
					i += indent;
				}
			}
			
			xml += xmlData;
		}
		
		return xml;
	}
	
	public static String writeAttribute(XMLAttribute attribute) {
		return attribute.getID() + "=\"" + attribute.getContent() + '\"';
	}
}