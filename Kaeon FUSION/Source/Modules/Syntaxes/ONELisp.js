var moduleDependencies = {
	one: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/Source/Engine/ONE.js",
	tokenizer: "https://raw.githubusercontent.com/Gallery-of-Kaeon/JavaScript-Utilities/master/JavaScript%20Utilities/Utilities/Data/tokenizer.js"
};

var one = require(moduleDependencies.one);
var tokenizer = require(moduleDependencies.tokenizer);

function processONELisp(data) {

	let root = new one.Element();
	
	let tokens =
		tokenizer.tokenize(
			[" ", "\t", "\n", "\"", "(", ")", "\\"],
			data
		);
	
	let currentElement = root;
	root.content = null;
	
	let isQuote = false;
	let literal = "";
	
	for(let i = 0; i < tokens.length; i++) {
		
		if(tokens[i] == "\"") {
			
			isQuote = !isQuote;
			
			if(!isQuote && literal.length > 0) {
				
				if(currentElement.content == null)
					currentElement.content = literal;
				
				else
					one.addChild(currentElement, one.createElement(literal));
				
				literal = "";
			}
			
			continue;
		}
		
		if(tokens[i] == "\\" && i < tokens.length - 1) {
			
			if(isQuote)
				literal += tokens[i + 1];
			
			else {
				
				if(currentElement.content == null)
					currentElement.content = tokens[i + 1];
				
				else
					one.addChild(currentElement, one.createElement(tokens[i + 1]));
			}
			
			i++;
			
			continue;
		}
		
		if(tokens[i] == "(" && i < tokens.length - 1) {
			
			let newElement = new one.Element();
			newElement.content = null;
			
			one.addChild(currentElement, newElement);
			
			currentElement = newElement;
		}
		
		else if(tokens[i] == ")" && currentElement.parent != null)
			currentElement = currentElement.parent;
		
		else if(isQuote)
			literal += tokens[i];
		
		else if(tokens[i] != " " && tokens[i] != "\n" && tokens[i] != "\t") {
			
			if(currentElement.content == null)
				currentElement.content = tokens[i];
			
			else
				one.addChild(currentElement, one.createElement(tokens[i]));
		}
	}
	
	root.content = "";
	
	if(currentElement.content == null)
		currentElement.content = "";
	
	return one.writeONE(root);
}

module.exports = function(item) {

	if(typeof item == "string")
		return processONELisp(item);

	item.returnValue = processONELisp(process.argv[2]);
};