// DEPENDENCIES

var one = require("./ONE.js");
var fusion = require("./FUSION.js");
var philosophersStone = require("./PhilosophersStone.js");
var onePlus = require("./ONEPlus.js");

var io = require("./io.js");
var tokenizer = require("./tokenizer.js");

var fs = {};
var path = {};
var cmd = {};

try {
	fs = require("fs");
	path = require("path");
	cmd = require("node-cmd");
}

catch(error) {

}

var platform = "Browser";

if(typeof process === 'object') {

	if(typeof process.versions === 'object') {

		if(typeof process.versions.node !== 'undefined') {
			platform = "Node";
		}
	}
}

// USE OVERRIDE STONE

function useCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Use");

	this.fusion = null;

	var reference = this;

	this.verify = function(element) {

		if(reference.fusion == null) {

			reference.fusion =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "FUSION");
					}
				)[0];
		}

		return element.content.toLowerCase() == "use";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < processed.length; i++) {

			try {

				let path = "" + processed[i];

				if(
					!(path.startsWith("http://") || path.startsWith("https://")) ||
					platform.toLowerCase() == "browser") {

					if(!(path.startsWith("http://") || path.startsWith("https://"))) {
						
						if(path.indexOf("/") == -1)
							path = "./" + path;

						if(!path.toLowerCase().endsWith(".js"))
							path += ".js";
					}

					require(path)(reference.fusion);
				}

				else {

					io.save(io.open(path), "./OnlineInterface.js")

					require("./OnlineInterface.js")(reference.fusion);
				}

				reference.fusion.update();
			}

			catch(error) {
				
			}
		}

		return null;
	}
}

// UTILITY STONES

function state() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("State");

	this.data = [[], [], []]; // [Global, Base Scope, Sub Scope 1, ..., Sub Scope N]

	var reference = this;

	this.verify = function(element) {
		return true;
	}
	
	this.trickleDown = function(element) {
		
		reference.push();
		
		return true;
	}
	
	this.jump = function(element, processed) {
		
		reference.pop();
		
		return null;
	}

	this.getAlias = function(alias, scope /* true = global ; false = local */) {

		let lower = alias.toLowerCase();

		let start = reference.data.length - 1;
		let end = 0;

		if(scope != null) {
			
			if(scope)
				start = 0;
		
			else
				end = 1;
		}

		for(let i = start; i >= end; i--) {

			for(let j = reference.data[i].length - 1; j >= 0; j--) {
				
				if(reference.data[i][j][0].toLowerCase() == lower)
					return reference.data[i][j];
			}
		}

		return null;
	}

	this.getAllAliases = function(scope /* true = global ; false = local */) {

		let aliases = [];

		let start = reference.data.length - 1;
		let end = 0;

		if(scope != null) {
			
			if(scope)
				start = 0;
		
			else
				end = 1;
		}

		for(let i = start; i >= end; i--) {

			for(let j = reference.data[i].length - 1; j >= 0; j--) {

				if(!aliases.includes(reference.data[i][j]))
					aliases.push(reference.data[i][j]);
			}
		}

		return aliases;
	}

	this.delete = function(alias, scope /* true = global ; false = local */) {

		let lower = alias.toLowerCase();

		let start = reference.data.length - 1;
		let end = 0;

		if(scope != null) {
			
			if(scope)
				start = 0;
		
			else
				end = 1;
		}

		for(let i = start; i >= end; i--) {

			for(let j = reference.data[i].length - 1; j >= 0; j--) {
				
				if(reference.data[i][j][0].toLowerCase() == lower) {

					reference.data[i].splice(j, 1);

					return;
				}
			}
		}

		return null;
	}

	this.has = function(alias, scope /* true = global ; false = local */) {
		return reference.getAlias(alias, scope) != null;
	}

	this.get = function(alias) {
		
		let item = reference.getAlias(alias);

		return item != null ? item[1] : null;
	}

	this.set = function(alias, value) {

		let item = reference.getAlias(alias);

		if(item == null)
			reference.data[reference.data.length - 2].push([alias, value]);

		else
			item[1] = value
	}

	this.globalize = function(alias, value) {

		let item = reference.getAlias(alias);

		reference.delete(alias);

		if(item != null)
			reference.data[0].push(item);
		
		else
			reference.data[0].push([alias, null]);
	}

	this.serialize = function() {
		return reference.data.slice(0);
	}

	this.deserialize = function(data) {
		reference.data = data.slice(0);
	}

	this.push = function() {
		reference.data.push([]);
	}

	this.pop = function() {
		
		if(reference.data.length > 2)
			reference.data.splice(reference.data.length - 1, 1);
	}
}

// STUB
function priority() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Priority");

	this.verify = function(element) {
		return true;
	}
}

// UNDEFINED COMMAND STONES

function literal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Literal");

	this.fusion = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.fusion == null) {

				reference.fusion =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "FUSION");
						}
					)[0];
			}

			for(let i = 0; i < reference.fusion.fusionUnits.length; i++) {

				if(reference.fusion.fusionUnits[i] == reference ||
					philosophersStone.isTagged(reference.fusion.fusionUnits[i], "State") ||
					philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Priority")) {

					continue;
				}

				if(reference.fusion.fusionUnits[i].verify(element)) {
					return false;
				}
			}
		}

		catch(error) {

		}

		return true;
	}

	this.process = function(element, processed) {

		let value = element.content;

		if(value.startsWith("\"") && value.endsWith("\""))
			value = value.substring(1, value.length - 1);
		
		value = value.split("\\n").join("\n");
		value = value.split("\\t").join("\t");

		value = value.split("\\\\").join("\\");

		return value;
	}
}

function variable() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Variable");

	this.fusion = null;
	this.state = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.fusion == null) {

				reference.fusion =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "FUSION");
						}
					)[0];
			}

			if(reference.state == null) {

				reference.state =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "State");
						}
					)[0];
			}

			for(let i = 0; i < reference.fusion.fusionUnits.length; i++) {

				if(reference.fusion.fusionUnits[i] == reference ||
					philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Literal") ||
					philosophersStone.isTagged(reference.fusion.fusionUnits[i], "State") ||
					philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Priority")) {
					
					continue;
				}

				if(reference.fusion.fusionUnits[i].verify(element)) {
					return false;
				}
			}

			return element.children.length > 0 || reference.state.has(element.content);
		}

		catch(error) {

		}

		return false;
	}

	this.process = function(element, processed) {

		if(processed.length > 0)
			reference.state.set(element.content, processed[0]);

		return reference.state.get(element.content);
	}
}

// DEFINED COMMAND STONES

function define() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "define";
	}

	this.trickleDown = function(element) {
		return false;
	}

	this.process = function(element, processed) {

		let defined = one.copyElement(element);
		defined.content = "";

		return one.toList(defined);
	}
}

function global() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.state == null) {

				reference.state =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "State");
						}
					)[0];
			}
		}

		catch(error) {
			
		}

		return element.content.toLowerCase() == "global";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < element.children.length; i++)
			reference.state.globalize(element.children[i].content);

		return null;
	}
}

function doCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusion = null;
	this.state = null;
	this.argumentsCommand = null;
	this.returnCommand = null;

	var reference = this;

	this.verify = function(element) {

		try {

			reference.fusion =
				reference.fusion == null ?
					getStone(reference, "FUSION") :
					reference.fusion;

			reference.state =
				reference.state == null ?
					getStone(reference, "State") :
					reference.state;

			reference.argumentsCommand =
				reference.argumentsCommand == null ?
					getStone(reference, "Arguments") :
					reference.argumentsCommand;

			reference.returnCommand =
				reference.returnCommand == null ?
					getStone(reference, "Return") :
					reference.returnCommand;
		}

		catch(error) {
			
		}

		return element.content.toLowerCase() == "do";
	}

	this.process = function(element, processed) {

		return executeFunction(
			element,
			reference.fusion,
			reference.state,
			reference.argumentsCommand,
			reference.returnCommand,
			one.toElement(processed[0]),
			processed.length > 1 ? processed[1] : null
		);
	}
}

function argumentsCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Arguments");

	this.args = null;

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "arguments";
	}

	this.process = function(element, processed) {
		return reference.args == null ? process.argv.slice(2) : reference.args;
	}
}

function thisCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		if(reference.state == null) {

			reference.state =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "State");
					}
				)[0];
		}

		return element.content.toLowerCase() == "this";
	}

	this.process = function(element, processed) {
		return reference.state.serialize();
	}
}

function newCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "new";
	}

	this.process = function(element, processed) {

		if(element.children.length > 0) {

			if(element.children[0].savedState != null)
				return element.children[0].savedState;
		}

		return null;
	}
}

function nullCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "null";
	}

	this.process = function(element, processed) {
		return null;
	}
}

function literalCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "literal";
	}

	this.trickleDown = function(element) {
		return false;
	}

	this.process = function(element, processed) {
		return element.children.length > 0 ? element.children[0].content : "";
	}
}

function type() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "type";
	}

	this.process = function(element, processed) {
		return typeof processed[0];
	}
}

function form() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "form";
	}

	this.process = function(element, processed) {

		let form = ("" + processed[0]).toLowerCase();

		if(form == "true" || form == "false")
			return "Boolean";

		return isNaN(form) ? "String" : "Number";
	}
}

function isCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusion = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.fusion == null) {

				reference.fusion =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "FUSION");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "is command";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < reference.fusion.fusionUnits.length; i++) {

			if(philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Literal") ||
				philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Variable") ||
				philosophersStone.isTagged(reference.fusion.fusionUnits[i], "State") ||
				philosophersStone.isTagged(reference.fusion.fusionUnits[i], "Priority")) {

				continue;
			}

			if(reference.fusion.fusionUnits[i].verify(one.createElement("" + processed[0]))) {
				return true;
			}
		}

		return false;
	}
}

function isVariable() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.state == null) {

				reference.state =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "State");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "is variable";
	}

	this.process = function(element, processed) {

		let scope = null;

		if(processed.length > 1)
			scope = ("" + processed[1]).toLowerCase() == "true";

		return reference.state.has("" + processed[0], scope);
	}
}

function destroy() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.state == null) {

				reference.state =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "State");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "destroy";
	}

	this.process = function(element, processed) {

		let scope = null;

		if(processed.length > 1)
			scope = ("" + processed[1]).toLowerCase() == "true";

		return reference.state.delete("" + processed[0], scope);
	}
}

function variables() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.state == null) {

				reference.state =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "State");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "variables";
	}

	this.process = function(element, processed) {

		let scope = null;

		if(processed.length > 1)
			scope = ("" + processed[1]).toLowerCase() == "true";
		
		let aliases = reference.state.getAllAliases(scope);
		let value = [];

		for(let i = aliases.length - 1; i >= 0; i--)
			value.push(aliases[i][0]);

		return value;
	}
}

function getCode() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "get code";
	}

	this.process = function(element, processed) {
		
		let current = element;
		
		while(current.parent != null)
			current = current.parent;
		
		return one.toList(current);
	}
}

function getCodeIndex() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "get code index";
	}

	this.process = function(element, processed) {
		
		let indexes = [];
		
		let current = element;
		
		while(current.parent != null) {
			
			indexes.push(one.getIndex(current));
			
			current = current.parent;
		}
		
		return indexes;
	}
}

function disable() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Disable");

	this.block = [];

	var reference = this;

	this.deny = function(element) {
		
		for(let i = 0; i < reference.block.length; i++) {
			
			if(element.content.toLowerCase() == ("" + reference.block[i]).toLowerCase())
				return true;
		}
		
		return false;
	}

	this.verify = function(element) {
		return element.content.toLowerCase() == "disable";
	}

	this.process = function(element, processed) {

		reference.block = reference.block.concat(processed);

		return null;
	}
}

function enable() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.disable = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.disable == null) {

				reference.disable =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "Disable");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "enable";
	}

	this.process = function(element, processed) {
		
		let block = reference.disable.block;
		
		for(let i = 0; i < processed.length; i++) {
			
			for(let j = 0; j < block.length; j++) {
				
				if(("" + processed[i]).toLowerCase() == ("" + block[j]).toLowerCase()) {
					
					block.splice(j, 1);
					
					j--;
				}
			}
		}
		
		return null;
	}
}

function lockDown() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.block = [];

	var reference = this;

	this.deny = function(element) {
		
		for(let i = 0; i < reference.block.length; i++) {
			
			if(element.content.toLowerCase() == ("" + reference.block[i]).toLowerCase())
				return true;
		}
		
		return false;
	}

	this.verify = function(element) {
		return element.content.toLowerCase() == "lock down";
	}

	this.process = function(element, processed) {

		reference.block = reference.block.concat(processed);

		return null;
	}
}

function reflect() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusionStone = null;
	this.state = null;
	this.argumentsCommand = null;
	this.returnCommand = null;

	var reference = this;

	this.verify = function(element) {

		try {

			reference.fusionStone =
				reference.fusionStone == null ?
					getStone(reference, "FUSION") :
					reference.fusionStone;

			reference.state =
				reference.state == null ?
					getStone(reference, "State") :
					reference.state;

			reference.argumentsCommand =
				reference.argumentsCommand == null ?
					getStone(reference, "Arguments") :
					reference.argumentsCommand;

			reference.returnCommand =
				reference.returnCommand == null ?
					getStone(reference, "Return") :
					reference.returnCommand;
		}

		catch(error) {
			
		}

		return element.content.toLowerCase() == "reflect";
	}

	this.process = function(element, processed) {
		
		let deny = processed.length > 0 ? processed[0] : null;
		let verify = processed.length > 1 ? processed[1] : null;
		let trickleDown = processed.length > 2 ? processed[2] : null;
		let processFunction = processed.length > 3 ? processed[3] : null;
		let terminate = processed.length > 4 ? processed[4] : null;
		let isAdded = processed.length > 5 ? processed[5] : null;
		let jump = processed.length > 6 ? processed[6] : null;
		let handleError = processed.length > 7 ? processed[7] : null;

		let unit = new fusion.FUSIONUnit();

		var innerReference = this;
		innerReference.reference = reference;

		unit.deny = function(element) {
				
			if(deny == null)
				return false;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(deny),
				[one.toList(element)],
				unit);
			
			return ("" + result).toLowerCase() == "true";
		}

		unit.verify = function(element) {

			if(verify == null)
				return false;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(verify),
				[one.toList(element)],
				unit);
			
			return result;
		}

		unit.trickleDown = function(element) {
				
			if(trickleDown == null)
				return true;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(trickleDown),
				[one.toList(element)],
				unit);
			
			return ("" + result).toLowerCase() == "true";
		}

		unit.process = function(element, processed) {
				
			if(processFunction == null)
				return null;
			
			return executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(processFunction),
				[one.toList(element), processed],
				unit);
		}

		unit.terminate = function(element, processed) {
				
			if(terminate == null)
				return false;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(terminate),
				[one.toList(element), processed],
				unit);
			
			return ("" + result).toLowerCase() == "true";
		}

		unit.isAdded = function(element, processed) {
				
			if(isAdded == null)
				return true;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(isAdded),
				[one.toList(element), processed],
				unit);
			
			return ("" + result).toLowerCase() == "true";
		}

		unit.jump = function(element, processed) {
				
			if(jump == null)
				return null;
			
			let result = executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(jump),
				[one.toList(element), processed],
				unit);
			
			return one.toElement(result);
		}

		unit.handleError = function(element, processed, exception) {
				
			if(handleError == null)
				return null;
			
			executeFunction(
				element,
				innerReference.reference.fusionStone,
				innerReference.reference.state,
				innerReference.reference.argumentsCommand,
				innerReference.reference.returnCommand,
				one.toElement(handleError),
				[one.toList(element), processed, exception],
				unit);
		}

		philosophersStone.connect(reference.fusionStone, unit, [], true);
		
		reference.fusionStone.update();

		return null;
	}
}

function interpreter() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusion = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.fusion == null) {

				reference.fusion =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "FUSION");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "interpreter";
	}

	this.process = function(element, processed) {
		return reference.fusion;
	}
}

function listen() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.lists = [];

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "listen";
	}

	this.process = function(element, processed) {
		
		for(let i = 0; i < processed.length; i++)
			lists.push(processed[i]);

		return reference.fusion;
	}
	
	this.standard = function(packet) {
		
		for(let i = 0; i < lists.length; i++)
			lists[i].push(packet);
		
		return null;
}
}

function be() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		if(reference.state == null) {

			reference.state =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "State");
					}
				)[0];
		}

		return element.content.toLowerCase() == "be";
	}

	this.process = function(element, processed) {

		if(Array.isArray(processed[0]))
			reference.state.deserialize(processed[0]);

		return null;
	}
}

function call() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "call";
	}

	this.process = function(element, processed) {

		let result = [];

		let atlas = philosophersStone.traverse(reference);

		for(let i = 0; i < atlas; i++) {

			let item = atlas[i].standard(processed);

			if(item != null)
				result.push(item);
		}

		return result;
	}
}

function direct() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "direct";
	}

	this.process = function(element, processed) {

		let result = [];

		for(let i = 0; i < processed.length; i++) {

			try {
				result.push(eval("" + processed[i]));
			}

			catch(error) {
				result.push(null);
			}
		}

		return result;
	}
}

function returnCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Return");

	this.returnValue = null;
	this.isReturning = false;

	var reference = this;

	this.deny = function(element) {
		return reference.isReturning;
	}

	this.verify = function(element) {
		return element.content.toLowerCase() == "return";
	}

	this.process = function(element, processed) {

		reference.returnValue =
			processed.length == 0 ?
				null :
				(processed.length == 1 ?
					processed[0] :
					processed);
		
		reference.isReturning = true;

		return reference.returnValue;
	}
}

function catchCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.caught = false;
	this.auto = false;

	this.tags.push("Catch");

	var reference = this;
	
	this.deny = function(element) {
		
		let pseudo = element;
		
		while(pseudo != null) {
			
			if(pseudo.content.toLowerCase() == "reverse catch")
				return false;
			
			pseudo = pseudo.parent;
		}
		
		return element.content.toLowerCase() != "catch" && reference.caught && !reference.auto;
	}
	
	this.verify = function(element) {
		
		return element.content.toLowerCase() == "catch" ||
			element.content.toLowerCase() == "reverse catch";
	}
	
	this.trickleDown = function(element) {

		if(reference.auto)
			return false;
		
		let wasCaught = reference.caught;
		
		if(element.content.toLowerCase() == "catch")
			reference.caught = false;
		
		return wasCaught;
	}
	
	this.handleError = function(element, processed, error) {
		reference.caught = true;
	}
}

function catchEnabled() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.catchStone = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.catchStone == null) {

				reference.catchStone =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "Catch");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "catch enabled";
	}

	this.process = function(element, processed) {
		return reference.catchStone.auto;
	}
}

function scope() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "scope";
	}

	this.process = function(element, processed) {
		return null;
	}
}

function execute() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusion = null;

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "execute";
	}

	this.process = function(element, processed) {

		if(reference.fusion == null) {

			reference.fusion =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "FUSION");
					}
				)[0];
		}

		for(let i = 0; i < processed.length; i++) {

			let data = "" + processed[i];

			for(let j = 0; j < data.length; j++) {

				if(data.charCodeAt(j) == 13) {
					data = data.substring(0, j) + data.substring(j + 1);
					j--;
				}
			}

			reference.fusion.internalProcess(onePlus.readONEPlus(data), true);
		}

		return null;
	}
}

function breakCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Break");

	this.state = null;
	this.broke = false;

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "break";
	}
	
	this.jump = function(element, processed) {

		if(reference.state == null) {

			reference.state =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "State");
					}
				)[0];
		}
		
		let condition = true;
		let nest = 0;
		
		for(let i = 0; i < processed.length; i++) {
				
			if((("" + processed[i]).toLowerCase() == "false") ||
				(("" + processed[i]).toLowerCase() == "true")) {
				
				condition = ("" + processed[i]).toLowerCase() == "true";
			}
			
			else
				nest = Number("" + processed[i]) - 1;
		}
		
		let current = element;
		
		if(current.parent.parent == null)
			return null;
		
		let popState = 0;
		
		for(let i = 0; i < nest; i++) {
			
			current = current.parent;
			
			if(current.parent.parent == null)
				return null;
			
			popState++;
		}
		
		reference.broke = condition;
		
		if(condition) {
			
			for(let i = 0; i < popState; i++)
				reference.state.pop();
			
			current = current.parent;
			
			while(current.parent != null) {
				
				reference.state.pop();
				
				let parentIndex = one.getIndex(current);

				if(parentIndex == current.parent.children.length - 1) {
					
					current = current.parent;

					continue;
				}

				return current.parent.children[parentIndex + 1];
			}

			philosophersStone.retrieve(
				philosophersStone.traverse(reference),
				function(item) {
					return philosophersStone.isTagged(item, "FUSION");
				}
			)[0].running = false;
		}

		return null;
	}
	
	this.terminate = function(element, processed) {
		
		let condition = true;
		let nest = 0;
		
		for(let i = 0; i < processed.length; i++) {
				
			if((("" + processed[i]).toLowerCase() == "false") ||
				(("" + processed[i]).toLowerCase() == "true")) {
				
				condition = ("" + processed[i]).toLowerCase() == "true";
			}
			
			else
				nest = Number("" + processed[i]) - 1;
		}
		
		let current = element;
		
		for(let i = 0; i < nest; i++) {
			
			current = current.parent;
			
			if(current.parent.parent == null)
				return true;
		}
		
		if(condition) {
			
			current = element.parent;
			
			while(current.parent != null) {
				
				let parentIndex = one.getIndex(current);
				
				if(parentIndex == current.parent.children.length - 1) {
					
					current = current.parent;
					
					continue;
				}
				
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}

function elseCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.broke = null;

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "else";
	}

	this.trickleDown = function(element) {
		
		if(reference.broke == null) {

			reference.broke =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "Break");
					}
				)[0];
		}
		
		let trickleDown = reference.broke.broke;
		reference.broke.broke = false;
		
		return trickleDown;
	}
}

function loop() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.state = null;

	var reference = this;

	this.verify = function(element) {

		if(reference.state == null) {

			reference.state =
				philosophersStone.retrieve(
					philosophersStone.traverse(reference),
					function(item) {
						return philosophersStone.isTagged(item, "State");
					}
				)[0];
		}

		return element.content.toLowerCase() == "loop";
	}
	
	this.jump = function(element, processed) {
		
		let condition = true;
		let nest = 0;
		
		for(let i = 0; i < processed.length; i++) {
				
			if((("" + processed[i]).toLowerCase() == "false") ||
				(("" + processed[i]).toLowerCase() == "true")) {
				
				condition = ("" + processed[i]).toLowerCase() == "true";
			}
			
			else
				nest = Number("" + processed[i]) - 1;
		}
		
		let current = element;
		
		let popState = 0;
		
		for(let i = 0; i < nest && current.parent.parent != null; i++) {
			
			current = current.parent;
			
			popState++;
		}
		
		if(condition) {
			
			for(let i = 0; i < popState; i++)
				reference.state.pop();
			
			reference.state.pop();
			reference.state.push();
			
			return current.parent.children[0];
		}
		
		return null;
	}
}

function wait() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "wait";
	}

	this.process = function(element, processed) {

		let go = (new Date()).getTime() + (Number("" + processed[0]) * 1000);

		while((new Date()).getTime() < go);

		return null;
	}
}

function run() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "run";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < processed.length; i++) {

			try {
				cmd.run("" + processed[i]);
			}

			catch(error) {

			}
		}
	}
}

function automaticCatch() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.catchStone = null;

	var reference = this;

	this.verify = function(element) {

		try {

			if(reference.catchStone == null) {

				reference.catchStone =
					philosophersStone.retrieve(
						philosophersStone.traverse(reference),
						function(item) {
							return philosophersStone.isTagged(item, "Catch");
						}
					)[0];
			}
		}

		catch(error) {

		}

		return element.content.toLowerCase() == "automatic catch";
	}

	this.process = function(element, processed) {

		if(reference.catchStone)
			reference.catchStone.auto = ("" + processed[0]).toLowerCase() == "true";

		return null;
	}
}

function throwCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "throw";
	}

	this.process = function(element, processed) {
		return this.field();
	}
}

function exit() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "exit";
	}

	this.process = function(element, processed) {

		process.exit();

		return null;
	}
}

function exception() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Exception");

	this.error = null;

	var reference = this;

	this.verify = function(element) {
		return element.content.toLowerCase() == "exception";
	}

	this.process = function(element, processed) {
		return reference.error != null ? reference.error.message : null;
	}
	
	this.handleError = function(element, processed, error) {
		reference.error = error;
	}
}

function retrieve() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());
	
	this.retrieved = null;
	this.retrieve = null;

	var reference = this;
	
	this.verify = function(element) {
		
		if(reference.retrieved != null) {

			if(reference.retrieved.parent == element)
				return true;
		}

		return element.content.toLowerCase() == "retrieve";
	}
	
	this.process = function(element, processed) {
		
		if(element.content.toLowerCase() == "retrieve") {
			
			if(processed.length > 0) {
				
				if(processed.length == 1)
					reference.retrieve = processed[0];
				
				else
					reference.retrieve = processed;
				
				reference.retrieved = element;
			}
		}
		
		else {
			
			let object = reference.retrieve;
			
			reference.retrieved = null;
			reference.retrieve = null;
			
			return object;
		}
		
		return null;
	}
}

function shift() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "shift";
	}

	this.jump = function(element, processed) {

		let item = one.toElement(processed[0]);

		if(processed.length > 1) {

			for(let i = 0; i < processed[1].length; i++)
				item = item.children[processed[1][i]];
		}

		return item;
	}
}

function flip() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "flip";
	}
	
	this.jump = function(element, processed) {

		if(("" + processed[0]).toLowerCase() == "true") {
			
			let jump = element;
			let indexes = [];
			
			while(jump.parent != null) {
				
				indexes.push(one.getIndex(jump));
				
				jump = jump.parent;
				
				if(processed.length > 1) {
					
					if(("" + processed[1]).toLowerCase() == "true")
						break;
				}
			}
			
			jump = one.copyElement(jump);
			
			reverseElement(jump);
			
			for(let i = 0; i < indexes.length; i++)
				jump = jump.children[jump.children.length - indexes[i] - 1];
			
			let jumpIndex = one.getIndex(jump);
			
			while(jump.parent != null) {
				
				if(jumpIndex < jump.parent.children.length - 1)
					return jump.parent.children[jumpIndex + 1];
				
				jump = jump.parent;
			}
		}
		
		return null;
	}
}

function block() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "block";
	}

	this.trickleDown = function(element) {
		return false;
	}
}

function ternary() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "ternary";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).toLowerCase() == "true" ? processed[1] : processed[2];
	}
}

function isolate() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "isolate";
	}
}

function vanish() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.toVanish = [];

	var reference = this;

	this.verify = function(element) {
		
		for(let i = 0; i < reference.toVanish.length; i++) {
			
			let index = one.getIndex(toVanish[i]);
			
			if(index >= 0)
				one.removeChild(toVanish[i].parent, index);
		}

		return element.content.toLowerCase() == "vanish";
	}

	this.process = function(element) {

		reference.toVanish.push(element);

		return null;
	}
}

function log() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	var nodeProcess = platform == "Node" ? process : null;

	this.verify = function(element) {
		return element.content.toLowerCase() == "log";
	}

	this.process = function(element, processed) {

		let str = "";

		for(let i = 0; i < processed.length; i++)
			str += renderItem(processed[i]);
		
		if(platform == "Browser")
			console.log(str);
		
		else
			nodeProcess.stdout.write(str);

		return null;
	}
}

function logLine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	var nodeProcess = platform == "Node" ? process : null;

	this.verify = function(element) {
		return element.content.toLowerCase() == "log line";
	}

	this.process = function(element, processed) {

		let str = "";

		for(let i = 0; i < processed.length; i++)
			str += renderItem(processed[i]);
		
		if(platform == "Browser")
			console.log(str);
		
		else
			nodeProcess.stdout.write(str + "\n");

		return null;
	}
}

function logError() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	var nodeProcess = platform == "Node" ? process : null;

	this.verify = function(element) {
		return element.content.toLowerCase() == "log error";
	}

	this.process = function(element, processed) {

		let str = "";

		for(let i = 0; i < processed.length; i++)
			str += renderItem(processed[i]);
	
		if(platform == "Browser")
			console.error(str);
		
		else
			nodeProcess.stderr.write(str);

		return null;
	}
}

function logLineError() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	var nodeProcess = platform == "Node" ? process : null;

	this.verify = function(element) {
		return element.content.toLowerCase() == "log line error";
	}

	this.process = function(element, processed) {

		let str = "";

		for(let i = 0; i < processed.length; i++)
			str += renderItem(processed[i]);
		
		if(platform == "Browser")
			console.error(str);
		
		else
			nodeProcess.stderr.write(str + "\n");

		return null;
	}
}

function input() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "input";
	}

	this.process = function(element, processed) {

		let str = "";

		for(var i = 0; i < processed.length; i++)
			str += renderItem(processed[i]);

		return io.getInput(str);
	}
}

function operatingSystem() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "operating system";
	}

	this.process = function(element, processed) {
		return platform == "Node" ? process.platform : window.navigator.oscpu;
	}
}

function time() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "time";
	}

	this.process = function(element, processed) {
		return (new Date()).getTime() / 1000;
	}
}

function year() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "year";
	}

	this.process = function(element, processed) {
		return (new Date()).getFullYear();
	}
}

function month() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "month";
	}

	this.process = function(element, processed) {
		return (new Date()).getMonth();
	}
}

function day() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "day";
	}

	this.process = function(element, processed) {
		return (new Date()).getDate();
	}
}

function hour() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hour";
	}

	this.process = function(element, processed) {
		return (new Date()).getHours();
	}
}

function minute() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "minute";
	}

	this.process = function(element, processed) {
		return (new Date()).getMinutes();
	}
}

function second() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "second";
	}

	this.process = function(element, processed) {
		return (new Date()).getSeconds();
	}
}

function weekday() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "weekday";
	}

	this.process = function(element, processed) {
		return ((new Date()).getDay() % 7) + 1;
	}
}

function open() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "open";
	}

	this.process = function(element, processed) {
		return io.open("" + processed[0]);
	}
}

function save() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "save";
	}

	this.process = function(element, processed) {
		return io.save("" + processed[0], "" + processed[1]);
	}
}

function deleteCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "delete";
	}

	this.process = function(element, processed) {

		try {
			fs.unlinkSync("" + processed[0]);
		}

		catch(error) {

		}

		return null;
	}
}

function directory() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "directory";
	}

	this.process = function(element, processed) {

		let contents = [[], []];

		fs.readdirSync("" + processed[0]).forEach(file => {

			if(fs.lstatSync("" + processed[0] + path.sep + file).isDirectory())
				contents[0].push(file);

			else
				contents[1].push(file);
		});

		return contents;
	}
}

function localDirectory() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "local directory";
	}

	this.process = function(element, processed) {
		return path.resolve(".") + path.sep;
	}
}

// NOTE - COMPROMISE
function rootDirectories() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "root directories";
	}

	this.process = function(element, processed) {

		let value = path.resolve(".");
		let index = value.indexOf(":") + 1;

		for(; index < value.length; index++) {

			if(value.charAt(index) != path.sep)
				break;
		}

		return [value.substring(0, index)];
	}
}

function parentDirectory() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "parent directory";
	}

	this.process = function(element, processed) {

		let root = path.resolve(".");
		let index = root.indexOf(":") + 1;

		for(; index < root.length; index++) {

			if(root.charAt(index) != path.sep)
				break;
		}

		root = root.substring(0, index);

		let directory = path.resolve("" + processed[0]);

		if(directory == root)
			return directory;
		
		if(directory.endsWith(path.sep))
			directory = directory.substring(0, directory.length - 1);

		let nest = processed.length == 1 ? 1 : Number("" + processed[1]);

		for(let i = 0; i < nest && directory + path.sep != root; i++)
			directory = directory.substring(0, directory.lastIndexOf(path.sep));

		return directory + path.sep;
	}
}

function absolutePath() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "absolute path";
	}

	this.process = function(element, processed) {
		return path.resolve("" + processed[0]);
	}
}

function createDirectory() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "create directory";
	}

	this.process = function(element, processed) {

		if(!fs.existsSync("" + processed[0]))
			fs.mkdirSync("" + processed[0]);

		return null;
	}
}

function isDirectory() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "is directory";
	}

	this.process = function(element, processed) {
		return fs.lstatSync("" + processed[0]).isDirectory();
	}
}

function fileExists() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "file exists";
	}

	this.process = function(element, processed) {
		return fs.existsSync("" + processed[0]);
	}
}

function separator() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "separator";
	}

	this.process = function(element, processed) {
		return path.sep;
	}
}

function isHidden() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "is hidden";
	}

	this.process = function(element, processed) {
		return false; // STUB - TEMPORARY
	}
}

function fileSize() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "file size";
	}

	this.process = function(element, processed) {
		return fs.statSync("" + processed[0]).size;
	}
}

function rename() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "rename";
	}

	this.process = function(element, processed) {

		fs.rename(
			"" + processed[0],
			"" + processed[1],
			function(error) {
				
			}
		);

		return null;
	}
}

function pathSeparator() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "path separator";
	}

	this.process = function(element, processed) {
		return null; // STUB - TEMPORARY
	}
}

function sourceWorkspaces() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "source workspaces";
	}

	this.process = function(element, processed) {
		return [path.resolve(".")]; // STUB - TEMPORARY
	}
}

function buildWorkspace() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "build workspace";
	}

	this.process = function(element, processed) {
		return path.resolve("."); // STUB - TEMPORARY
	}
}

function list() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "list";
	}

	this.process = function(element, processed) {
		return processed;
	}
}

function size() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "size";
	}

	this.process = function(element, processed) {
		
		return Array.isArray(processed[0]) ? processed[0].length : ("" + processed[0]).length;
	}
}

function at() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "at";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])){
			
			let item = processed[0];

			for(let i = 1; i < processed.length && Array.isArray(item); i++)
				item = item[Number("" + processed[i]) - 1];
			
			return item;
		}

		return ("" + processed[0]).charAt(Number("" + processed[1]) - 1);
	}
}

function append() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "append";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {

			let item = processed[0];
			
			for(let i = 1; i < processed.length; i++)
				item.push(processed[i]);
			
			return item;
		}
		
		let string = "" + processed[0];
		
		for(let i = 1; i < processed.length; i++)
			string += processed[i];
		
		return string;
	}
}

function set() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "set";
	}

	this.process = function(element, processed) {
		
		let index = Number("" + processed[1]) - 1;
		let object = processed[2];
		
		if(Array.isArray(processed[0])) {
			
			let item = processed[0];
			
			while(item.length < index + 1)
				item.push(null);
			
			item[index] = object;
			
			return item;
		}
		
		let string = "" + processed[0];
		
		while(string.length < index + 1)
			string += " ";
		
		return string.substring(0, index) + object + string.substring(index + 1);
	}
}

function insert() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "insert";
	}

	this.process = function(element, processed) {
		
		let index = Number("" + processed[1]) - 1;
		
		if(Array.isArray(processed[0])) {
			
			let item = processed[0];
			
			while(item.length < index)
				item.push(null);
			
			for(let i = 2; i < processed.length; i++)
				list.splice(index + (i - 2), 0, processed[i]);
			
			return list;
		}
		
		let string = "" + processed[0];
		
		while(string.length < index)
			string += " ";
		
		let insert = "";
		
		for(let i = 2; i < processed.length; i++)
			insert += processed[i];
		
		return string.substring(0, index) + insert + string.substring(index);
	}
}

function remove() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "remove";
	}

	this.process = function(element, processed) {
		
		let index = Number("" + processed[1]) - 1;
		
		if(Array.isArray(processed[0]))
			return processed[0].splice(index, 1);
		
		let string = "" + processed[0];
		
		let remove = string.charAt(index);
		string = string.substring(0, index) + string.substring(index + 1);
		
		return remove;
	}
}

function concatenate() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "concatenate";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let concatenate = [];
			
			for(let i = 0; i < processed.length; i++) {
				
				if(Array.isArray(processed[i]))
					concatenate.concat(processed[i]);
				
				else
					concatenate.concat(convertSequence(processed[i]));
			}
			
			return concatenate;
		}
		
		let concatenate = "";
			
		for(let i = 0; i < processed.length; i++) {
			
			if(Array.isArray(processed[i]))
				concatenate += convertSequence(processed[i]);
			
			else
				concatenate += "" + processed[i];
		}
		
		return concatenate;
	}
}

function crop() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "crop";
	}

	this.process = function(element, processed) {
		
		let from = Number("" + processed[1]) - 1;
		let to = Number("" + processed[2]) - 1;
		
		if(from <= to) {
			
			if(Array.isArray(processed[0]))
				return processed[0].slice(from, to);
			
			return ("" + processed[0]).substring(from, to);
		}
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0]
			let crop = [];
			
			for(let i = from; i > to; i--)
				crop.push(list[i]);
			
			return crop;
		}
		
		let string = ("" + processed[0]);
		let crop = "";
		
		for(let i = from; i > to; i--)
			crop += string.charAt(i);
		
		return crop;
	}
}

function contains() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "contains";
	}

	this.process = function(element, processed) {

		if(Array.isArray(processed[0]))
			return processed[0].includes(processed[1]);
		
		return ("" + processed[0]).includes("" + processed[1]);
	}
}

function index() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "index";
	}

	this.process = function(element, processed) {
		
		let start = 0;
		
		if(processed.length >= 3)
			start = Number("" + processed[2]);
		
		if(Array.isArray(processed[0])) {
			
			for(let i = start; i < processed[0].length; i++) {
				
				if(processed[0][i] == processed[1])
					return i + 1;
			}
			
			return 0;
		}
		
		return ("" + processed[0]).indexOf("" + processed[1], start) + 1;
	}
}

function count() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "count";
	}

	this.process = function(element, processed) {
		
		let count = 0;
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			for(let i = 0; i < list.length; i++) {
				
				if(list[i] == processed[1])
					count++;
			}
			
			return count;
		}
		
		let string = "" + processed[0];
		let item = "" + processed[1];
		
		for(let i = 0; i <= string.length - item.length && i < string.length; i++) {
			
			if(string.substring(i, i + item.length) == item)
				count++;
		}
		
		return count;
	}
}

function cut() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "cut";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let sub = [];
			let current = [];
			
			let list = processed[0];
			
			for(let i = 0; i < list.length; i++) {
				
				if(list[i] == processed[1] && current.length > 0) {
					sub.push(current);
					current = [];
				}
				
				else
					current.push(list[i]);
			}
			
			if(current.length > 0)
				sub.push(current);
			
			return sub;
		}
		
		return ("" + processed[0]).split("" + processed[1]);
	}
}

function reverse() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "reverse";
	}

	this.process = function(element, processed) {

		if(Array.isArray(processed[0])) {

			let list = processed[0].slice(0);
			list.reverse();

			return list;
		}
		
		return ("" + processed[0]).split("").reverse().join("");
	}
}

function convertSequenceCommand() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "convert sequence";
	}

	this.process = function(element, processed) {
		return convertSequence(processed[0]);
	}
}

function listToElement() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "list to element";
	}

	this.process = function(element, processed) {
		return one.writeONE(one.toElement(processed[0]));
	}
}

function elementToList() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "element to list";
	}

	this.process = function(element, processed) {

		let data = "" + processed[0];

		for(let i = 0; i < data.length; i++) {

			if(data.charCodeAt(i) == 13) {
				data = data.substring(0, i) + data.substring(i + 1);
				i--;
			}
		}

		return one.toList(onePlus.readONEPlus(data));
	}
}

function tokenize() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "tokenize";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let sub = [];
			let current = [];
			
			let list = processed[0];
			
			for(let i = 0; i < list.length; i++) {
				
				let token = false;
				
				for(let j = 1; j < processed.length; j++) {
					
					if(list[i] == processed[j]) {
						
						token = true;
						
						break;
					}
				}
				
				if(token) {
					
					if(current.length > 0) {
						
						sub.push(current);
						
						current = [];
					}
					
					sub.push([list[i]]);
				}
				
				else
					current.push(list[i]);
			}
			
			if(current.length > 0)
				sub.push(current);
			
			return sub;
		}
		
		let tokens = [];
		
		for(let i = 1; i < processed.length; i++)
			tokens.push("" + processed[i]);
		
		return tokenizer.tokenize(tokens, "" + processed[0]);
	}
}

function appendAll() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "append all";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {

			let list = processed[0];
			
			for(let i = 1; i < processed.length; i++) {
				
				if(Array.isArray(processed[i]))
					list = list.concat(processed[i]);
				
				else
					list = list.concat(ConvertSequence.stringToList("" + processed[i]));
			}
			
			return list;
		}
		
		let string = "" + processed[0];
		
		for(let i = 1; i < processed.length; i++) {
			
			if(Array.isArray(processed[i]))
				string += convertSequence(processed[i]);
			
			else
				string += processed[i];
		}
		
		return string;
	}
}

function insertAll() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "insert all";
	}

	this.process = function(element, processed) {
		
		let index = Number("" + processed[1]) - 1;
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			while(list.length < index)
				list.push(null);
			
			let insert = [];
			
			for(let i = 2; i < processed.length; i++) {
				
				if(Array.isArray(processed[i]))
					insert = insert.concat(processed[i]);
				
				else
					insert = insert.concat(convertSequence("" + processed[i]));
			}
			
			for(let i = insert.length - 1; i >= 0; i--)
				list.splice(index, 0, insert[i]);
			
			return list;
		}
		
		let string = "" + processed[0];
		
		while(string.length < index)
			string += " ";
		
		let insert = "";
		
		for(let i = 2; i < processed.length; i++) {
			
			if(Array.isArray(processed[i]))
				insert += convertSequence(processed[i]);
			
			else
				insert += processed[i];
		}
		
		return string.substring(0, index) + insert + string.substring(index);
	}
}

function indexes() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "indexes";
	}

	this.process = function(element, processed) {
		
		let indexes = [];
		
		let start = 0;
		
		if(processed.length >= 3)
			start = Number("" + processed[2]);
		
		if(Array.isArray(processed[0])) {
			
			for(let i = start; i < processed[0].length; i++) {
				
				if(processed[0][i] == processed[1])
					indexes.push(i + 1);
			}
		}
		
		else {
			
			let string = "" + processed[0];
			let token = "" + processed[1];
			
			while(true) {
				
				start = string.indexOf(token, start);
				
				if(start == -1)
					break;
				
				start++;
				indexes.push(start);
			}
		}
		
		return indexes;
	}
}

function swap() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "swap";
	}

	this.process = function(element, processed) {
		
		let a = Number("" + processed[1]) - 1;
		let b = Number("" + processed[2]) - 1;
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			let temporary = list[a];
			
			list[a] = list[b];
			list[b] = list[temporary];
			
			return list;
		}
		
		let string = "" + processed[0];
		
		let charA = string.charAt(a);
		let charB = string.charAt(b);
		
		string = string.substring(0, a) + charB + string.substring(a + 1);
		string = string.substring(0, b) + charA + string.substring(b + 1);
		
		return string;
	}
}

function sortAlphabetical() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "sort alphabetical";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			let sortLists = [];
			
			if(processed.length == 1) {
				
				for(let i = 0; i < list.length; i++) {
					
					let sortListObjects = [];
					sortListObjects.push(list[i]);
					
					let sort = new sortList();
					
					sort.list = sortListObjects;
					sort.key = 0;
					sort.numerical = false;
					
					sortLists.push(sort);
				}
				
				sortLists.sort(function(a, b) { return a.compareTo(b); });
				
				for(let i = 0; i < sortLists.length; i++)
					processed[0][i] = sortLists[i].list[0];
			}
			
			else {
				
				let givenKey = Number("" + processed[1]) - 1;
				
				for(let i = 0; i < list.length; i++) {
					
					let sortList = new sortList();
					
					sort.list = list[i];
					sort.key = givenKey;
					sort.numerical = false;
					
					sortLists.push(sort);
				}
				
				sortLists.sort(function(a, b) { return a.compareTo(b); });
				
				for(let i = 0; i < sortLists.length; i++)
					processed[0][i] = sortLists[i].list;
			}
			
			return processed[0];
		}
		
		return sortString("" + processed[0], false);
	}
}

function sortNumerical() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "sort numerical";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			let sortLists = [];
			
			if(processed.length == 1) {
				
				for(let i = 0; i < list.length; i++) {
					
					let sortListObjects = [];
					sortListObjects.push(list[i]);
					
					let sort = new sortList();
					
					sort.list = sortListObjects;
					sort.key = 0;
					sort.numerical = true;
					
					sortLists.push(sort);
				}
				
				sortLists.sort(function(a, b) { return a.compareTo(b); });
				
				for(let i = 0; i < sortLists.length; i++)
					processed[0][i] = sortLists[i].list[0];
			}
			
			else {
				
				let givenKey = Number("" + processed[1]) - 1;
				
				for(let i = 0; i < list.length; i++) {
					
					let sort = new sortList();
					
					sort.list = list[i];
					sort.key = givenKey;
					sort.numerical = true;
					
					sortLists.push(sort);
				}
				
				sortLists.sort(function(a, b) { return a.compareTo(b); });
				
				for(let i = 0; i < sortLists.length; i++)
					processed[0][i] = sortLists[i].list;
			}
			
			return processed[0];
		}
		
		return sortString("" + processed[0], true);
	}
}

function shuffle() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "shuffle";
	}

	this.process = function(element, processed) {

		if(Array.isArray(processed[0])) {

			let list = processed[0];

			for (let i = list.length - 1; i > 0; i--) {

				let j = Math.floor(Math.random() * (i + 1));
				let x = list[i];

				list[i] = list[j];
				list[j] = x;
			}

			return list;
		}

		let string = "" + processed[0];
		let value = "";

		while(string.length > 0) {

			let index = Math.floor(Math.random() * string.length);

			value += string.charAt(index);
			string = string.substring(0, index) + string.substring(index + 1);
		}

		return value;
	}
}

function isSortedAlphabetical() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "is sorted alphabetical";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			if(processed.length == 1) {
				
				for(let i = 0; i < list.length - 1; i++) {
					
					if(("" + list[i]).localeCompare("" + list[i + 1]) > 0)
						return false;
				}
			}
			
			else {
				
				for(let i = 0; i < list.length - 1; i++) {
					
					let givenKey = Number("" + processed[1]) - 1;
					
					let a = list[i];
					let b = list[i + 1];
					
					if(("" + a[givenKey]).localeCompare("" + b[givenKey]) > 0)
						return false;
				}
			}
		}
		
		else {
			
			let string = "" + processed[0];
			
			for(let i = 0; i < string.length - 1; i++) {
				
				if(string.charAt(i).localeCompare(string.charAt(i + 1)) > 0)
					return false;
			}
		}
		
		return true;
	}
}

function isSortedNumerical() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "is sorted numerical";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			if(processed.length == 1) {
				
				for(let i = 0; i < list.size() - 1; i++) {
					
					let a = Number("" + list[i]);
					let b = Number("" + list[i + 1]);
					
					if(a > b)
						return false;
				}
			}
			
			else {
				
				for(let i = 0; i < list.length - 1; i++) {
					
					let givenKey = Math.floor(Number("" + processed[1]) - 1);
					
					let a = Number("" + list[i][givenKey]);
					let b = Number("" + list[i + 1][givenKey]);
					
					if(a > b)
						return false;
				}
			}
		}
		
		else {
			
			let string = "" + processed[0];
			
			for(let i = 0; i < string.length - 1; i++) {
				
				if(Number(string.charAt(i)) > Number(string.charAt(i + 1)))
					return false;
			}
		}
		
		return true;
	}
}

function keyIndex() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "key index";
	}

	this.process = function(element, processed) {
		
		let lists = processed[0];
		let object = processed[1];
		let index = processed.length > 2 ? Number("" + processed[2]) - 1 : -1;
		
		for(let i = 0; i < lists.length; i++) {
			
			let list = lists[i];
			
			if(index != -1) {
				
				let key = list[index];
				
				if(object == key)
					return i + 1;
			}
			
			else if(list.includes(object))
				return i + 1;
		}
		
		return 0;
	}
}

function keyIndexes() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "key indexes";
	}

	this.process = function(element, processed) {
		
		let lists = processed[0];
		let object = processed[1];
		let index = processed.length > 2 ? Number("" + processed[2]) - 1 : -1;
		
		let keys = [];
		
		for(let i = 0; i < lists.length; i++) {
			
			let list = lists[i];
			
			if(index != -1) {
				
				let key = list[index];
				
				if(object == key)
					keys.push(i + 1);
			}
			
			else if(list.includes(object))
				keys.push(i + 1);
		}
		
		return keys;
	}
}

function replace() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "replace";
	}

	this.process = function(element, processed) {
		
		if(Array.isArray(processed[0])) {
			
			let list = processed[0];
			
			for(let i = 0; i < list.length; i++) {
				
				if(list[i] == processed[1])
					list[i] = processed[2];
			}
			
			return list;
		}
		
		return ("" + processed[0]).split("" + processed[1]).join("" + processed[2]);
	}
}

function rank() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "rank";
	}

	this.process = function(element, processed) {

		let count = [];
		let unique = [];
		
		let list = processed[0];
		
		for(let i = 0; i < list.length; i++) {
			
			let found = false;
			
			for(let j = 0; j < unique.length; j++) {
				
				if(list[i] == unique[j]) {
					
					count[j] = count[j] + 1;
					found = true;
					
					break;
				}
			}
			
			if(!found) {
				unique.push(list[i]);
				count.push(1);
			}
		}
		
		let lists = [];
		
		for(let i = 0; i < unique.length; i++) {
			
			let countNumber = count[i];
			
			while(lists.length < countNumber)
				lists.push([]);
			
			lists[countNumber - 1].push(unique[i]);
		}
		
		for(let i = 0; i < lists.length; i++) {
			
			if(lists[i].length == 0) {
				
				lists.splice(i, 1);
				
				i--;
			}
		}
		
		return lists;
	}
}

// NOTE - LIST ALIASES CAN BE IMPLEMENTED AS ALIAS LIST FIELD IN LIST OBJECT

function setAlias() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "set alias";
	}

	this.process = function(element, processed) {

		if(processed[0].standardAliases == null)
			processed[0].standardAliases = [];
		
		while(processed[0].standardAliases.length < Number("" + processed[1]))
			processed[0].standardAliases.push(null);
		
		processed[0].standardAliases[Number("" + processed[1]) - 1] = "" + processed[2];

		return null;
	}
}

function getByAlias() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "get by alias";
	}

	this.process = function(element, processed) {

		let value = [];

		if(processed[0].standardAliases == null)
			return value;

		for(let i = 0; i < processed[0].standardAliases.length && i < processed[0].length; i++) {

			if(processed[0].standardAliases[i].toLowerCase() == ("" + processed[1]).toLowerCase())
				value.push(processed[0][i]);
		}

		return value;
	}
}

function getAliasIndices() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "get alias indices";
	}

	this.process = function(element, processed) {

		let value = [];

		if(processed[0].standardAliases == null)
			return value;

		for(let i = 0; i < processed[0].standardAliases.length && i < processed[0].length; i++) {

			if(processed[0].standardAliases[i].toLowerCase() == ("" + processed[1]).toLowerCase())
				value.push(i + 1);
		}

		return value;
	}
}

function getAlias() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "get alias";
	}

	this.process = function(element, processed) {

		if(processed[0].standardAliases == null)
			return null;

		let index = Number("" + processed[1]) - 1;

		if(index >= processed[0].length || index >= processed[0].standardAliases.length)
			return null;
		
		return processed[0].standardAliases[index];
	}
}

function characterToNumber() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "character to number";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).charCodeAt(0);
	}
}

function numberToCharacter() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "number to character";
	}

	this.process = function(element, processed) {
		return String.fromCharCode(Number("" + processed[0]));
	}
}

function upper() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "upper";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).toUpperCase();
	}
}

function lower() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "lower";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).toLowerCase();
	}
}

function trim() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "trim";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).trim();
	}
}

function patternMatch() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "pattern match";
	}

	this.process = function(element, processed) {
		return ("" + processed[0]).match(new RegExp("" + processed[1])) != null;
	}
}

function not() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "not";
	}

	this.process = function(element, processed) {
		return !(("" + processed[0]).toLowerCase() == "true");
	}
}

function is() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "is";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(processed[i - 1] !== processed[i])
				return false;
		}

		return true;
	}
}

function equal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "equal";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(("" + processed[i - 1]) != ("" + processed[i]))
				return false;
		}

		return true;
	}
}

function and() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "and";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < processed.length; i++) {

			if(!(("" + processed[i]).toLowerCase() == "true"))
				return false;
		}

		return true;
	}
}

function or() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "or";
	}

	this.process = function(element, processed) {

		for(let i = 0; i < processed.length; i++) {

			if(("" + processed[i]).toLowerCase() == "true")
				return true;
		}

		return false;
	}
}

function exclusiveOr() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "exclusive or";
	}

	this.process = function(element, processed) {

		let valid = false;

		for(let i = 0; i < processed.length; i++) {

			if(("" + processed[i]).toLowerCase() == "true") {

				if(!valid)
					valid = true;

				else
					return false;
			}
		}

		return valid;
	}
}

function greater() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "greater";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(Number("" + processed[i - 1]) <= Number("" + processed[i]))
				return false;
		}

		return true;
	}
}

function greaterOrEqual() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "greater or equal";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(Number("" + processed[i - 1]) < Number("" + processed[i]))
				return false;
		}

		return true;
	}
}

function less() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "less";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(Number("" + processed[i - 1]) >= Number("" + processed[i]))
				return false;
		}

		return true;
	}
}

function lessOrEqual() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "less or equal";
	}

	this.process = function(element, processed) {

		for(let i = 1; i < processed.length; i++) {

			if(Number("" + processed[i - 1]) > Number("" + processed[i]))
				return false;
		}

		return true;
	}
}

function add() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "add";
	}

	this.process = function(element, processed) {

		let result = 0;

		for(let i = 0; i < processed.length; i++)
			result += Number("" + processed[i]);

		return result;
	}
}

function subtract() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "subtract";
	}

	this.process = function(element, processed) {

		if(processed.length == 0)
			return 0;

		let result = processed[0];

		for(let i = 1; i < processed.length; i++)
			result -= Number("" + processed[i]);

		return result;
	}
}

function multiply() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "multiply";
	}

	this.process = function(element, processed) {

		if(processed.length == 0)
			return 0;

		let result = processed[0];

		for(let i = 1; i < processed.length; i++)
			result *= Number("" + processed[i]);

		return result;
	}
}

function divide() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "divide";
	}

	this.process = function(element, processed) {

		if(processed.length == 0)
			return 0;

		let result = processed[0];

		for(let i = 1; i < processed.length; i++)
			result /= Number("" + processed[i]);

		return result;
	}
}

function modulus() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "modulus";
	}

	this.process = function(element, processed) {

		if(processed.length == 0)
			return 0;

		let result = processed[0];

		for(let i = 1; i < processed.length; i++)
			result %= Number("" + processed[i]);

		return result;
	}
}

function random() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "random";
	}

	this.process = function(element, processed) {
		return Math.random();
	}
}

function negative() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "negative";
	}

	this.process = function(element, processed) {
		return Number("" + processed[0]) * -1;
	}
}

function power() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "power";
	}

	this.process = function(element, processed) {
		return Math.pow(Number("" + processed[0]), Number("" + processed[1]));
	}
}

function sine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "sine";
	}

	this.process = function(element, processed) {
		return Math.sin(Number("" + processed[0]));
	}
}

function cosine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "cosine";
	}

	this.process = function(element, processed) {
		return Math.cos(Number("" + processed[0]));
	}
}

function tangent() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "tangent";
	}

	this.process = function(element, processed) {
		return Math.tan(Number("" + processed[0]));
	}
}

function squareRoot() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "square root";
	}

	this.process = function(element, processed) {
		return Math.sqrt(Number("" + processed[0]));
	}
}

function naturalLogarithm() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "natural logarithm";
	}

	this.process = function(element, processed) {
		return Math.log(Number("" + processed[0]));
	}
}

function naturalLogarithm() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "natural logarithm";
	}

	this.process = function(element, processed) {
		return Math.log(Number("" + processed[0]));
	}
}

function floor() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "floor";
	}

	this.process = function(element, processed) {
		return Math.floor(Number("" + processed[0]));
	}
}

function ceiling() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "ceiling";
	}

	this.process = function(element, processed) {
		return Math.ceil(Number("" + processed[0]));
	}
}

function toRadians() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "to radians";
	}

	this.process = function(element, processed) {
		return Number("" + processed[0]) * (Math.PI / 180);
	}
}

function toDegrees() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "to degrees";
	}

	this.process = function(element, processed) {
		return Number("" + processed[0]) * (180 / Math.PI);
	}
}

function absoluteValue() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "absolute value";
	}

	this.process = function(element, processed) {
		return Math.abs(Number("" + processed[0]));
	}
}

function infinity() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "infinity";
	}

	this.process = function(element, processed) {
		return Infinity;
	}
}

function arcSine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "arc sine";
	}

	this.process = function(element, processed) {
		return Math.asin(Number("" + processed[0]));
	}
}

function arcCosine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "arc cosine";
	}

	this.process = function(element, processed) {
		return Math.acos(Number("" + processed[0]));
	}
}

function arcTangent() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "arc tangent";
	}

	this.process = function(element, processed) {
		return Math.atan(Number("" + processed[0]));
	}
}

function hyperbolicSine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hyperbolic sine";
	}

	this.process = function(element, processed) {
		return Math.sinh(Number("" + processed[0]));
	}
}

function hyperbolicCosine() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hyperbolic cosine";
	}

	this.process = function(element, processed) {
		return Math.cosh(Number("" + processed[0]));
	}
}

function hyperbolicTangent() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hyperbolic tangent";
	}

	this.process = function(element, processed) {
		return Math.tanh(Number("" + processed[0]));
	}
}

function theta() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "theta";
	}

	this.process = function(element, processed) {
		return Math.atan2(Number("" + processed[1]), Number("" + processed[0]));
	}
}

function maximum() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "maximum";
	}

	this.process = function(element, processed) {

		if(processed[0].length == 0)
			return null;

		let value = Number("" + processed[0][0]);

		for(let i = 1; i < processed[0].length; i++) {

			if(value < Number("" + processed[0][i]))
				value = Number("" + processed[0][i]);
		}

		return value;
	}
}

function minimum() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "minimum";
	}

	this.process = function(element, processed) {

		if(processed[0].length == 0)
			return null;

		let value = Number("" + processed[0][0]);

		for(let i = 1; i < processed[0].length; i++) {

			if(value > Number("" + processed[0][i]))
				value = Number("" + processed[0][i]);
		}

		return value;
	}
}

function mean() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "mean";
	}

	this.process = function(element, processed) {

		let value = 0;

		for(let i = 0; i < processed[0].length; i++)
			value += Number("" + processed[0][i]);

		return value / processed[0].length;
	}
}

function median() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "median";
	}

	this.process = function(element, processed) {

		if(processed[0].length == 0)
			return null;

		let value = processed[0].slice(0);
		value.sort(function(a, b) { return Number("" + a) - Number("" + b); });

		return value[Math.floor(value.length / 2)];
	}
}

function range() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "range";
	}

	this.process = function(element, processed) {

		if(processed[0].length == 0)
			return null;

		let max = Number("" + processed[0][0]);

		for(let i = 1; i < processed[0].length; i++) {

			if(max < Number("" + processed[0][i]))
				max = Number("" + processed[0][i]);
		}

		let min = Number("" + processed[0][0]);

		for(let i = 1; i < processed[0].length; i++) {

			if(min > Number("" + processed[0][i]))
				min = Number("" + processed[0][i]);
		}

		return max - min;
	}
}

function summation() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "summation";
	}

	this.process = function(element, processed) {

		let value = 0;

		for(let i = 0; i < processed[0].length; i++)
			value += Number("" + processed[0][i]);

		return value;
	}
}

function decimalToBinary() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "decimal to binary";
	}

	this.process = function(element, processed) {
		return Number("" + processed[0]).toString(2);
	}
}

function decimalToHexadecimal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "decimal to hexadecimal";
	}

	this.process = function(element, processed) {
		return Number("" + processed[0]).toString(16).toUpperCase();
	}
}

function binaryToDecimal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "binary to decimal";
	}

	this.process = function(element, processed) {
		return parseInt("" + processed[0], 2);
	}
}

function hexadecimalToDecimal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hexadecimal to decimal";
	}

	this.process = function(element, processed) {
		return parseInt("" + processed[0], 16);
	}
}

function binaryToHexadecimal() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "binary to hexadecimal";
	}

	this.process = function(element, processed) {
		return parseInt("" + processed[0], 2).toString(16).toUpperCase();
	}
}

function hexadecimalToBinary() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.verify = function(element) {
		return element.content.toLowerCase() == "hexadecimal to binary";
	}

	this.process = function(element, processed) {
		return parseInt("" + processed[0], 16).toString(2);
	}
}

// COMMAND UTILITY FUNCTIONS

function getStone(reference, tags) {

	return philosophersStone.retrieve(
		philosophersStone.traverse(reference),
		function(item) {
			return philosophersStone.isTagged(item, tags);
		}
	)[0];
}

function executeFunction(element, fusion, state, argumentsCommand, returnCommand, code, argumentsList, unit) {

	let currentArguments = argumentsCommand.args;
	let currentState = state.data;

	argumentsCommand.args = argumentsList;
	state.data = [currentState[0], [], []];

	let verify = null;

	if(unit != null) {

		verify = unit.verify;

		unit.verify = function(element) {
			return false;
		}
	}

	fusion.internalProcess(code, true);

	let value = returnCommand.returnValue;

	element.savedState = state.data.slice(0);
	element.savedState[0] = [];

	argumentsCommand.args = currentArguments;
	state.data = currentState;
	returnCommand.returnValue = null;
	returnCommand.isReturning = false;

	if(unit != null)
		unit.verify = verify;

	return value;
}
	
function reverseElement(element) {
		
	for(let i = 0; i < element.children.length; i++)
		reverseElement(element.children[i]);
	
	element.children.reverse();
}

function renderItem(item) {

	if(!Array.isArray(item))
		return "" + item;

	let string = "[";

	for(let i = 0; i < item.length; i++) {

		string +=
			(Array.isArray(item[i]) ?
				renderItem(item[i]) :
				"" + item[i]) +
			(i < item.length - 1 ?
				", " :
				"");
	}

	return string + "]";
}

function convertSequence(item) {

	if(Array.isArray(item)) {

		let value = "";

		for(let i = 0; i < item.length; i++)
			value += item[i];

		return value;
	}

	else {

		let item = "" + item;
		let value = [];

		for(let i = 0; i < item.length; i++)
			value.push(item.charAt(i));

		return value;
	}
}

function sortList() {
	
	this.numerical = true;
	
	this.list = [];
	this.key = 0;

	var reference = this;
	
	this.compareTo = function(item) {

		if(reference.key >= reference.list.length || item.key >= item.list.length)
			return 0;
		
		if(reference.numerical) {
			
			let a = Number("" + reference.list[reference.key]);
			let b = Number("" + item.list[item.key]);
			
			return a - b;
		}
		
		else
			return ("" + reference.list[reference.key]).localeCompare("" + item.list[item.key]);
	}
}
	
function sortString(string, numerical) {
		
	if(numerical) {
		
		let list = [];
		
		for(let i = 0; i < string.length; i++)
			list.push(Number("" + string.charAt(i)));
		
		string = "";
		
		list.sort(function(a, b) { return a - b; });
		
		for(let i = 0; i < list.length; i++)
			string += list[i];
		
		return string;
	}
	
	else {
		
		let list = [];
		
		for(let i = 0; i < string.length; i++)
			list.push("" + string.charAt(i));
		
		string = "";
		
		list.sort(function(a, b) { return a.localeCompare(b); });
		
		for(let i = 0; i < list.length; i++)
			string += list[i];
		
		return string;
	}
}

// DIRECTIVES

function alternate() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
	
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "alternate") {

			let index = one.getIndex(element);
			
			let syntax = directive.header[0].content;
			
			for(let i = 0; i < directive.body.length; i++) {
				
				try {
					
					let document = require(syntax)(directive.body[i].content);
					
					for(let j = 0; j < document.children.length; j++) {
						
						one.addChild(
								element.parent,
								document.children[i],
								index + i + 1);
					}
				}
				
				catch(error) {
					
				}
			}
		}
	}
}

function callDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
			
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "call") {
			
			let call =
				getDefinition(
					directiveUnits,
					element.children[0].content);
			
			if(call != null) {
				
				applyArguments(directives, call, element.children[0].children);
				
				let start = 0;
				let end = call.children.length - 1;
				
				if(element.children.length == 2) {
					start = Number(element.children[1].content) - 1;
					end = start;
				}
				
				if(element.children.length == 3) {
					start = Number(element.children[1].content) - 1;
					end = Number(element.children[2].content) - 1;
				}
				
				for(let i = start; i <= end; i++) {
					
					one.addChild(
						element.parent,
						call.children[i],
						one.getIndex(element) + (i - start) + 1);
				}
			}
		}
	}
}

function defineDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
	
	this.isDefine = true;
	this.definitions = [];

	var reference = this;
			
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "define") {

			let define = one.copyElement(element.children[0]);
			
			for(let i = 1; i < element.children.length; i++)
				one.addChild(define, one.copyElement(element.children[i]));
			
			reference.definitions.push(define);
		}
	}
}

function ifDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
			
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "if") {
			
			for(let i = 0; i < directive.header.length - 1; i++) {
				
				if(!one.equals(directive.header[i], directive.header[i + 1]))
					return;
			}
			
			let index = one.getIndex(element);
			
			for(let i = 0; i < directive.body.length; i++) {
				
				one.addChild(
					element.parent,
					directive.body[i],
					index + i + 1);
			}
		}
	}
}

function forDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
			
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "for") {
			
			let body = directive.body;
			
			if(directive.header.length == 0)
				return;
			
			let start = 0;
			let end = 0;
			
			if(directive.header.length == 1) {
				start = 0;
				end = Number(directive.header[0].content) - 1;
			}
			
			if(directive.header.length == 2) {
				start = Number(directive.header[0].content) - 1;
				end = Number(directive.header[1].content) - 1;
			}
			
			let index = one.getIndex(element);
			
			for(let i = start; i <= end; i++) {
				
				let apply = applyIndex(directives, body, i);
				
				for(let j = 0; j < apply.length; j++) {
					
					one.addChild(
						element.parent,
						apply[i],
						index + (body.length * (i - start)) + j + 1);
				}
			}
		}
	}
}

function importDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
			
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "import") {
			
			let define = new defineDirective();
			
			for(let i = 0; i < directiveUnits.length; i++) {
				
				if(typeof directiveUnits[i].isDefine != undefined) {
					
					define = directiveUnits[i];
					
					break;
				}
			}
			
			importDirectives(define, element);
		}
	}
}

function sizeDirective() {

	philosophersStone.abide(this, new onePlus.DirectiveUnit());
	
	this.apply = function(directiveUnits, directives, directive) {
		
		let element = directive.directive;
		
		if(element.content.toLowerCase() == "size") {
			
			let call = new one.Element();
			
			call.content =
				"" +
				getDefinition(
					directiveUnits,
					element.children[0].content).children.length;
			
			one.addChild(
					element.parent,
					call,
					one.getIndex(element) + 1);
		}
	}
}

// DIRECTIVE UTILITY FUNCTIONS
	
function isDirective(directives, element) {
		
	for(let i = 0; i < directives.length; i++) {
		
		if(directives[i].directive == element)
			return true;
	}
	
	return false;
}

function getDefinition(directiveUnits, definition) {
	
	let define = new defineDirective();
	
	for(let i = 0; i < directiveUnits.length; i++) {
		
		if(typeof directiveUnits[i].isDefine != undefined) {
			
			define = directiveUnits[i];
			
			break;
		}
	}
	
	for(let i = 0; i < define.definitions.length; i++) {
		
		if(definition.equalsIgnoreCase(define.definitions[i].content)) {
			
			let element = one.copyElement(define.definitions[i]);
			element.content = "";
			
			return element;
		}
	}
	
	return null;
}

function applyArguments(directives, element, args) {

	for(let i = 0; i < element.children.length; i++) {
		
		if(isDirective(directives, element.children[i])) {
			
			if(element.children[i].content.toLowerCase() == "arguments") {
				
				one.addChild(
					element,
					one.copyElement(
						args[
							Number(
								element.
								children.
								splice(i, 1)[0].
								children[0].
								content) - 1]));
			}
			
			continue;
		}
		
		applyArguments(directives, element.children[i], args);
	}
}
	
function applyIndex(directives, elements, index) {
	
	let apply = [];
	
	for(let i = 0; i < elements.length; i++) {
		
		let copy = new one.Element();
		copy.content = elements[i].content;
		
		if(isDirective(directives, elements[i])) {
			
			if(elements[i].content.toLowerCase() == "index")
				copy.content = "" + index;
			
			else {
				
				let directive = new onePlus.Directive();
				directive.directive = copy;
				
				directives.push(directive);
			}
		}
		
		one.addChild(
			copy,
			applyIndex(directives, elements[i].children, index));
		
		apply.push(copy);
	}
	
	return apply;
}
	
function importDirectives(define, element) {
	
	for(let i = 0; i < element.children.length; i++) {
		
		let directiveElements = getDirectives(io.open(element.children[i].content));
		
		for(let j = 0; j < directiveElements.length; j++) {
			
			if(directiveElements[j].directive.content.toLowerCase() == "define") {

				let defineElement = directiveElements[i].directive;
				let definition = one.copyElement(directiveElements[j].directive.children[0]);
				
				for(let k = 1; k < defineElement.children.length; k++)
					one.addChild(definition, one.copyElement(defineElement.children[k]));
				
				define.definitions.push(definition);
			}
			
			if(directiveElements[j].directive.content.toLowerCase() == "import")
				importDirectives(define, directiveElements[j].directive);
		}
	}
}

function getDirectives(string) {
	
	let tokens = onePlus.getTokens(string);
	let tokenize = tokenizer.tokenize(tokens, string);
	
	let nestToken = onePlus.getIndentToken(string);
	
	let directives = [];
	
	let element = new one.Element();
	
	let currentElement = element;
	
	let previousNest = 0;
	let previousElement = element;
	
	let baseElements = [];
	
	let inLiteralBlock = false;
	let literalNest = 0;
	let literalString = "";
	
	for(let i = 0; i < tokenize.length;) {
		
		let line = onePlus.getLine(tokenize, i);
		let nest = onePlus.getNest(line, nestToken);
		
		if(!inLiteralBlock) {
			
			if(nest > previousNest) {
				
				baseElements.push(currentElement);
				
				currentElement = previousElement;
			}
			
			else if(nest < previousNest) {
				
				for(let j = nest; j < previousNest && baseElements.length > 0; j++)
					currentElement = baseElements.splice(baseElements.length - 1, 1)[0];
			}
			
			previousNest = nest;
		}
		
		let literal =
				onePlus.isLiteralBlock(line, nest) &&
				!(inLiteralBlock && nest != literalNest);
		
		if(literal) {
			inLiteralBlock = !inLiteralBlock;
			literalNest = nest;
		}
		
		if(inLiteralBlock) {
			
			if(!literal) {
				
				for(let j = literalNest + 1; j < line.length; j++)
					literalString += line[j];
				
				literalString += '\n';
			}
		}
		
		else if(literal) {
			
			previousElement = onePlus.cropElement(onePlus.getElement(literalString), true);
			one.addChild(currentElement, previousElement);
			
			literalString = "";
		}
		
		else if(line.length > 0)
			previousElement = onePlus.processLine(tokens, line, currentElement, directives);
		
		i += line.length + 1;
	}
	
	return onePlus.generateDirectives(directives);
}

// EXPORT FUNCTION

module.exports = function(fusion) {

	if(fusion != null) {

		let use = getStone(fusion, "Use");

		philosophersStone.disconnect(fusion, use, true);

		philosophersStone.connect(fusion, new useCommand(), [], true);

		philosophersStone.connect(fusion, new state(), [], true);
		philosophersStone.connect(fusion, new priority(), [], true);

		philosophersStone.connect(fusion, new literal(), [], true);
		philosophersStone.connect(fusion, new variable(), [], true);

		philosophersStone.connect(fusion, new define(), [], true);
		philosophersStone.connect(fusion, new global(), [], true);
		philosophersStone.connect(fusion, new doCommand(), [], true);
		philosophersStone.connect(fusion, new argumentsCommand(), [], true);
		philosophersStone.connect(fusion, new thisCommand(), [], true);
		philosophersStone.connect(fusion, new newCommand(), [], true);
		philosophersStone.connect(fusion, new nullCommand(), [], true);
		philosophersStone.connect(fusion, new literalCommand(), [], true);
		philosophersStone.connect(fusion, new type(), [], true);
		philosophersStone.connect(fusion, new form(), [], true);
		philosophersStone.connect(fusion, new isCommand(), [], true);
		philosophersStone.connect(fusion, new isVariable(), [], true);
		philosophersStone.connect(fusion, new destroy(), [], true);
		philosophersStone.connect(fusion, new variables(), [], true);
		philosophersStone.connect(fusion, new getCode(), [], true);
		philosophersStone.connect(fusion, new getCodeIndex(), [], true);
		philosophersStone.connect(fusion, new disable(), [], true);
		philosophersStone.connect(fusion, new enable(), [], true);
		philosophersStone.connect(fusion, new lockDown(), [], true);
		philosophersStone.connect(fusion, new reflect(), [], true);
		philosophersStone.connect(fusion, new interpreter(), [], true);
		philosophersStone.connect(fusion, new listen(), [], true);
		philosophersStone.connect(fusion, new be(), [], true);
		philosophersStone.connect(fusion, new call(), [], true);
		philosophersStone.connect(fusion, new direct(), [], true);
		
		philosophersStone.connect(fusion, new returnCommand(), [], true);
		philosophersStone.connect(fusion, new catchCommand(), [], true);
		philosophersStone.connect(fusion, new catchEnabled(), [], true);
		philosophersStone.connect(fusion, new scope(), [], true);
		philosophersStone.connect(fusion, new execute(), [], true);
		philosophersStone.connect(fusion, new breakCommand(), [], true);
		philosophersStone.connect(fusion, new elseCommand(), [], true);
		philosophersStone.connect(fusion, new loop(), [], true);
		philosophersStone.connect(fusion, new wait(), [], true);
		philosophersStone.connect(fusion, new run(), [], true);
		philosophersStone.connect(fusion, new automaticCatch(), [], true);
		philosophersStone.connect(fusion, new throwCommand(), [], true);
		philosophersStone.connect(fusion, new exit(), [], true);
		philosophersStone.connect(fusion, new exception(), [], true);
		philosophersStone.connect(fusion, new retrieve(), [], true);
		philosophersStone.connect(fusion, new shift(), [], true);
		philosophersStone.connect(fusion, new flip(), [], true);
		philosophersStone.connect(fusion, new block(), [], true);
		philosophersStone.connect(fusion, new ternary(), [], true);
		philosophersStone.connect(fusion, new isolate(), [], true);
		philosophersStone.connect(fusion, new vanish(), [], true);

		philosophersStone.connect(fusion, new log(), [], true);
		philosophersStone.connect(fusion, new logLine(), [], true);
		philosophersStone.connect(fusion, new logError(), [], true);
		philosophersStone.connect(fusion, new logLineError(), [], true);
		philosophersStone.connect(fusion, new input(), [], true);
		
		philosophersStone.connect(fusion, new operatingSystem(), [], true);
		philosophersStone.connect(fusion, new time(), [], true);
		philosophersStone.connect(fusion, new year(), [], true);
		philosophersStone.connect(fusion, new month(), [], true);
		philosophersStone.connect(fusion, new day(), [], true);
		philosophersStone.connect(fusion, new hour(), [], true);
		philosophersStone.connect(fusion, new minute(), [], true);
		philosophersStone.connect(fusion, new second(), [], true);
		philosophersStone.connect(fusion, new weekday(), [], true);
		
		philosophersStone.connect(fusion, new open(), [], true);
		philosophersStone.connect(fusion, new save(), [], true);
		philosophersStone.connect(fusion, new deleteCommand(), [], true);
		philosophersStone.connect(fusion, new directory(), [], true);
		philosophersStone.connect(fusion, new localDirectory(), [], true);
		philosophersStone.connect(fusion, new rootDirectories(), [], true);
		philosophersStone.connect(fusion, new parentDirectory(), [], true);
		philosophersStone.connect(fusion, new absolutePath(), [], true);
		philosophersStone.connect(fusion, new createDirectory(), [], true);
		philosophersStone.connect(fusion, new isDirectory(), [], true);
		philosophersStone.connect(fusion, new fileExists(), [], true);
		philosophersStone.connect(fusion, new separator(), [], true);
		philosophersStone.connect(fusion, new isHidden(), [], true);
		philosophersStone.connect(fusion, new fileSize(), [], true);
		philosophersStone.connect(fusion, new rename(), [], true);
		philosophersStone.connect(fusion, new pathSeparator(), [], true);
		philosophersStone.connect(fusion, new sourceWorkspaces(), [], true);
		philosophersStone.connect(fusion, new buildWorkspace(), [], true);

		philosophersStone.connect(fusion, new list(), [], true);
		philosophersStone.connect(fusion, new size(), [], true);
		philosophersStone.connect(fusion, new at(), [], true);
		philosophersStone.connect(fusion, new append(), [], true);
		philosophersStone.connect(fusion, new set(), [], true);
		philosophersStone.connect(fusion, new insert(), [], true);
		philosophersStone.connect(fusion, new remove(), [], true);
		philosophersStone.connect(fusion, new concatenate(), [], true);
		philosophersStone.connect(fusion, new crop(), [], true);
		philosophersStone.connect(fusion, new contains(), [], true);
		philosophersStone.connect(fusion, new index(), [], true);
		philosophersStone.connect(fusion, new count(), [], true);
		philosophersStone.connect(fusion, new cut(), [], true);
		philosophersStone.connect(fusion, new reverse(), [], true);
		philosophersStone.connect(fusion, new convertSequenceCommand(), [], true);
		philosophersStone.connect(fusion, new listToElement(), [], true);
		philosophersStone.connect(fusion, new elementToList(), [], true);
		philosophersStone.connect(fusion, new tokenize(), [], true);
		philosophersStone.connect(fusion, new appendAll(), [], true);
		philosophersStone.connect(fusion, new insertAll(), [], true);
		philosophersStone.connect(fusion, new indexes(), [], true);
		philosophersStone.connect(fusion, new swap(), [], true);
		philosophersStone.connect(fusion, new sortAlphabetical(), [], true);
		philosophersStone.connect(fusion, new sortNumerical(), [], true);
		philosophersStone.connect(fusion, new isSortedAlphabetical(), [], true);
		philosophersStone.connect(fusion, new isSortedNumerical(), [], true);
		philosophersStone.connect(fusion, new keyIndex(), [], true);
		philosophersStone.connect(fusion, new keyIndexes(), [], true);
		philosophersStone.connect(fusion, new replace(), [], true);
		philosophersStone.connect(fusion, new rank(), [], true);
		philosophersStone.connect(fusion, new shuffle(), [], true);
		philosophersStone.connect(fusion, new setAlias(), [], true);
		philosophersStone.connect(fusion, new getByAlias(), [], true);
		philosophersStone.connect(fusion, new getAliasIndices(), [], true);
		philosophersStone.connect(fusion, new getAlias(), [], true);
		
		philosophersStone.connect(fusion, new characterToNumber(), [], true);
		philosophersStone.connect(fusion, new numberToCharacter(), [], true);
		philosophersStone.connect(fusion, new upper(), [], true);
		philosophersStone.connect(fusion, new lower(), [], true);
		philosophersStone.connect(fusion, new trim(), [], true);
		philosophersStone.connect(fusion, new patternMatch(), [], true);
		
		philosophersStone.connect(fusion, new not(), [], true);
		philosophersStone.connect(fusion, new is(), [], true);
		philosophersStone.connect(fusion, new equal(), [], true);
		philosophersStone.connect(fusion, new and(), [], true);
		philosophersStone.connect(fusion, new or(), [], true);
		philosophersStone.connect(fusion, new exclusiveOr(), [], true);
		philosophersStone.connect(fusion, new greater(), [], true);
		philosophersStone.connect(fusion, new greaterOrEqual(), [], true);
		philosophersStone.connect(fusion, new less(), [], true);
		philosophersStone.connect(fusion, new lessOrEqual(), [], true);
		
		philosophersStone.connect(fusion, new add(), [], true);
		philosophersStone.connect(fusion, new subtract(), [], true);
		philosophersStone.connect(fusion, new multiply(), [], true);
		philosophersStone.connect(fusion, new divide(), [], true);
		philosophersStone.connect(fusion, new modulus(), [], true);
		philosophersStone.connect(fusion, new random(), [], true);
		philosophersStone.connect(fusion, new negative(), [], true);
		philosophersStone.connect(fusion, new power(), [], true);
		philosophersStone.connect(fusion, new sine(), [], true);
		philosophersStone.connect(fusion, new cosine(), [], true);
		philosophersStone.connect(fusion, new tangent(), [], true);
		philosophersStone.connect(fusion, new squareRoot(), [], true);
		philosophersStone.connect(fusion, new naturalLogarithm(), [], true);
		philosophersStone.connect(fusion, new floor(), [], true);
		philosophersStone.connect(fusion, new ceiling(), [], true);
		philosophersStone.connect(fusion, new toRadians(), [], true);
		philosophersStone.connect(fusion, new toDegrees(), [], true);
		philosophersStone.connect(fusion, new absoluteValue(), [], true);
		philosophersStone.connect(fusion, new infinity(), [], true);
		philosophersStone.connect(fusion, new arcSine(), [], true);
		philosophersStone.connect(fusion, new arcCosine(), [], true);
		philosophersStone.connect(fusion, new arcTangent(), [], true);
		philosophersStone.connect(fusion, new hyperbolicSine(), [], true);
		philosophersStone.connect(fusion, new hyperbolicCosine(), [], true);
		philosophersStone.connect(fusion, new hyperbolicTangent(), [], true);
		philosophersStone.connect(fusion, new theta(), [], true);
		philosophersStone.connect(fusion, new maximum(), [], true);
		philosophersStone.connect(fusion, new minimum(), [], true);
		philosophersStone.connect(fusion, new mean(), [], true);
		philosophersStone.connect(fusion, new median(), [], true);
		philosophersStone.connect(fusion, new range(), [], true);
		philosophersStone.connect(fusion, new summation(), [], true);
		philosophersStone.connect(fusion, new decimalToBinary(), [], true);
		philosophersStone.connect(fusion, new decimalToHexadecimal(), [], true);
		philosophersStone.connect(fusion, new binaryToDecimal(), [], true);
		philosophersStone.connect(fusion, new hexadecimalToDecimal(), [], true);
		philosophersStone.connect(fusion, new binaryToHexadecimal(), [], true);
		philosophersStone.connect(fusion, new hexadecimalToBinary(), [], true);
	}

	else {

		let directives = [
			new alternate(),
			new callDirective(),
			new defineDirective(),
			new forDirective(),
			new ifDirective(),
			new importDirective(),
			new sizeDirective()
		];
		
		return directives;
	}
};