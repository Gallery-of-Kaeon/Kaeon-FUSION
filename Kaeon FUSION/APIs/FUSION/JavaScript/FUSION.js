var one = require("./ONE.js");
var philosophersStone = require("./PhilosophersStone.js");

class FUSION extends philosophersStone.PhilosophersStone {
	
	constructor() {

		super();

		this.running = false;

		this.fusionUnits = [];
		this.updated = false;

		this.tags.push("FUSION");
	}

	onCall(packet) {
		
		if(("" + packet[0]).toLowerCase() == "update")
			this.update();
		
		else if(("" + packet[0]).toLowerCase() == "stop")
			this.running = false;

		return null;
	}

	update() {
		
		var units = philosophersStone.get(philosophersStone.getAtlas(this), ["FUSION Unit"]);

		for(var i = 0; i < units.length; i++) {
			
			for(var j = i + 1; j < units.length; j++) {
				
				if(units[i] === units[j]) {

					units.splice(j, 1);

					j--;
				}
			}
		}

		this.fusionUnits = units;

		this.updated = true;
	}

	process(element) {

		this.running = true;

		this.update();

		this.internalProcess(element, true);

		this.fusionUnits = [];
		this.running = false;
	}

	internalProcess(element, internal) {
		
		var currentElement = element;

		if(!internal) {

			this.running = true;

			this.update();
		}

		var processed = [[], []];

		var bubbleUp = false;

		while(this.running) {

			this.updated = false;

			var denied = this.isDenied(currentElement);

			var verifiedFUSIONUnits =
				!denied ?
					this.getVerifiedFUSIONUnits(currentElement) :
					[];

			if(!bubbleUp) {
				
				var trickleDown = this.trickleDown(verifiedFUSIONUnits, currentElement);

				if(trickleDown && currentElement.children.length > 0) {
					
					currentElement = currentElement.children[0];
					processed.push([]);

					continue;
				}
			}

			var processedArguments = processed[processed.length - 2];
			var newArguments = processed[processed.length - 1];

			var object = this.processElement(verifiedFUSIONUnits, currentElement, newArguments);
			verifiedFUSIONUnits = this.updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);

			var terminated = this.terminate(verifiedFUSIONUnits, currentElement, newArguments);
			verifiedFUSIONUnits = this.updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);

			var added = this.isAdded(verifiedFUSIONUnits, currentElement, newArguments);
			verifiedFUSIONUnits = this.updateVerifiedUnits(verifiedFUSIONUnits, currentElement, denied);

			var jumpElement = this.jump(verifiedFUSIONUnits, currentElement, newArguments);

			if(!denied && added)
				processedArguments.push(object);

			processed[processed.length - 1] = [];

			if(terminated)
				break;

			bubbleUp = false;

			if(jumpElement == null) {

				var index = one.getIndex(currentElement);

				if(currentElement.parent == null)
					break;

				if(index < currentElement.parent.children.length - 1)
					currentElement = currentElement.parent.children[index + 1];

				else {
					
					currentElement = currentElement.parent;
					bubbleUp = true;

					processed.splice(processed.length - 1, 1);
				}
			}

			else {

				for(var i = 0; i < processed.length; i++)
					processed[i] = [];

				currentElement = jumpElement;

				bubbleUp = false;
			}
		}
	}

	updateVerifiedUnits(
		verifiedFUSIONUnits,
		currentElement,
		denied) {
		
		var update = this.updated;
		this.updated = false;

		if(update) {
			
			return !denied ?
				this.getVerifiedFUSIONUnits(currentElement) :
				[];
		}

		return verifiedFUSIONUnits;
	}

	isDenied(element) {

		if(element.content == null)
			return false;
		
		var denied = false;
		
		for(var i = 0; i < this.fusionUnits.length; i++) {

			try {
				
				if(this.fusionUnits[i].deny(element))
					denied = true;
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
		}
		
		return denied;
	}

	getVerifiedFUSIONUnits(element) {

		var verifiedFUSIONUnits = [];
		
		for(var i = 0; i < this.fusionUnits.length; i++) {
			
			try {
				
				if(this.fusionUnits[i].verify(element))
					verifiedFUSIONUnits.push(this.fusionUnits[i]);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
		}
		
		return verifiedFUSIONUnits;
	}

	trickleDown(
		verifiedFUSIONUnits,
		element) {
		
		var trickleDown = true;
		
		for(var i = 0; i < verifiedFUSIONUnits.length; i++) {
			
			var result = true;
			
			try {
				result = verifiedFUSIONUnits[i].trickleDown(element);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
			
			if(!result)
				trickleDown = false;
		}
		
		return trickleDown;
	}

	processElement(
		verifiedFUSIONUnits,
		element,
		processed) {

		var object = null;
		
		for(var i = 0; i < verifiedFUSIONUnits.length; i++) {
			
			var newObject = null;
			
			try {
				newObject = verifiedFUSIONUnits[i].process(element, processed);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
			
			if(newObject != null)
				object = newObject;
		}
		
		return object;
	}

	isAdded(
		verifiedFUSIONUnits,
		element,
		processed) {
		
		var isAdded = true;
		
		for(var i = 0; i < verifiedFUSIONUnits.length; i++) {
			
			var result = true;
			
			try {
				result = verifiedFUSIONUnits[i].isAdded(element, processed);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
			
			if(!result)
				isAdded = false;
		}
		
		return isAdded;
	}

	terminate(
		verifiedFUSIONUnits,
		element,
		processed) {
		
		var terminate = false;
		
		for(var i = 0; i < verifiedFUSIONUnits.length; i++) {
			
			var result = false;
			
			try {
				result = verifiedFUSIONUnits[i].terminate(element, processed);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
			
			if(result)
				terminate = true;
		}
		
		return terminate;
	}

	jump(
		verifiedFUSIONUnits,
		element,
		processed) {
		
		var defaultElement = null;
		var jumpElement = defaultElement;
		
		for(var i = 0; i < verifiedFUSIONUnits.length; i++) {
			
			var newJumpElement = defaultElement;
			
			try {
				newJumpElement = verifiedFUSIONUnits[i].jump(element, processed);
			}
			
			catch(error) {
				this.handleError(element, [], error);
			}
			
			if(defaultElement != newJumpElement)
				jumpElement = newJumpElement;
		}
		
		return jumpElement;
	}

	handleError(
		element,
		processed,
		error) {
		
		for(var i = 0; i < this.fusionUnits.length; i++) {
			
			try {
				this.fusionUnits[i].handleError(element, arguments, error);
			}
			
			catch(error) {
				
			}
		}
	}
}

class FUSIONUnit extends philosophersStone.PhilosophersStone {
	
	constructor() {

		super();

		this.tags.push("FUSION Unit");
	}

	deny(element) {
		return false;
	}

	verify(element) {
		return false;
	}

	trickleDown(element) {
		return true;
	}

	process(element, processed) {
		return null;
	}

	isAdded(element, processed) {
		return true;
	}

	terminate(element, processed) {
		return false;
	}

	jump(element, processed) {
		return null;
	}

	handleError(element, processed, error) {
		
	}
}

module.exports = {

	FUSION,
	FUSIONUnit
};