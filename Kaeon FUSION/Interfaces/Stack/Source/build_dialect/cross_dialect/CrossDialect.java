package build_dialect.cross_dialect;

import java.util.ArrayList;

import build_dialect.BuildDialect;
import one.Element;
import one.ElementUtilities;

public class CrossDialect extends BuildDialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		if(name == null) {
			
			name = "source";
			
			if(index > 0)
				name += "_" + index;
		}
		
		Element main = code.get(0);
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		boolean utility = false;
		boolean snippet = false;
		
		for(int i = 0; i < arguments.size(); i++) {
			
			if(("" + arguments.get(i)).equalsIgnoreCase("Utility"))
				utility = true;
			
			if(("" + arguments.get(i)).equalsIgnoreCase("Snippet"))
				snippet = true;
		}
		
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
								0),
						utility,
						snippet);
		
		buildCategories(
				files,
				name,
				main,
				mainBuild,
				categories,
				utility,
				snippet);
	}
	
	public String buildElement(
			String name,
			Element element,
			ArrayList<Category> categories,
			ArrayList<String> variables,
			Element meta,
			int nest) {
		
		try {
				
			if(element.content.equalsIgnoreCase("Use"))
				return null;
			
			if(element.content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(meta, element, categories);
				
				return getInjection(meta);
			}
			
			String arraySize = "Variable";
			int arrayIndex = 1;
			
			Element arrays = ElementUtilities.getChild(meta, "Arrays");
			
			if(arrays != null) {
				
				if(ElementUtilities.hasChild(arrays, "Size"))
					arraySize = ElementUtilities.getChild(arrays, "Size").content;
				
				if(ElementUtilities.hasChild(arrays, "Index"))
					arrayIndex = Integer.parseInt(ElementUtilities.getChild(arrays, "Index").content);
			}
			
			addFunctions(element, categories);
			
			ArrayList<String> arguments = new ArrayList<String>();
			
			Element metaCopy = ElementUtilities.copyElement(meta);
			
			boolean trickleDown = true;
			
			if(element.content != null)
				trickleDown = trickleDown(element, meta, categories);
			
			if(trickleDown) {
				
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
							
							if(element.children.get(i).equals("Meta")) {
								
								ammendMeta(metaCopy, element.children.get(i), categories);
								
								continue;
							}
							
							Element operation = element.children.get(j);
							
							if(operation != null) {
								
								arguments.add(
										buildObjectOperation(
												operator,
												operation,
												metaCopy,
												categories,
												variables,
												nest));
							}
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
					
					if(!variables.contains(element.children.get(i).content.toLowerCase()) && element.children.get(i).children.size() > 0)
						variables.add(element.children.get(i).content.toLowerCase());
				}
			}
			
			if(element.parent == null) {
				
				String string = "";
				
				for(int i = 0; i < arguments.size(); i++)
					string += arguments.get(i) + buildBodyElementSeparator();
				
				return string;
			}
			
			if(element.content.equalsIgnoreCase("Throw"))
				return buildThrow(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Catch"))
				return buildCatch(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Exit"))
				return buildExit(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Exeption"))
				return buildException(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Out"))
				return buildOut(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Retrieve"))
				return buildRetrieve(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Shift"))
				return buildShift(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Operating System"))
				return buildOperatingSystem(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Year"))
				return buildYear(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Month"))
				return buildMonth(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Day"))
				return buildDay(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Hour"))
				return buildHour(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Minute"))
				return buildMinute(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Second"))
				return buildSecond(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Weekday"))
				return buildWeekday(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("List to Element"))
				return buildListToElement(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Element to List"))
				return buildElementToList(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Tokenize"))
				return buildTokenize(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Adapt"))
				return buildAdapt(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Generate"))
				return buildGenerate(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Form"))
				return buildForm(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Is Command"))
				return buildIsCommand(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Define"))
				return buildDefine(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Arguments"))
				return buildArguments(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Import"))
				return buildImport(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Global"))
				return buildGlobal(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Execute"))
				return buildExecute(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Call"))
				return buildCall(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("This"))
				return buildThis(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("New"))
				return buildNew(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Null"))
				return buildNull(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Literal"))
				return buildLiteralCommand(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Type"))
				return buildType(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Return"))
				return buildReturn(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Scope")) {
				
				if(element.children.size() >= 2) {
					
					if(element.children.get(0).content.equalsIgnoreCase("In")) {
						
						Element in = element.children.get(0);
						
						String operator = 
								buildElement(
										name,
										in.children.get(0),
										categories,
										new ArrayList<String>(variables),
										meta,
										nest + 1);
						
						Element operation = element.children.get(1);
						
						return
								buildObjectOperation(
										operator,
										operation,
										meta,
										categories,
										variables,
										nest);
					}
				}
				
				return buildScope(element, arguments, meta, nest);
			}
			
			if(element.content.equalsIgnoreCase("Break"))
				return buildBreak(element, arguments, meta, nest);
			
			if(element.content.equalsIgnoreCase("Else"))
				return buildElse(element, arguments, meta, nest);
			
			if(element.content.equalsIgnoreCase("Loop"))
				return buildLoop(element, arguments, meta, nest);
			
			if(element.content.equalsIgnoreCase("Run"))
				return buildRun(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Wait"))
				return buildWait(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Split"))
				return buildSplit(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Log"))
				return buildLog(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Log Line"))
				return buildLogLine(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Input"))
				return buildInput(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Time"))
				return buildTime(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Open"))
				return buildOpen(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Save"))
				return buildSave(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("List"))
				return buildList(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Size"))
				return buildSize(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("At"))
				return buildAt(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Append"))
				return buildAppend(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Set"))
				return buildSet(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Insert"))
				return buildInsert(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Remove"))
				return buildRemove(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Concatenate"))
				return buildConcatenate(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Crop"))
				return buildCrop(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Contains"))
				return buildContains(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Index"))
				return buildIndex(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Count"))
				return buildCount(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Cut"))
				return buildCut(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Reverse"))
				return buildReverse(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Convert Sequence"))
				return buildConvertSequence(element, arguments, meta, categories, arraySize, arrayIndex);
			
			if(element.content.equalsIgnoreCase("Character to Number"))
				return buildCharacterToNumber(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Number to Character"))
				return buildNumberToCharacter(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Upper"))
				return buildUpper(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Lower"))
				return buildLower(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Trim"))
				return buildTrim(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Not"))
				return buildNot(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Is"))
				return buildIs(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Equal"))
				return buildEqual(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("And"))
				return buildAnd(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Or"))
				return buildOr(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Exclusive Or"))
				return buildExclusiveOr(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Greater"))
				return buildGreater(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Greater or Equal"))
				return buildGreaterOrEqual(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Less"))
				return buildLess(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Less or Equal"))
				return buildLessOrEqual(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Add"))
				return buildAdd(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Subtract"))
				return buildSubtract(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Multiply"))
				return buildMultiply(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Divide"))
				return buildDivide(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Modulus"))
				return buildModulus(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Random"))
				return buildRandom(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Negative"))
				return buildNegative(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Power"))
				return buildPower(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Sine"))
				return buildSine(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Cosine"))
				return buildCosine(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Tangent"))
				return buildTangent(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Square Root"))
				return buildSquareRoot(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Natural Logarithm"))
				return buildNaturalLogarithm(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Floor"))
				return buildFloor(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Ceiling"))
				return buildCeiling(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("To Radians"))
				return buildToRadians(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("To Degrees"))
				return buildToDegrees(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Absolute Value"))
				return buildAbsoluteValue(element, arguments, meta, categories);
			
			if(element.content.equalsIgnoreCase("Infinity"))
				return buildInfinity(element, arguments, meta, categories);
			
			String operation = buildOperator(element, arguments, meta, categories);
			
			if(operation != null)
				return operation;
			
			if(getCategory(categories, "Function Names").objects.contains(element.content.toLowerCase()))
				return buildFunctionCall(element, arguments, meta, categories);
			
			if(variables.contains(element.content.toLowerCase())) {
				
				if(element.children.size() > 0)
					return buildVariableAssignment(element, arguments, meta, categories);
				
				else
					return buildVariableReference(element, arguments, meta, categories);
			}
			
			if(element.children.size() > 0)
				return buildVariableDeclaration(element, arguments, meta, categories);
			
			return buildLiteral(element, arguments, meta, categories);
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public void addFunctions(Element element, ArrayList<Category> categories) {

		Category functions = getCategory(categories, "Function Names");
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Define")) {
				
				Element define = element.children.get(i);
				
				for(int j = 0; j < define.children.size(); j++) {
					
					if(!functions.objects.contains(define.children.get(j).content.toLowerCase()))
						functions.objects.add(define.children.get(j).content.toLowerCase());
				}
			}
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				Element meta = ElementUtilities.getChild(element.children.get(i), "Host");
				
				if(meta != null) {
					
					for(int j = 0; j < meta.children.size(); j++) {
						
						if(!functions.objects.contains(meta.children.get(j).content.toLowerCase()))
							functions.objects.add(meta.children.get(j).content.toLowerCase());
					}
				}
			}
		}
	}
	
	public Category getCategory(ArrayList<Category> categories, String name) {
		
		for(int i = 0; i < categories.size(); i++) {
			
			if(categories.get(i).name.equalsIgnoreCase(name))
				return categories.get(i);
		}
		
		Category category = new Category();
		category.name = name;
		
		categories.add(category);
		
		return category;
	}
	
	public String formatIdentifier(String identifier) {
		return identifier;
	}
	
	public String buildInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(" + arguments.get(0);
		
		for(int i = 1; i < arguments.size(); i++)
			build += infix + arguments.get(i);
		
		return build + ")";
	}
	
	public String buildLogicInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(";
		
		for(int i = 0; i < arguments.size() - 1; i++) {
			
			build += "(" + arguments.get(i) + infix + arguments.get(i + 1) + ")";
			
			if(i < arguments.size() - 2)
				build += " and ";
		}
		
		return build + ")";
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body,
			boolean utility,
			boolean snippet) {
		
		return body;
	}
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
	}
	
	public String buildBodyElementSeparator() {
		return ";";
	}
	
	public void ammendMeta(Element meta, Element element, ArrayList<Category> categories) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Nullify"))
				continue;
			
			if(element.children.get(i).content.equalsIgnoreCase("Notation")) {
				
				Element notation = ElementUtilities.getChild(meta, "Notation");
				
				if(notation == null)
					ElementUtilities.addChild(meta, element.children.get(i));
				
				else
					ammendNotation(notation, element.children.get(i), categories);
				
				continue;
			}
			
			boolean found = false;
			
			for(int j = 0; j < meta.children.size(); j++) {
				
				if(element.children.get(i).content.equalsIgnoreCase(meta.children.get(j).content)) {
					
					ElementUtilities.removeChild(meta, j);
					ElementUtilities.addChild(meta, ElementUtilities.copyElement(element.children.get(i)), j);
					
					found = true;
					
					break;
				}
			}
			
			if(!found)
				ElementUtilities.addChild(meta, ElementUtilities.copyElement(element.children.get(i)));
		}
		
		if(ElementUtilities.hasChild(element, "Host")) {
			
			Element functions = ElementUtilities.getChild(element, "Host");
			
			for(int i = 0; i < functions.children.size(); i++)
				getCategory(categories, "Function Names").objects.add(functions.children.get(i).content);
		}
		
		if(ElementUtilities.hasChild(element, "Nullify")) {

			Element nullify = ElementUtilities.getChild(element, "Nullify");
			
			for(int i = 0; i < nullify.children.size(); i++) {
				
				if(nullify.children.get(i).content.equals("Notation")) {
					
					Element notation = ElementUtilities.getChild(meta, "Notation");
					
					if(notation != null)
						nullifyNotation(notation, nullify.children.get(i), categories);
				}
				
				else
					ElementUtilities.removeChild(meta, nullify.children.get(i).content);
			}
		}
	}
	
	public void ammendNotation(Element metaNotation, Element notation, ArrayList<Category> categories) {
		
	}
	
	public void nullifyNotation(Element metaNotation, Element notation, ArrayList<Category> categories) {
		
	}
	
	public boolean trickleDown(Element element, Element meta, ArrayList<Category> categories) {
		
		if(element.content.equalsIgnoreCase("Define"))
			return false;
		
		return true;
	}
	
	public String buildCast(String operation, Element meta, ArrayList<Category> categories) {
		
		Element cast = ElementUtilities.getChild(meta, "Cast");
		
		if(cast == null)
			return operation;
		
		ArrayList<String> type = new ArrayList<String>();
		
		for(int i = 0; i < cast.children.size(); i++)
			type.add(cast.children.get(i).content);
		
		ElementUtilities.removeChild(meta, "Cast");
		
		return buildCastOperation(operation, buildCastType(type));
	}
	
	public String buildCastOperation(String operation, String cast) {
		return "(" + cast + ")" + "(" + operation + ")";
	}
	
	public String buildCastType(ArrayList<String> type) {
		return "";
	}
	
	public String buildObjectOperation(
			String operator,
			Element operation,
			Element meta,
			ArrayList<Category> categories,
			ArrayList<String> variables,
			int nest) {
		
		String operationString = "";
		
		if(operation.content.equalsIgnoreCase("Retrieve") || operation.content.equalsIgnoreCase("Return"))
			operationString = operation.children.get(0).content;
		
		else {
			
			if(ElementUtilities.hasChild(meta, "Field")) {
				
				if(ElementUtilities.hasChild(ElementUtilities.getChild(meta, "Field"), "Variable")) {
				
					if(operation.children.size() == 0)
						operationString = operation.content;
				
					else {
						
						ArrayList<String> newVariables = new ArrayList<String>(variables);
						newVariables.add(operation.content);
						
						operationString =
								buildElement(
										"",
										operation,
										categories,
										newVariables,
										meta,
										nest + 1);
					}
				}
			}
			
			else {
				
				ArrayList<String> arguments = new ArrayList<String>();
				
				for(int i = 0; i < operation.children.size(); i++) {
					
					String argument =
							buildElement(
									"",
									operation.children.get(i),
									categories,
									new ArrayList<String>(variables),
									meta,
									nest + 1);
					
					if(argument != null)
						arguments.add(argument);
				}
				
				operationString = buildFunctionCall(operation, arguments, meta, categories);
			}
		}
		
		if(operator.startsWith("\"") && operator.endsWith("\"") && operator.length() > 1)
			operator = operator.substring(1, operator.length() - 1);
		
		return operator + buildObjectOperator(meta) + operationString;
	}
	
	public String buildObjectOperator(Element meta) {
		return ".";
	}
	
	public String buildOperator(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return null;
	}
	
	public String buildLiteral(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		try {
			
			Double.parseDouble(element.content);
				
			return element.content;
		}
		
		catch(Exception exception) {
			
		}
		
		if(element.content.equalsIgnoreCase("true") || element.content.equalsIgnoreCase("false"))
			return element.content.toLowerCase();
		
		if(element.content.indexOf('\"') == 0 &&
				element.content.lastIndexOf('\"') == element.content.length() - 1) {

			if(element.content.length() > 1)
				return element.content;
			
			return "\"\"";
		}
		
		return "\"" + element.content + "\"";
	}
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String type =
				buildVariableDeclarationType(
						element,
						arguments,
						meta,
						categories);
		
		return
				type +
				(type.length() > 0 ? " " : "") +
				buildVariableAssignment(
						element,
						arguments,
						meta,
						categories);
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return element.content + "=" + arguments.get(0);
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return element.content;
	}
	
	public String buildFunctionCall(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = element.content + "(";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + ")";
	}
	
	public String buildWeekday(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildSecond(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildMinute(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildHour(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildDay(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildMonth(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildYear(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildOperatingSystem(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildOut(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildRetrieve(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildShift(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildException(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildExit(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildThrow(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildCatch(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildAdapt(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildGenerate(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildForm(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildIsCommand(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildLiteralCommand(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildDefine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(metaCopy, element.children.get(i), categories);
				
				continue;
			}
			
			Element function = ElementUtilities.getChild(metaCopy, "Function");
			
			if(function == null)
				function = ElementUtilities.createElement("Function");

			int parameterNumber = 0;
			ArrayList<Category> parameters = new ArrayList<Category>();

			if(ElementUtilities.hasChild(function, "Parameter Number"))
				parameterNumber = Integer.parseInt(ElementUtilities.getChild(function, "Parameter Number").children.get(0).content);

			if(ElementUtilities.hasChild(function, "Parameters")) {
				
				Element parameterData = ElementUtilities.getChild(function, "Parameters");
				
				for(int j = 0; j < parameterData.children.size(); j++) {
					
					Category parameter = new Category();
					
					parameter.name = parameterData.children.get(j).content;
					
					for(int k = 0; k < parameterData.children.get(j).children.size(); k++)
						parameter.objects.add(parameterData.children.get(j).children.get(k).content);
					
					parameters.add(parameter);
				}
			}
			
			if(ElementUtilities.hasChild(function, "Class")) {
						
				ArrayList<Category> newCategories = new ArrayList<Category>(categories);
				
				for(int j = 0; j < newCategories.size(); j++) {
					
					if(newCategories.get(j).name.equalsIgnoreCase("Functions") ||
							newCategories.get(j).name.equalsIgnoreCase("Function Names") ||
							newCategories.get(j).name.equalsIgnoreCase("Global")) {
					
						newCategories.remove(j);
						
						j--;
					}
				}
				
				Category functions = new Category();
				
				functions.name = "Functions";
				
				newCategories.add(functions);
				
				Category functionNames = new Category();
				
				functionNames.name = "Function Names";
				functionNames.objects = new ArrayList<Object>(getCategory(categories, "Function Names").objects);
				
				newCategories.add(functionNames);
				
				Category global = new Category();
				
				functionNames.name = "Global";
				functionNames.objects = new ArrayList<Object>(getCategory(categories, "Global").objects);
				
				newCategories.add(global);
				
				ArrayList<String> inheritence = new ArrayList<String>();
				
				Element classElement = ElementUtilities.getChild(function, "Class");
				
				for(int j = 0; j < classElement.children.size(); j++)
					inheritence.add(classElement.children.get(i).content);
				
				String constructor =
						buildFunctionDefinition(
								element.children.get(i),
								buildFunctionBody(element.children.get(i), newCategories, metaCopy, parameters),
								metaCopy,
								newCategories,
								new ArrayList<String>(),
								true,
								parameters,
								parameterNumber);
				
				getCategory(categories, "Classes").objects.add(
						buildClassDefinition(
								element.children.get(i),
								constructor,
								metaCopy,
								newCategories,
								inheritence));
			}
			
			else {
				
				ArrayList<String> returnType = new ArrayList<String>();
				
				if(ElementUtilities.hasChild(function, "Type")) {
					
					Element type = ElementUtilities.getChild(function, "Type");
					
					for(int j = 0; j < type.children.size(); j++)
						returnType.add(type.children.get(j).content);
				}
				
				getCategory(categories, "Functions").objects.add(
						buildFunctionDefinition(
								element.children.get(i),
								buildFunctionBody(element.children.get(i), categories, metaCopy, parameters),
								metaCopy,
								categories,
								returnType,
								ElementUtilities.hasChild(function, "Constructor"),
								parameters,
								parameterNumber));
			}
		}
		
		return null;
	}
	
	public String buildFunctionBody(Element function, ArrayList<Category> categories, Element metaCopy, ArrayList<Category> parameters) {
		
		Element functionElement = ElementUtilities.copyElement(function);
		functionElement.content = "";
		
		ArrayList<String> variables = new ArrayList<String>();
		
		for(int i = 0; i < parameters.size(); i++)
			variables.add(parameters.get(i).name);
		
		return buildElement(
				"",
				functionElement,
				categories,
				variables,
				metaCopy,
				0);
	}
	
	public String buildFunctionDefinition(
			Element function,
			String functionBody,
			Element metaCopy,
			ArrayList<Category> categories,
			ArrayList<String> returnType,
			boolean isConstructor,
			ArrayList<Category> parameters,
			int parameterNumber) {
		
		return "";
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> newCategories, ArrayList<String> inheritence) {
		return "";
	}
	
	public String buildArguments(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "arguments";
	}
	
	public String buildGlobal(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {

		for(int i = 0; i < element.children.size(); i++)
			getCategory(categories, "Global").objects.add(element.children.get(i).content);
		
		return null;
	}
	
	public String buildImport(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		for(int i = 0; i < element.children.size(); i++)
			getCategory(categories, "Imports").objects.add(element.children.get(i).content);
		
		return null;
	}
	
	public String buildCall(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildExecute(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "this";
	}
	
	public String buildNew(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "null";
	}
	
	public String buildReturn(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "return ";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build;
	}
	
	public String buildScope(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "while(true){";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + buildBodyElementSeparator();;
		
		return build + "break;}";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "true") +
				"){scope=true;break;}";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "if(scope){scope=false;while(true){";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + buildBodyElementSeparator();;
		
		return build + "break;}}";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "true") +
				"){scope=true;continue;}";
	}
	
	public String buildWait(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildRun(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildSplit(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildTime(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildOpen(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildSave(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildSize(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildAt(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + "[(" + arguments.get(1) + ")-" + index + "]";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + "[(" + arguments.get(1) + ")-" + index + "]=" + arguments.get(2);
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCrop(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildContains(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildIndex(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCount(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCut(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildReverse(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildConvertSequence(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildListToElement(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildElementToList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildTokenize(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCharacterToNumber(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNumberToCharacter(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildUpper(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildLower(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildTrim(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "!(" + arguments.get(0) + ")";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildLogicInfix(element, arguments, meta, "==");
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildIs(element, arguments, meta, categories);
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "&&");
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "||");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "^");
	}
	
	public String buildGreater(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildLogicInfix(element, arguments, meta, ">");
	}
	
	public String buildGreaterOrEqual(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildLogicInfix(element, arguments, meta, ">=");
	}
	
	public String buildLess(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildLogicInfix(element, arguments, meta, "<");
	}
	
	public String buildLessOrEqual(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildLogicInfix(element, arguments, meta, "<=");
	}
	
	public String buildAdd(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "+");
	}
	
	public String buildSubtract(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "-");
	}
	
	public String buildMultiply(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "*");
	}
	
	public String buildDivide(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "/");
	}
	
	public String buildModulus(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, "%");
	}
	
	public String buildRandom(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNegative(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "(-(" + arguments.get(0) + "))";
	}
	
	public String buildPower(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildSine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildCosine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildTangent(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildSquareRoot(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNaturalLogarithm(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildFloor(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildCeiling(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildToRadians(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildToDegrees(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildAbsoluteValue(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildInfinity(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
}