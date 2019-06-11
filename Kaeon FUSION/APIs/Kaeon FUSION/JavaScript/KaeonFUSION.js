var philosophersStone = require("./PhilosophersStone.js");
var fusion = require("./FUSION.js");

function Use() {

	philosophersStone.abide(this, new fusion.FUSIONUnit());

	this.tags.push("Use");

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

				let path = element.children[i].content;

				if(path.indexOf("/") == -1)
					path = "./" + path;

				if(!path.toLowerCase().endsWith(".js"))
					path += ".js";

				require(path)(this.fusion);

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