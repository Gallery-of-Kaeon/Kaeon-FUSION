package standard_kaeon_fusion.commands.math;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class DecimalToHexadecimal extends FUSIONUnit {
	
	public DecimalToHexadecimal() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Decimal to Hexadecimal");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		double number = Double.parseDouble("" + processed.get(0));
		
		boolean signed = Boolean.parseBoolean("" + processed.get(1));
		boolean floating = Boolean.parseBoolean("" + processed.get(2));
		
		if(signed || floating)
			return "" + Long.toHexString(Double.doubleToRawLongBits(Double.parseDouble("" + processed.get(0)))).toUpperCase();
		
		return Integer.toHexString((int) number).toUpperCase();
	}
}