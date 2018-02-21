package stack.utilities.cross_dialect;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.Dialect;

public class CrossDialect extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		if(name == null) {
			
			name = "main";
			
			if(index > 0)
				name += "_" + index;
		}
		
		Element main = code.get(0);
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		String mainBuild =
				buildMain(
						name,
						main,
						categories, 
						buildElement(
								name,
								main,
								categories,
								new ArrayList<String>(),
								new Element(),
								0));
		
		buildCategories(
				files,
				name,
				main,
				mainBuild,
				categories);
	}
	
	public String buildElement(
			String name,
			Element element,
			ArrayList<Category> categories,
			ArrayList<String> variables,
			Element meta,
			int nest) {
		
		if(element.content != null) {
			
			if(element.content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(meta, element);
				
				return null;
			}
		}
		
		addFunctions(element, categories);
		
		ArrayList<String> arguments = new ArrayList<String>();
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("In")) {
				
				String operator = 
						buildElement(
								name,
								element.children.get(i).children.get(0),
								categories,
								new ArrayList<String>(variables),
								metaCopy,
								nest + 1);
				
				for(int j = i + 1; j < element.children.size(); j++) {

					String operation =
							buildElement(
									name,
									element.children.get(j),
									categories,
									new ArrayList<String>(variables),
									metaCopy,
									nest + 1);
					
					if(operation != null)
						arguments.add(buildObjectOperation(operator, operation, metaCopy));
				}
				
				break;
			}
			
			String argument =
					buildElement(
							name,
							element.children.get(i),
							categories,
							new ArrayList<String>(variables),
							metaCopy,
							nest + 1);
			
			if(argument != null)
				arguments.add(argument);
		}
		
		if(element.content == null && element.parent == null) {
			
			String string = "";
			
			for(int i = 0; i < arguments.size(); i++)
				string += arguments.get(i);
			
			return string;
		}
		
		if(element.content.equalsIgnoreCase("Define"))
			return buildDefine(element, arguments, meta, categories);
		
		if(element.content.equalsIgnoreCase("Import"))
			return buildImport(element, arguments, meta, categories);
		
		if(element.content.equalsIgnoreCase("Global"))
			return buildGlobal(element, arguments, meta, categories);
		
		if(element.content.equalsIgnoreCase("Execute"))
			return buildExecute(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Call"))
			return buildCall(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("This"))
			return buildThis(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("New"))
			return buildNew(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Null"))
			return buildNull(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Return"))
			return buildReturn(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Scope"))
			return buildScope(element, arguments, meta, nest);
		
		if(element.content.equalsIgnoreCase("Break"))
			return buildBreak(element, arguments, meta, nest);
		
		if(element.content.equalsIgnoreCase("Else"))
			return buildElse(element, arguments, meta, nest);
		
		if(element.content.equalsIgnoreCase("Loop"))
			return buildLoop(element, arguments, meta, nest);
		
		if(element.content.equalsIgnoreCase("Run"))
			return buildRun(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Wait"))
			return buildWait(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Split"))
			return buildSplit(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Log"))
			return buildLog(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Log Line"))
			return buildLogLine(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Input"))
			return buildInput(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Time"))
			return buildTime(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Open"))
			return buildOpen(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Save"))
			return buildSave(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("List"))
			return buildList(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Size"))
			return buildSize(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("At"))
			return buildAt(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Append"))
			return buildAppend(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Set"))
			return buildSet(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Insert"))
			return buildInsert(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Remove"))
			return buildRemove(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Concatenate"))
			return buildConcatenate(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Crop"))
			return buildCrop(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("List to String"))
			return buildListToString(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("String to List"))
			return buildStringToList(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Character to Number"))
			return buildCharacterToNumber(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Number to Character"))
			return buildNumberToCharacter(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Not"))
			return buildNot(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Is"))
			return buildIs(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Equal"))
			return buildEqual(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("And"))
			return buildAnd(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Or"))
			return buildOr(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Exclusive Or"))
			return buildExclusiveOr(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Greater"))
			return buildGreater(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Greater or Equal"))
			return buildGreaterOrEqual(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Less"))
			return buildLess(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Less or Equal"))
			return buildLessOrEqual(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Add"))
			return buildAdd(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Subtract"))
			return buildSubtract(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Multiply"))
			return buildMultiply(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Divide"))
			return buildDivide(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Modulus"))
			return buildModulus(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Random"))
			return buildRandom(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Negative"))
			return buildNegative(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Power"))
			return buildPower(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Sine"))
			return buildSine(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Cosine"))
			return buildCosine(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Tangent"))
			return buildTangent(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Square Root"))
			return buildSquareRoot(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Natural Logarithm"))
			return buildNaturalLogarithm(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Floor"))
			return buildFloor(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Ceiling"))
			return buildCeiling(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("To Radians"))
			return buildToRadians(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("To Degrees"))
			return buildToDegrees(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Absolute Value"))
			return buildAbsoluteValue(element, arguments, meta);
		
		String operation = buildOperator(element, arguments, meta);
		
		if(operation != null)
			return operation;
		
		if(getCategory(categories, "Function Names").objects.contains(element.content.toLowerCase()))
			return buildFunctionCall(element, arguments, meta);
		
		boolean isElementAlias = variables.contains(element.content.toLowerCase());
		
		if(isElementAlias) {
			
			if(element.children.size() > 0)
				return buildVariableAssignment(element, arguments, meta);
			
			else
				return buildVariableReference(element, arguments, meta);
		}
		
		if(element.children.size() > 0) {
			
			variables.add(element.content);
			
			return buildVariableDeclaration(element, arguments, meta);
		}
		
		return buildLiteral(element, arguments, meta);
	}
	
	public void addFunctions(Element element, ArrayList<Category> categories) {

		Category functions = getCategory(categories, "Function Names");
		
		if(functions == null) {
			
			functions = new Category();
			functions.name = "Function Names";
			
			categories.add(functions);
		}
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Define")) {
				
				Element define = element.children.get(i);
				
				for(int j = 0; j < define.children.size(); j++) {
					
					if(!functions.objects.contains(define.children.get(i).content.toLowerCase()))
						functions.objects.add(define.children.get(i).content.toLowerCase());
				}
			}
		}
	}
	
	public Category getCategory(ArrayList<Category> categories, String name) {
		
		for(int i = 0; i < categories.size(); i++) {
			
			if(categories.get(i).name.equalsIgnoreCase(name))
				return categories.get(i);
		}
		
		return null;
	}
	
	public String formatIdentifier(String identifier) {
		return identifier;
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body) {
		
		return body;
	}
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
	}
	
	public String buildBodyElementSeparator() {
		return "";
	}
	
	public void ammendMeta(Element meta, Element element) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			for(int j = 0; j < meta.children.size(); j++) {
				
				if(element.children.get(i).content.equalsIgnoreCase(element.children.get(j).content)) {
					meta.children.set(j, element.children.get(i));
				}
			}
		}
	}
	
	public String buildObjectOperation(
			String operator,
			String operation,
			Element meta) {
		
		return "";
	}
	
	public String buildOperator(
			Element element,
			ArrayList<String> arguments,
			Element meta) {
		
		return null;
	}
	
	public String buildLiteral(Element element, ArrayList<String> arguments, Element meta) {
		
		try {
			
			Double.parseDouble(element.content);
				
			return element.content;
		}
		
		catch(Exception exception) {
			
		}
		
		try {
			
			Boolean.parseBoolean(element.content);
				
			return element.content;
		}
		
		catch(Exception exception) {
			
		}
		
		if(element.content.indexOf('\"') == 0 &&
				element.content.lastIndexOf('\"') == element.content.length() - 1) {

			return element.content;
		}
		
		return "\"" + element.content + "\"";
	}
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildFunctionCall(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildIn(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildDefine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildGlobal(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildImport(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildCall(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildExecute(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNew(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildReturn(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildScope(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "";
	}
	
	public String buildWait(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildRun(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSplit(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildTime(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildOpen(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSave(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSize(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildAt(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCrop(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildListToString(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildStringToList(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCharacterToNumber(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNumberToCharacter(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildGreater(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildGreaterOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildLess(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildLessOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildAdd(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSubtract(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildMultiply(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildDivide(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildModulus(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildRandom(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNegative(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildPower(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSine(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCosine(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildTangent(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSquareRoot(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNaturalLogarithm(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildFloor(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCeiling(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildToRadians(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildToDegrees(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildAbsoluteValue(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
}