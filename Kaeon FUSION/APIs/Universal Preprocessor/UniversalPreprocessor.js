var kaeonFUSION = require("./KaeonFUSION.js");
var onePlus = require("./ONEPlus.js");

var tokenizer = require("./tokenizer.js");

function getLangauge(language) {

	if(language == "*") {
		// STUB - AUTO DETECT
	}

	return language.trim().toLowerCase();
}

function processKaeonFUSIONDirective(state, directive, text, index) {

	state.kaeonFUSIONInterpreter =
		state.kaeonFUSIONInterpreter ?
			state.kaeonFUSIONInterpreter :
			new kaeonFUSION.KaeonFUSION();

	var tempArgs = process.argv;
	var tempWrite = process.stdout.write.bind(process.stdout);

	process.argv = [null, null, text, index];

	process.stdout.write = (chunk, encoding, callback) => {

		if (typeof chunk === 'string')
			alpha += chunk.substring(0, chunk.length - 1);
	};

	var alpha = text.substring(0, index);
	var beta = text.substring(index);

	try {

		// STUB - ADD RETURN
		state.kaeonFUSIONInterpreter.process(onePlus.readONEPlus(directive));

		process.argv = tempArgs;
		process.stdout.write = tempWrite;

		return alpha + beta;
	}

	catch(error) {

		process.argv = tempArgs;
		process.stdout.write = tempWrite;
		
		return text;
	}
}

function processJavaScriptDirective(state, directive, text, index) {

	var tempArgs = process.argv;
	var tempWrite = process.stdout.write.bind(process.stdout);

	process.stdout.write = (chunk, encoding, callback) => {

		if (typeof chunk === 'string')
			alpha += chunk.substring(0, chunk.length - 1);
	};

	var alpha = text.substring(0, index);
	var beta = text.substring(index);

	try {

		var value = null;

		eval(
			"var tempFunc=function(text, index){" +
			directive +
			"};value=tempFunc(text, index);"
		);
	
		process.argv = tempArgs;
		process.stdout.write = tempWrite;
	
		return value != null ? "" + value : alpha + beta;
	}

	catch(error) {
	
		process.argv = tempArgs;
		process.stdout.write = tempWrite;
	
		return text;
	}
}

function processDirective(state, language, directive, text, index) {

	let directiveLanguage = getLangauge(language);

	if(directiveLanguage == "kaeon fusion")
		return processKaeonFUSIONDirective(state, directive, text, index);

	if(directiveLanguage == "javascript" || directiveLanguage == "js")
		return processJavaScriptDirective(state, directive, text, index);

	// STUB - ADD OTHER LANGUAGES

	return text;
}

// STUB - REQUIRES FURTHER TESTING
function preprocess(text) {

	let tokens = tokenizer.tokenize(["(]", "[>", "<)"], text);
	let directives = [];

	let newText = "";

	for(let i = 0; i < tokens.length; i++) {

		if(tokens[i] == "(]") {

			directives.push(
				{
					language: tokens[i + 1].trim(),
					content: tokens[i + 3].trim(),
					index: newText.length
				}
			)

			i += 4;
		}

		else
			newText += tokens[i];
	}

	let state = { };
	let indexShift = 0;

	for(let i = 0; i < directives.length; i++) {

		let tempLength = newText.length;

		newText =
			processDirective(
				state,
				directives[i].language,
				directives[i].content,
				newText,
				directives[i].index + indexShift
			);
		
		indexShift += newText.length - tempLength;
	}

	return newText;
}

module.exports = {
	getLangauge,
	processKaeonFUSIONDirective,
	processJavaScriptDirective,
	processDirective,
	preprocess
}