var philosophersStone = require("./PhilosophersStone.js");
var fusion = require("./FUSION.js");

function Use() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.fusion = null;

	this.verify = function(element) {

		if(this.fusion == null) {

			this.fusion =
				philosophersStone.retrieve(
					philosophersStone.traverse(this),
					function(item) {
						return philosophersStone.isTagged(item, "FUSION");
					}
				)[0];
		}

		return element.content.toLowerCase() == "use";
	}

	this.process = function(element, processed) {

		for(var i = 0; i < element.children.length; i++) {

			try {

				require(element.children[i].content)(this.fusion);

				this.fusion.update();
			}

			catch(error) {

			}
		}

		return null;
	}
}

function KaeonFUSION() {

	philosophersStone.abide(this, new fusion.FUSION());

	this.tags.push("Kaeon FUSION");

	philosophersStone.connect(this, new Use(), [], true);
}

module.exports = {

	Use,
	KaeonFUSION
};