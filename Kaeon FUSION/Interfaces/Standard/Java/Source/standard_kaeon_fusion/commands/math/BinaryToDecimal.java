package standard_kaeon_fusion.commands.math;

import java.math.BigInteger;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class BinaryToDecimal extends FUSIONUnit {
	
	public BinaryToDecimal() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Binary to Decimal");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		String number = "" + processed.get(0);
		
		boolean signed = Boolean.parseBoolean("" + processed.get(1));
		boolean floating = Boolean.parseBoolean("" + processed.get(2));
		
		double result = 0;
		
		if(signed || floating)
			result = Double.longBitsToDouble(new BigInteger(number, 2).longValue());
		
		result = Long.parseLong(number, 2);
		
		return result % 1 != 0 ? result : "" + (long) result;
	}
}