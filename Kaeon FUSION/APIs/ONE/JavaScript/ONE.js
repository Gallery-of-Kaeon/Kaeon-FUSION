function Element() {
	
	this.content = "";
	
	this.parent = null;
	this.children = [];
}

function createElement(content, child) {
	
	var element = new Element();
	
	if(content != null)
		element.content = content;
	
	if(child != null)
		addChild(element, child);
	
	return element;
}

function copyElement(element) {

	var newElement = createElement(element.content);
	
	for(var i = 0; i < element.children.length; i++)
		addChild(newElement, copyElement(element.children[i]));
		
	return newElement;
}

function addChild(parent, child, indices) {
	
	if(Array.isArray(child)) {
		
		if(indices != null) {
		
			var get = getChild(parent, indices);
			
			var index = getIndex(get);
			parent = get.parent;
			
			for(var i = 0; i < children.length; i++) {
		
				child.parent = parent;
				
				parent.children.splice(index + i, 0, children[i]);
			}
		}
		
		else {
			
			for(var i = 0; i < child.length; i++) {
		
				child.parent = parent;
				
				parent.children.push(child[i]);
			}
		}
	}

	else {
		
		if(indices != null) {
		
			var get = getChild(parent, indices);
			
			var index = getIndex(get);
			parent = get.parent;
			
			child.parent = parent;
			
			parent.children.splice(index, 0, child);
		}
		
		else {
		
			child.parent = parent;
			
			parent.children.push(child);
		}
	}
}

function removeChild(element, indices, amount, multiple, deep, caseSensitive) {

	var get = getChild(element, indices, amount, multiple, deep, caseSensitive);

	for(var i = 0; i < get.length; i++)
		get[i].parent.splice(getIndex(get[i]), 1);
		
	return get;
}

function getChild(element, indices, amount, multiple, deep, caseSensitive) {
	
	if(!Array.isArray(indices))
		indices = [indices];
	
	if(indices.length == 0)
		return null;
	
	var currentElement = element;
	
	for(var i = 0; i < indices.length; i++) {
		
		if(typeof indices[i] == "number") {
			
			var index = indices[i];
			
			if(index >= 0 && index < currentElement.children.length)
				currentElement = currentElement.children[index];
			
			else
				return null;
		}
		
		else {
			
			content = "" + indices[i];
			
			for(var j = 0; j < currentElement.children.length; j++) {
				
				if(!caseSensitive) {
				
					if(currentElement.children[j].content.toLowerCase() == content.toLowerCase())
						currentElement = currentElement.children[j];
				}
				
				else {
				
					if(currentElement.children[j].content == content)
						currentElement = currentElement.children[j];
				}
			}
		}
	}
	
	if(typeof indices[indices.length - 1] == "number")
		return currentElement;
	
	else {
		
		if(currentElement.parent != null)
			currentElement = currentElement.parent;
		
		if(multiple) {
			
			var children = [];
			
			var lower = caseSensitive ? content : content.toLowerCase();
			
			for(var i = 0; i < currentElement.children.length; i++) {
				
				if(currentElement.children[i].content.toLowerCase() === lower)
					children.push(currentElement.children[i]);
				
				if(deep)
					requestedChildren = requestedChildren.concat(getChildrenAs(currentElement.children[i], content, caseSensitive, deep));
			}
			
			return children;
		}
		
		else {
			
			var lower = caseSensitive ? content : content.toLowerCase();
			
			for(var i = 0; i < currentElement.children.length; i++) {
				
				if(currentElement.children[i].content.toLowerCase() === lower)
					return currentElement.children[i];
				
				if(deep) {
				
					child = getChildAs(currentElement.children[i], content, caseSensitive, deep);
					
					if(child != null)
						return child;
				}
			}
			
			return null;
		}
	}
}

function getRoot(element) {
	
	var root = element;
	
	while(root.parent != null)
		root = root.parent;
	
	return root;
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

function getPath(element) {
	
	var root = element;
	var path = [];
	
	while(root.parent != null) {
	
		path.push(getIndex(root));
	
		root = root.parent;
	}
	
	return path.reverse();
}
	
function equals(a, b, caseSensitive) {
	
	var aLower = caseSensitive ? a.content : a.content.toLowerCase();
	var bLower = caseSensitive ? b.content : b.content.toLowerCase();
	
	if(aLower != bLower || a.children.length != b.children.length)
		return false;
	
	for(var i = 0; i < a.children.length; i++) {
		
		if(!equals(a.children[i], b.children[i]))
			return false;
	}
	
	return true;
}

function toElement(list) {

	if(list.length == 0)
		return new Element();

	let element = createElement("" + list[0]);

	for(let i = 1; i < list.length; i++) {
		
		if(Array.isArray(list[i]))
			addChild(element, toElement(list[i]));
		
		else
			addChild(element, createElement("" + list[i]));
	}

	return element;
}

function toList(element) {

	let list = [];
	
	if(element.content != null)
		list.push(element.content);
	
	for(let i = 0; i < element.children.length; i++) {
		
		if(element.children[i].children.length == 0)
			list.push(element.children[i].content);
		
		else
			list.push(toList(element.children[i]));
	}
		
	return list;
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
	return writeONEAs(element.parent == null ? element : createElement("", copyElement(element)), ["-", "\n", "\t"], false);
}

function writeONEAs(element, tokens, reduced) {

	try {
	
		var write = element;
		
		if(element.content != "") {
			
			write = new Element();
			
			addChild(write, copyElement(element));
		}
		
		return writeElement(tokens, write, 0, true, reduced);
	}
	
	catch(error) {
		return "";
	}
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
	removeChild,
	getChild,
	getRoot,
	getIndex,
	getPath,
	equals,
	toElement,
	toList,
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