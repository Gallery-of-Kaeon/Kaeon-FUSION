class Element {
	
	constructor() {
		
		this.content = "";
		
		this.parent = null;
		this.children = [];
	}
}

function createElement(content) {
	
	var element = new Element();
	
	if(content != null)
		element.content = content;
	
	return element;
}

function copyElement(element) {

	var newElement = createElement(element.content);
	
	for(var i = 0; i < element.length; i++)
		addChild(newElement, copyElement(element.children[i]));
		
	return newElement;
}

function addChild(parent, child) {

	child.parent = parent;
	
	parent.children.push(child);
}

function addChildren(parent, children) {
	
	for(var i = 0; i < children.length; i++) {

		child.parent = parent;
		
		parent.children.push(children[i]);
	}
}

function insertChild(parent, child, index) {

	child.parent = parent;
	
	parent.children.splice(index + i, 0, child);
}

function insertChildren(parent, children, index) {
	
	for(var i = 0; i < children.length; i++) {

		children[i].parent = parent;
		
		parent.children.splice(index + i, 0, children[i]);
	}
}

function getChild(content) {
	
	var lower = content.toLowerCase();
	
	for(var i = 0; i < element.children.length; i++) {
		
		if(element.children[i].content.toLowerCase() === lower)
			return element.children[i];
	}
	
	return null;
}

function getChildWithCase(content) {
	
	for(var i = 0; i < element.children.length; i++) {
		
		if(element.children[i].content === content)
			return element.children[i];
	}
	
	return null;
}

function getChildren(content) {
	
	var children = [];
	
	var lower = content.toLowerCase();
	
	for(var i = 0; i < element.children.length; i++) {
		
		if(element.children[i].content.toLowerCase() === lower)
			children.push(element.children[i]);
	}
	
	return children;
}

function getChildrenWithCase(content) {
	
	var children = [];
	
	for(var i = 0; i < element.children.length; i++) {
		
		if(element.children[i].content === content)
			children.push(element.children[i]);
	}
	
	return children;
}

function getIndex(element) {

	if(element.parent == null)
		return -1;
	
	for(var i = 0; i < element.parent.children.length; i++) {
		
		if(element.parent.children[i] === element)
			return i;
	}
	
	return -1;
}

function readONE(one) {
	return readONEAs(one, ["-", "\n", "\t"], false);
}

function readONEAs(one, tokens, reduced) {
	
	var element = new Element();
	
	try {
		
		var elements = getElements(one.split(tokens[1]));
		
		var currentElement = element;
		var currentNest = 0;
		
		for(var i = 0; i < elements.length; i++) {
		
			var nest = cropElement(elements[i], tokens[2]);
			
			var newElement = createElement(getElementContent(elements[i], tokens[1]));
			
			for(var j = currentNest; j > nest - 1 && currentElement.parent != null; j--)
				currentElement = currentElement.parent;
			
			if(nest > currentNest && currentElement.children.length > 0)
				currentElement = currentElement.children[currentElement.children.length - 1];
			
			addChild(currentElement, newElement);
			
			currentElement = newElement;
			currentNest = nest;
		}
	}
	
	catch(error) {
		element = new Element();
	}
	
	return element;
}

function getElements(lines) {
	
	var elements = [];
	
	for(var i = 0; i < lines.length; i++) {
		
		line = lines[i];
		
		element = [];
		element.push(lines[i]);
		
		for(i++; i < lines.length && lines[i] != line; i++)
			element.push(lines[i]);
		
		elements.push(element);
	}
	
	return elements;
}

function cropElement(element, nesting) {
	
	var nest = 0;
	
	for(var nestIndex = 0; element[0].indexOf(nesting, nestIndex) != -1; nestIndex += nesting.length)
		nest++;
		
	element.splice(0, 1);
	
	for(var i = 0; i < element.length; i++)
		element[i] = element[i].substring((nest + 1) * nesting.length);
	
	return nest;
}

function getElementContent(element, breaking) {
	
	var content = "";
	
	for(var i = 0; i < element.length; i++) {
		
		content += element[i];
		
		if(i < element.length - 1)
			content += breaking;
	}
	
	return content;
}

function writeONE(element) {
	return writeONEAs(element, ["-", "\n", "\t"], false);
}

function writeONEAs(element, tokens, reduced) {
	
	var write = element;
	
	if(element.content != "") {
		
		write = new Element();
		
		addChild(write, copyElement(element));
	}
	
	return writeElement(tokens, write, 0, true, reduced);
}

function writeElement(tokens, element, nest, isRoot, reduced) {

	var code = "";
	
	if(!isRoot) {
		
		var content = element.content;
		
		code += indent(nest, tokens[2]) + (!reduced ? tokens[0] : "") + tokens[1] + indent(nest + 1, tokens[2]);
		
		lines = content.split(tokens[1]);
		
		for(var i = 0; i < lines.length; i++) {
		
			code += lines[i];
			
			if(i < lines.length - 1)
				code += tokens[1] + indent(nest + 1, tokens[2]);
		}
		
		code += tokens[1] + indent(nest, tokens[2]) + (!reduced ? tokens[0] : "");
	}
	
	var elements = element.children;
	
	for(var i = 0; i < elements.length; i++) {
		
		if(!isRoot || i > 0)
			code += tokens[1];
		
		code += writeElement(tokens, elements[i], (!isRoot ? nest + 1 : nest), false, reduced);
	}
	
	return code;
}

function indent(nest, token) {
	
	var indent = "";
	
	for(var i = 0; i < nest; i++)
		indent += token;
	
	return indent;
}

module.exports = {

	Element,
	createElement,
	copyElement,
	addChild,
	addChildren,
	insertChild,
	insertChildren,
	getChild,
	getChildWithCase,
	getChildren,
	getChildrenWithCase,
	getIndex,
	readONE,
	readONEAs,
	getElements,
	cropElement,
	getElementContent,
	writeONE,
	writeONEAs,
	writeElement,
	indent
};