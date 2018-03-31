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
			
			name = "source";
			
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
			
			if(element.content.equalsIgnoreCase("Use"))
				return null;
			
			if(element.content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(meta, element, categories);
				
				return null;
			}
		}
		
		addFunctions(element, categories);
		
		ArrayList<String> arguments = new ArrayList<String>();
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		boolean trickleDown = true;
		
		if(element.content != null)
			trickleDown = trickleDown(element, meta, categories);
		
		if(trickleDown) {
			
			for(int i = 0; i < element.children.size(); i++) {
				
				if(!variables.contains(element.children.get(i).content.toLowerCase()) && element.children.get(i).children.size() > 0)
					variables.add(element.children.get(i).content.toLowerCase());
				
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
			}
		}
		
		if(element.content == null && element.parent == null) {
			
			String string = "";
			
			for(int i = 0; i < arguments.size(); i++)
				string += arguments.get(i) + buildBodyElementSeparator();
			
			return string;
		}
		
		if(element.content.equalsIgnoreCase("Define"))
			return buildDefine(element, arguments, meta, categories);
		
		if(element.content.equalsIgnoreCase("Arguments"))
			return buildArguments(element, arguments, meta);
		
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
			return buildNew(element, arguments, meta, categories);
		
		if(element.content.equalsIgnoreCase("Null"))
			return buildNull(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Literal"))
			return buildLiteralCommand(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Type"))
			return buildType(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Return"))
			return buildReturn(element, arguments, meta);
		
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
		
		if(element.content.equalsIgnoreCase("Contains"))
			return buildContains(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Index"))
			return buildIndex(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Count"))
			return buildCount(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Cut"))
			return buildCut(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Reverse"))
			return buildReverse(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Convert Sequence"))
			return buildConvertSequence(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Character to Number"))
			return buildCharacterToNumber(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Number to Character"))
			return buildNumberToCharacter(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Upper"))
			return buildUpper(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Lower"))
			return buildLower(element, arguments, meta);
		
		if(element.content.equalsIgnoreCase("Trim"))
			return buildTrim(element, arguments, meta);
		
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
		
		if(element.content.equalsIgnoreCase("Infinity"))
			return buildInfinity(element, arguments, meta);
		
		String operation = buildOperator(element, arguments, meta);
		
		if(operation != null)
			return operation;
		
		if(getCategory(categories, "Function Names").objects.contains(element.content.toLowerCase()))
			return buildFunctionCall(element, arguments, meta);
		
		if(variables.contains(element.content.toLowerCase())) {
			
			if(element.children.size() > 0)
				return buildVariableAssignment(element, arguments, meta);
			
			else
				return buildVariableReference(element, arguments, meta);
		}
		
		if(element.children.size() > 0)
			return buildVariableDeclaration(element, arguments, meta);
		
		return buildLiteral(element, arguments, meta);
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
				
				Element meta = ElementUtilities.getChild(element.children.get(i), "Functions");
				
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
		return ";";
	}
	
	public void ammendMeta(Element meta, Element element, ArrayList<Category> categories) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
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
		
		if(ElementUtilities.hasChild(element, "Functions")) {
			
			Element functions = ElementUtilities.getChild(element, "Functions");
			
			for(int i = 0; i < functions.children.size(); i++)
				getCategory(categories, "Function Names").objects.add(functions.children.get(i).content);
		}
	}
	
	public boolean trickleDown(Element element, Element meta, ArrayList<Category> categories) {
		
		if(element.content.equalsIgnoreCase("Define"))
			return false;
		
		return true;
	}
	
	public String buildObjectOperation(
			String operator,
			Element operation,
			Element meta,
			ArrayList<Category> categories,
			ArrayList<String> variables,
			int nest) {
		
		String operationString = "";
		
		if(operation.content.equalsIgnoreCase("Return"))
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
				
				operationString = buildFunctionCall(operation, arguments, meta);
			}
		}
		
		if(operator.startsWith("\"") && operator.endsWith("\"") && operator.length() > 1)
			operator = operator.substring(1, operator.length() - 1);
		
		return operator + buildObjectOperator(meta) + operationString;
	}
	
	public String buildObjectOperator(Element meta) {
		return ".";
	}
	
	public String buildOperator(Element element, ArrayList<String> arguments, Element meta) {
		return null;
	}
	
	public String buildLiteral(Element element, ArrayList<String> arguments, Element meta) {
		
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
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		
		return
				buildVariableDeclarationType(
						element,
						arguments,
						meta) +
				" " +
				buildVariableAssignment(
						element,
						arguments,
						meta);
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta) {
		return element.content + "=" + arguments.get(0);
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments, Element meta) {
		return element.content;
	}
	
	public String buildFunctionCall(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = element.content + "(";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + ")";
	}
	
	public String buildLiteralCommand(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildType(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildDefine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(metaCopy, element.children.get(i), categories);
				
				continue;
			}
			
			String definition = "Function";
			
			if(ElementUtilities.hasChild(metaCopy, "Definition"))
				definition = ElementUtilities.getChild(metaCopy, "Definition").children.get(0).content;
			
			if(definition.equalsIgnoreCase("Class")) {
						
				ArrayList<Category> newCategories = new ArrayList<Category>(categories);
				
				for(int j = 0; j < newCategories.size(); j++) {
					
					if(newCategories.get(j).name.equalsIgnoreCase("Functions") ||
							newCategories.get(j).name.equalsIgnoreCase("Function Names")) {
					
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
				
				ArrayList<String> inheritence = new ArrayList<String>();
				
				for(int j = 0; j < ElementUtilities.getChild(metaCopy, "Definition").children.get(0).children.size(); j++)
					inheritence.add(ElementUtilities.getChild(metaCopy, "Definition").children.get(0).children.get(i).content);
				
				String constructor =
						buildFunctionDefinition(
								element.children.get(i),
								buildFunctionBody(element.children.get(i), newCategories, metaCopy),
								metaCopy,
								newCategories,
								true);
				
				getCategory(categories, "Classes").objects.add(
						buildClassDefinition(
								element.children.get(i),
								constructor,
								metaCopy,
								newCategories,
								inheritence));
			}
			
			else {
				
				getCategory(categories, "Functions").objects.add(
						buildFunctionDefinition(
								element.children.get(i),
								buildFunctionBody(element.children.get(i), categories, metaCopy),
								metaCopy,
								categories,
								false));
			}
		}
		
		return null;
	}
	
	public String buildFunctionBody(Element function, ArrayList<Category> categories, Element metaCopy) {
		
		Element functionElement = ElementUtilities.copyElement(function);
		functionElement.content = null;
		
		return buildElement(
				"",
				functionElement,
				categories,
				new ArrayList<String>(),
				metaCopy,
				0);
	}
	
	public String buildFunctionDefinition(Element function, String functionBody, Element metaCopy, ArrayList<Category> categories, boolean isConstructor) {
		return "";
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> newCategories, ArrayList<String> inheritence) {
		return "";
	}
	
	public String buildArguments(Element element, ArrayList<String> arguments, Element meta) {
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
	
	public String buildCall(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildExecute(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNew(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildReturn(Element element, ArrayList<String> arguments, Element meta) {
		
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
		return arguments.get(0) + "[(" + arguments.get(1) + ")-1]";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + "[(" + arguments.get(1) + ")-1]=" + arguments.get(2);
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
	
	public String buildContains(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildIndex(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCount(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCut(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildReverse(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildConvertSequence(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildCharacterToNumber(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNumberToCharacter(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildUpper(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildLower(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildTrim(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta) {
		return "!(" + arguments.get(0) + ")";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "==");
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildIs(element, arguments, meta);
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "&&");
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "||");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "^");
	}
	
	public String buildGreater(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, ">");
	}
	
	public String buildGreaterOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, ">=");
	}
	
	public String buildLess(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "<");
	}
	
	public String buildLessOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "<=");
	}
	
	public String buildAdd(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "+");
	}
	
	public String buildSubtract(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "-");
	}
	
	public String buildMultiply(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "*");
	}
	
	public String buildDivide(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "/");
	}
	
	public String buildModulus(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "%");
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
	
	public String buildInfinity(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
}