package legacy.kaeon_fusion_legacy.super_mode;

import legacy.utilities.one_plus.read.ONEPlusElement;

public class SuperMode {
	
	public static void superMode(ONEPlusElement element) {
		
		if(element.getContent() != null) {
			
			try {
				process(element);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		for(int i = 0; i < element.getNumElements(); i++)
			superMode(element.getElement(i));
	}
	
	private static void process(ONEPlusElement element) {

		ifStatement(element);
		whileLoop(element);
		
		set(element);

		dot(element);
		
		not(element);
		
		and(element);
		or(element);
		xor(element);
		
		equal(element);
		notEqual(element);
		
		greaterOrEqual(element);
		lessOrEqual(element);
		
		greater(element);
		less(element);

		subtract(element);
		add(element);
		divide(element);
		multiply(element);
		modulus(element);

		at(element);
	}
	
	private static void ifStatement(ONEPlusElement element) {
		
		if(element.getContent().equalsIgnoreCase("If")) {
			
			ONEPlusElement breakElement = new ONEPlusElement("Break");
			
			ONEPlusElement notElement = new ONEPlusElement("Not");
			notElement.addElement(element.removeElement(0));
			
			breakElement.addElement(notElement);
			element.addElement(breakElement, 0);
			
			element.setContent("Scope");
		}
	}
	
	private static void whileLoop(ONEPlusElement element) {
		
		if(element.getContent().equalsIgnoreCase("While")) {
			
			ONEPlusElement loop = new ONEPlusElement("Loop");
			loop.addElement(element.removeElement(0));
			
			element.addElement(loop);
			
			element.setContent("Scope");
		}
	}
	
	private static void set(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "=");
		
		if(index != -1) {
			
			if(index < content.length() - 1) {
				
				if(content.charAt(index + 1) == '=')
					return;
			}
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(newElement);
			element.setContent(content.substring(0, index).trim());
		}
	}
	
	private static void at(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "@");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("At");
		}
	}
	
	private static void dot(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "->");
		
		if(index != -1) {
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()), 0);
			
			element.setContent(content.substring(index + 2).trim());
		}
	}
	
	private static void not(ONEPlusElement element) {
		
		String content = element.getContent();
		
		if(content.length() == 4)
			return;
		
		int index = getIndexOf(content, "Not ");
		
		if(index != -1) {
			
			element.addElement(new ONEPlusElement(content.substring(index + 4).trim()));
			
			element.setContent("Not");
		}
	}
	
	private static void and(ONEPlusElement element) {
		
		String content = element.getContent();
		
		if(content.length() == 5)
			return;
		
		int index = getIndexOf(content, " And ");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 5).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("And");
		}
	}
	
	private static void or(ONEPlusElement element) {
		
		String content = element.getContent();
		
		if(content.length() == 4)
			return;
		
		int index = getIndexOf(content, " Or ");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 4).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Or");
		}
	}
	
	private static void xor(ONEPlusElement element) {
		
		String content = element.getContent();
		
		if(content.length() == 5)
			return;
		
		int index = getIndexOf(content, " Xor ");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 5).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Xor");
		}
	}
	
	private static void equal(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "==");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 2).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Equal");
		}
	}
	
	private static void notEqual(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "!=");
		
		if(index != -1) {

			ONEPlusElement equalElement = new ONEPlusElement("Equal");
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 2).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			equalElement.addElement(newElement);
			element.addElement(equalElement);
			
			element.setContent("Not");
		}
	}
	
	private static void greaterOrEqual(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, ">=");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 2).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Greater or Equal");
		}
	}
	
	private static void lessOrEqual(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "<=");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 2).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Less or Equal");
		}
	}
	
	private static void greater(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, ">");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Greater");
		}
	}
	
	private static void less(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "<");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Less");
		}
	}
	
	private static void add(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "+");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Add");
		}
	}
	
	private static void subtract(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "-");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Subtract");
		}
	}
	
	private static void multiply(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "*");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Multiply");
		}
	}
	
	private static void divide(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "/");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Divide");
		}
	}
	
	private static void modulus(ONEPlusElement element) {
		
		String content = element.getContent();
		
		int index = getIndexOf(content, "%");
		
		if(index != -1) {
			
			ONEPlusElement newElement = new ONEPlusElement(content.substring(index + 1).trim());
			
			while(element.getNumElements() > 0)
				newElement.addElement(element.removeElement(0));
			
			element.addElement(new ONEPlusElement(content.substring(0, index).trim()));
			element.addElement(newElement);
			
			element.setContent("Modulus");
		}
	}
	
	private static int getIndexOf(String content, String sequence) {
		
		boolean inQuote = false;
		
		for(int i = 0; i < content.length() - sequence.length(); i++) {
			
			boolean escape = false;
			
			if(i > 0) {
				
				if(content.charAt(i - 1) == '\\')
					escape = true;
			}
			
			if(!escape && content.charAt(i) == '\"')
				inQuote = !inQuote;
			
			if(inQuote)
				continue;
			
			if(content.substring(i, i + sequence.length()).equalsIgnoreCase(sequence))
				return i;
		}
		
		return -1;
	}
}