package stack.utilities.cross_dialect;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.Dialect;

public class CrossDialect extends Dialect {
	
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<ArrayList<Object>> functionDefintions,
			ArrayList<ArrayList<Object>> functions,
			ArrayList<Element> arguments) {

		String name = "" + functions.get(0).get(0);
		Element main = (Element) functions.get(0).get(1);
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		buildCategories(
				name,
				main,
				buildMain(
						name,
						main,
						categories),
				categories);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return
				buildMainPrefix(name, main, categories) +
				buildMainBody(name, main, categories) +
				buildMainPostfix(name, main, categories);
	}
	
	public String buildMainPrefix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildMainPostfix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildMainBody(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		String body = "";
		Element meta = new Element();
		
		for(int i = 0; i < main.children.size(); i++) {
			
			body +=
					buildElement(
							name,
							main.children.get(i),
							categories,
							meta);
		}
		
		return body;
	}
	
	public String buildElement(
			String name,
			Element element,
			ArrayList<Category> categories,
			Element meta) {
		
		ArrayList<String> children = new ArrayList<String>();
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta"))
				ammendMeta(metaCopy, element.children.get(i));
			
			else {
				
				children.add(
						buildElement(
								name,
								element.children.get(i),
								categories,
								ElementUtilities.copyElement(metaCopy)));
			}
		}
		
		return "";
	}
	
	public void ammendMeta(Element meta, Element element) {
		
	}
	
	public String buildCategories(
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		return build;
	}
	
	// DEFINITION
	
	public String buildDefinition(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionPrefix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionPostfix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionBody(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	// UNDEFINED COMMANDS
	
	// UNDEFINED COMMANDS - BUILD
	
	public String buildLiteral(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildFunctionCall(Element element, ArrayList<String> arguments, Element meta) {
		return "";
	}
	
	// DEFINED COMMANDS
	
	/*
	 * DEFINE
	 * GLOBAL
	 * CATCH
	 * THROW
	 * IN
	 * EXECUTE
	 * CALL
	 */
	
	// DEFINED COMMANDS - CORE - MISCELLANEOUS
	
	public String buildThis(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildNew(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildNull(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - FLOW CONTROL
	
	public String buildReturn(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildScope(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildWait(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildRun(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSplit(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - CONSOLE
	
	public String buildLog(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - SYSTEM
	
	public String buildTime(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - IO
	
	public String buildOpen(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSave(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - LIST
	
	public String buildList(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSize(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildAt(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildCrop(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildListToString(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildStringToList(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildCharacterToNumber(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildNumberToCharacter(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - LOGIC
	
	public String buildNot(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildOr(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildGreater(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildGreaterOrEqual(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildLess(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildLessOrEqual(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	// DEFINED COMMANDS - Math
	
	public String buildAdd(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSubtract(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildMultiply(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildDivide(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildModulus(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildRandom(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildNegative(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildPower(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSine(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildCosine(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildTangent(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildSquareRoot(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildNaturalLogarithm(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildFloor(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildCeiling(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildToRadians(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildToDegrees(Element element, ArrayList<String> arguments) {
		return "";
	}
	
	public String buildAbsoluteValue(Element element, ArrayList<String> arguments) {
		return "";
	}
}