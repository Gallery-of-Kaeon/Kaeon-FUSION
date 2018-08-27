package stack.dialects;

import java.util.ArrayList;

import build_dialect.BuildDialect;
import io.IO;
import kaeon_fusion.KaeonFUSION;
import one.Element;
import one_plus.ONEPlus;

public class Assembly extends BuildDialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		Element element = code.get(0);
		
		String mode = "Binary";
		ArrayList<Element> use = new ArrayList<Element>();
		
		String machineCode = "";
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				String injection = getInjection(element.children.get(i));
				
				if(injection != null)
					machineCode += injection;
			}
			
			else if(element.children.get(i).content.equalsIgnoreCase("Mode"))
				mode = setMode(element.children.get(i).children.get(0).content);
			
			else if(element.children.get(i).content.equalsIgnoreCase("Use"))
				addUse(use, element.children.get(i));
			
			else
				machineCode += processInstruction(mode, use, element.children.get(i));
		}
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(name == null) {
			
			name = "program";
			
			if(index > 0)
				name += "_" + index;
		}
		
		file.add(name);
		file.add(machineCode);
		
		files.add(file);
	}
	
	public String setMode(String mode) {
		
		if(mode.equalsIgnoreCase("Raw"))
			return "Raw";
		
		if(mode.equalsIgnoreCase("Decimal"))
			return "Decimal";
		
		if(mode.equalsIgnoreCase("Hexadecimal"))
			return "Hexadecimal";
		
		if(mode.equalsIgnoreCase("Mnemonic"))
			return "Mnemonic";
		
		return "Binary";
	}
	
	public void addUse(ArrayList<Element> use, Element element) {

		for(int i = 0; i < element.children.size(); i++) {
			
			try {
				use.add(0, ONEPlus.parseONEPlus(IO.openAsString(element.children.get(i).content)));
			}
			
			catch(Exception exception) {
				
			}
		}
	}
	
	public String processInstruction(String mode, ArrayList<Element> use, Element instruction) {
		
		if(mode.equalsIgnoreCase("Binary"))
			return "" + (char) binaryToInteger(instruction.content);
		
		if(mode.equalsIgnoreCase("Decimal"))
			return "" + (char) Integer.parseInt(instruction.content);
		
		if(mode.equalsIgnoreCase("Hexadecimal"))
			return "" + (char) hexToInteger(instruction.content);
		
		if(mode.equalsIgnoreCase("Mnemonic"))
			return processMnemonic(use, instruction);
		
		return instruction.content;
	}
	
	public long binaryToInteger(String binary) {
		
	    char[] numbers = binary.toCharArray();
	    
	    long result = 0;
	    
	    for(int i = numbers.length - 1; i >= 0; i--) {
	    	
	        if(numbers[i] == '1')
	            result += Math.pow(2, (numbers.length - i - 1));
	    }
	    
	    return result;
	}
	
	public long hexToInteger(String hex) {
		
	    char[] numbers = hex.toCharArray();
	    
	    long result = 0;
	    
	    for(int i = numbers.length - 1; i >= 0; i--) {
	    	
	    	int digit = 0;
	    	
	    	if(numbers[i] == '1')
	    		digit = 1;
	    	
	    	if(numbers[i] == '2')
	    		digit = 2;
	    	
	    	if(numbers[i] == '3')
	    		digit = 3;
	    	
	    	if(numbers[i] == '4')
	    		digit = 4;
	    	
	    	if(numbers[i] == '5')
	    		digit = 5;
	    	
	    	if(numbers[i] == '6')
	    		digit = 6;
	    	
	    	if(numbers[i] == '7')
	    		digit = 7;
	    	
	    	if(numbers[i] == '8')
	    		digit = 8;
	    	
	    	if(numbers[i] == '9')
	    		digit = 9;
	    	
	    	if(numbers[i] == 'A' || numbers[i] == 'a')
	    		digit = 10;
	    	
	    	if(numbers[i] == 'B' || numbers[i] == 'b')
	    		digit = 11;
	    	
	    	if(numbers[i] == 'C' || numbers[i] == 'c')
	    		digit = 12;
	    	
	    	if(numbers[i] == 'D' || numbers[i] == 'd')
	    		digit = 13;
	    	
	    	if(numbers[i] == 'E' || numbers[i] == 'e')
	    		digit = 14;
	    	
	    	if(numbers[i] == 'F' || numbers[i] == 'f')
	    		digit = 15;
	    	
	        result += digit * Math.pow(16, (numbers.length - i - 1));
	    }
	    
	    return result;
	}
	
	@SuppressWarnings("unchecked")
	public String processMnemonic(ArrayList<Element> use, Element instruction) {
		
		for(int i = 0; i < use.size(); i++) {
			
			KaeonFUSION fusion = new KaeonFUSION();
			
			fusion.process(instruction);
			
			try {
				
				String string = "";
				
				ArrayList<Object> list = (ArrayList<Object>) fusion.toReturn;
				
				for(int j = 0; j < list.size(); j++)
					string += (char) Long.parseLong("" + list.get(i), 2);
				
				return string;
			}
			
			catch(Exception exception) {
				
			}
		}
		
		return "";
	}
}