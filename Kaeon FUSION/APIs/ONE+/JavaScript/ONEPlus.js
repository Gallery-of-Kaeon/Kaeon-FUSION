var one = require("./ONE.js");
var tokenizer = require("./tokenizer.js");

function readONEPlus(data) {
	return readONEPlusAs(data, {});
}

function readONEPlusAs(data, host) {

	if(data.trim().startsWith("-[")) {

		return
			require(
				data.substring(data.indexOf("-[") + 2, data.indexOf("]\n")))(
					data.substring(string.indexOf("]\n") + 2));
	}

	tokens = getTokens(data);
	tokenize = tokenizer.tokenize(tokens, data);
	nestToken = getIndentToken(data);

	return process(tokens, preprocess(tokens, tokenize, nestToken), nestToken);
}

function getTokens(string) {

	return [
		"-",
		"\n",
		",",
		":",
		";",
		"(",
		")",
		"{",
		"}",
		"~",
		"\"",
		"\'",
		"[",
		"]",
		"#",
		"#[",
		"]#",
		getIndentToken(string)
	];
}

function getIndentToken(string) {

	try {

		var newLine = true;

		for(var i = 0; i < string.length; i++) {

			if(!newLine && string.charAt(i) != '\n')
				continue;

			if(string.charAt(i) == ' ' || string.charAt(i) == '\t') {

				var token = "" + string.charAt(i);
				i++;

				while(i < string.length) {

					if(string.charAt(i) == token.charAt(0))
						token += string.charAt(i);

					else
						break;

					i++;
				}

				if(i == string.length - 1)
					continue;

				if(string.charAt(i) != '\n' && string.charAt(i) != ' ' && string.charAt(i) != '\t')
					return token;
			}

			else
				newLine = string.charAt(i) == '\n';
		}
	}

	catch(error) {

	}

	return "\t";
}

function preprocess(tokens, tokenize, data) {

	var preprocess = [];

	var inLiteralBlock = -1;
	var inCommentBlock = false;

	for(var i = 0; i < tokenize.length;) {

		var line = getLine(tokenize, i);

		var shift = line.length + 1;
		var nest = getNest(line, nestToken);

		if(isLiteralBlock(line, nest) && !inCommentBlock) {

			if(inLiteralBlock == -1)
				inLiteralBlock = nest;

			else if(inLiteralBlock == nest)
				inLiteralBlock = -1;
		}

		else if(isBlankLine(line) && inLiteralBlock == -1) {

			i += shift;

			continue;
		}

		if(inLiteralBlock > -1) {

			preprocess = preprocess.concat(line);
			preprocess.push("\n");

			i += shift;

			continue;
		}

		var inLiteral = false;
		var inLiteralQuote = false;
		var inComment = false;

		for(var j = 0; j < line.length; j++) {

			if(inLiteral && !line[j] == "\'")
				continue;

			if(inLiteralQuote && !line[j] == "\"")
				continue;

			if(line[j] == "]#") {

				inCommentBlock = false;

				line.splice(j, 1);
				j--;

				continue;
			}

			if(inComment || inCommentBlock) {

				line.splice(j, 1);
				j--;

				continue;
			}

			if(line[j] == "~") {

				j++;

				continue;
			}

			if(line[j] == "\"") {

				inLiteralQuote = !inLiteralQuote;

				continue;
			}

			if(line[j] == "\'") {

				inLiteral = !inLiteral;

				continue;
			}

			if(line[j] == "#") {

				inComment = true;

				line.splice(j, 1);
				j--;

				continue;
			}

			if(line[j] == "#[") {

				inCommentBlock = true;

				line.splice(j, 1);
				j--;

				continue;
			}

			if(line[j].length == 0) {

				line.splice(j, 1);
				j--;

				continue;
			}
		}

		preprocess = preprocess.concat(line);
		preprocess.push("\n");

		i += shift;
	}

	if(preprocess.length > 0)
		preprocess.splice(preprocess.length - 1, 1);

	return preprocess;
}

function process(tokens, tokenize, nestToken) {

	var directives = [];

	var element = new one.Element();

	var currentElement = element;

	var previousNest = 0;
	var previousElement = element;

	var baseElements = [];

	var inLiteralBlock = false;
	var literalNest = 0;
	var literalString = "";

	for(var i = 0; i < tokenize.length;) {

		var line = getLine(tokenize, i);
		var nest = getNest(line, nestToken);

		if(!inLiteralBlock) {

			if(nest > previousNest) {

				baseElements.push(currentElement);

				currentElement = previousElement;
			}

			else if(nest < previousNest) {

				for(var j = nest; j < previousNest && baseElements.length > 0; j++)
					currentElement = baseElements.splice(baseElements.length - 1, 1)[0];
			}

			previousNest = nest;
		}

		var literal =
			isLiteralBlock(line, nest) &&
			!(inLiteralBlock && nest != literalNest);

		if(literal) {
			inLiteralBlock = !inLiteralBlock;
			literalNest = nest;
		}

		if(inLiteralBlock) {

			if(!literal) {

				for(var j = literalNest + 1; j < line.length; j++)
					literalString += line[j];

				literalString += '\n';
			}
		}

		else if(literal) {

			previousElement = cropElement(getElement(literalString), true);
			one.addChild(currentElement, previousElement);

			literalString = "";
		}

		else if(line.length > 0)
			previousElement = processLine(tokens, line, currentElement, directives);

		i += line.length + 1;
	}

	processDirectives(element, generateDirectives(directives));

	return element;
}

function getLine(tokenize, index) {

	var line = [];

	var openColon = 0;
	var openBracket = 0;

	for(var i = index; i < tokenize.length; i++) {
		
		if(tokenize[i] == "\n") {

			if(i > index) {

				if(tokenize[i - 1] == ":")
					openColon += 1;

				if(tokenize[i - 1] == "{")
					openBracket += 1;
			}

			if(openBracket == 0 && openColon == 0)
				break;
		}

		else {

			if(openColon > 0 && tokenize[i] == ";")
				openColon -= 1;

			if(openBracket > 0 && tokenize[i] == "}")
				openBracket -= 1;
		}

		line.push(tokenize[i]);
	}

	return line;
}

function getNest(line, nestToken) {

	var nest = 0;

	for(var i = 0; i < line.length; i++) {

		var token = line[i];

		if(token != nestToken)
			break;

		nest++;
	}

	return nest;
}

function isBlankLine(line) {

	var blank = true;

	for(var i = 0; i < line.length; i++) {

		var token = line[i];

		if(token.trim().length > 0) {

			blank = false;

			break;
		}
	}

	return blank;
}

function isLiteralBlock(line, nest) {

	if(line.length != nest + 1 || line.length == 0)
		return false;

	return line[line.length - 1] == "-";
}

function getElement(string) {

	var element = new one.Element();
	element.content = string;

	return element;
}

function cropElement(element, literal) {

	element.content = literal ?
		element.content.substring(0, element.content.length - 1) :
		element.content.trim();

	return element;
}

function processLine(tokens, line, currentElement, directives) {

	var baseElement = currentElement;

	var directive = false;

	var stack = [];
	var nestStack = [];
	var directiveStack = [];

	var newLine = preprocessLine(tokens, line);

	for(var i = 0; i < newLine.length; i++) {

		var token = newLine[i];

		if((!tokens.includes(token) || token == "-") && token.trim().length > 0) {

			var newElement = cropElement(getElement(processContent(token)), false);

			one.addChild(currentElement, newElement);

			if(directive)
				directives[directives.length - 1].push(newElement);
		}

		if(token == ":" || token == "{") {

			if(token == "{")
				nestStack.push(currentElement);

			if(currentElement.children.length > 0)
				currentElement = currentElement.children[currentElement.children.length - 1];
		}

		if(token == ";" || token == "}") {

			if(token == "}") {

				if(nestStack.length > 0)
					currentElement = nestStack.splice(nestStack.length - 1, 1)[0];
			}

			else if(currentElement.parent != null)
				currentElement = currentElement.parent;
		}

		if(token == "(")
			stack.push(currentElement);

		if(token == ")" && stack.length > 0)
			currentElement = stack.splice(stack.length - 1, 1)[0];

		if(token == "[") {

			directiveStack.push(currentElement);
			directives.push([]);

			directive = true;
		}

		if(token == "]" && directiveStack.length > 0) {

			currentElement = directiveStack.splice(directiveStack.length - 1, 1)[0];

			directive = directiveStack.length != 0;
		}
	}

	var previousElement = null;

	if(currentElement.children.length > 0)
		previousElement = currentElement.children[currentElement.children.length - 1];

	else
		previousElement = currentElement;

	currentElement = baseElement;

	return previousElement;
}

function processContent(content) {

	for(var i = 0; i < content.length; i++) {

		if(content.charAt(i) == '~') {

			content = content.substring(0, i) + content.substring(i + 1);

			if(content.length > 0) {

				if(content.charAt(i) == 'n')
					content = content.substring(0, i) + '\n' + content.substring(i + 1);

				else if(content.charAt(i) == 't')
					content = content.substring(0, i) + '\t' + content.substring(i + 1);
			}
		}
	}

	return content;
}

function preprocessLine(tokens, line) {

	var newLine = line.slice();

	while(newLine.length > 0) {

		if(!tokens.includes(newLine[0]) ||
			(newLine[0] == "~" ||
			newLine[0] == "\"" ||
			newLine[0] == "\'" ||
			newLine[0] == "[") ||
			newLine[0] == "-") {

			break;
		}

		newLine.splice(0, 1);
	}

	for(var i = 0; i < newLine.length; i++) {

		if(newLine[i] == "~") {

			if(i < newLine.length - 1)
				newLine[i] = newLine[i] + newLine.splice(i + 1, 1)[0];

			if(i > 0) {

				if(!tokens.includes(newLine[i - 1])) {

					i--;

					newLine[i] = newLine[i] + newLine.splice(i + 1, 1)[0];
				}
			}
		}

		if(newLine[i] == "\"") {

			var literal = "\"";

			while(i + 1 < newLine.length) {

				var token = newLine.splice(i + 1, 1)[0];

				if(token == "\"")
					break;

				literal += token;
			}

			newLine[i] = literal + '\"';
		}

		if(newLine[i] == "\'") {

			var literal = "";

			while(i + 1 < newLine.length) {

				var token = newLine.splice(i + 1, 1)[0];

				if(token == "\'")
					break;

				literal += token;
			}

			newLine[i] = literal;
		}
	}

	for(var i = 0; i < newLine.length - 1; i++) {

		if((!tokens.includes(newLine[i]) || newLine[i] == "-") &&
			(!tokens.includes(newLine[i + 1]) || newLine[i + 1] == "-")) {

			newLine[i] = newLine[i] + newLine.splice(i + 1, 1)[0];

			i--;
		}
	}

	return newLine;
}

function generateDirectives(elements) {

	var directives = [];

	for(var i = 0; i < elements.length; i++) {

		for(var j = 0; j < elements[i].length; j++) {

			var directive = new Directive();

			directive.directive = elements[i][j];

			for(var k = 0; k < elements[i][j].children.length; k++) {

				var isHeader = false;

				for(var l = 0; l < elements[i].length; l++) {

					if(elements[i][j].children[k] == elements[i][l]) {

						isHeader = true;

						break;
					}
				}

				if(isHeader)
					directive.header.push(elements[i][j].children[k]);

				else
					directive.body.push(elements[i][j].children[k]);
			}

			directives.push(directive);
		}
	}

	return directives;
}

function processDirectives(element, directives) {

	var directiveUnits = [];
	directiveUnits.push(new Use());

	while(hasDirectives(element, directives))
		processDirectives(element, directiveUnits, directives);
}

function processDirectivesAs(element, directiveUnits, directives) {

	if(isDirective(element, directives)) {

		for(var i = 0; i < element.children.length; i++) {

			var inHeader = false;

			var directive = getDirective(element, directives);

			for(var j = 0; j < directive.header.length; j++) {

				if(directive.header[j] == element.children[i]) {

					inHeader = true;

					break;
				}
			}

			if(inHeader) {

				processDirectives(
					element.children[i],
					directiveUnits,
					directives);
			}
		}

		for(var i = 0; i < directiveUnits.length; i++) {

			try {

				directiveUnits[i].apply(
					directiveUnits,
					directives,
					getDirective(
						element,
						directives));
			}

			catch(error) {

			}
		}

		return;
	}

	var newUnits = directiveUnits.slice();

	for(var i = 0; i < element.children.length; i++) {

		processDirectives(
			element.children[i],
			newUnits,
			directives);

		if(isDirective(element.children[i], directives)) {

			element.children.splice(i, 1);

			i--;
		}
	}
}

function hasDirectives(element, directives) {

	if(isDirective(element, directives))
		return true;

	for(var i = 0; i < element.children.length; i++) {

		if(hasDirectives(element.children[i], directives))
			return true;
	}

	return false;
}

function isDirective(element, directives) {

	for(var i = 0; i < directives.length; i++) {

		var directive = directives[i];

		if(directive.directive == element)
			return true;
	}

	return false;
}

function getDirective(element, directives) {

	for(var i = 0; i < directives.length; i++) {

		var directive = directives[i];

		if(directive.directive == element)
			return directive;
	}

	return null;
}

class Directive {

	constructor() {

		this.directive = null;

		this.header = [];
		this.body = [];
	}
}

class DirectiveUnit {

	apply(directiveUnits, directives, directive) {

	}
}

class Use extends DirectiveUnit {

	constructor() {
		super();
	}

	apply(directiveUnits, directives, directive) {

		if(directive.directive.content.toUpperCase != "USE")
			return;

		for(var i = 0; i < directive.directive.children.length; i++) {

			var child = directive.directive.children[i];

			try {
				directiveUnits = directiveUnits.concat(require(child.content)());
			}

			catch(error) {

			}
		}
	}
}

module.exports = {

	readONEPlus,
	readONEPlusAs,
	getTokens,
	getIndentToken,
	preprocess,
	process,
	getLine,
	getNest,
	isBlankLine,
	isLiteralBlock,
	getElement,
	cropElement,
	processLine,
	processContent,
	preprocessLine,
	generateDirectives,
	processDirectives,
	processDirectivesAs,
	hasDirectives,
	isDirective,
	getDirective,
	Directive,
	DirectiveUnit,
	Use
};