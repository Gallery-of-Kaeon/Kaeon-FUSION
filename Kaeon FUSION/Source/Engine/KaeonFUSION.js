var moduleDependencies = {
	philosophersStone: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Philosophers-Stone/master/Philosopher's%20Stone/API/JavaScript/PhilosophersStone.js",
	fusion: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/APIs/FUSION/JavaScript/FUSION.js",
	io: "https://raw.githubusercontent.com/Gallery-of-Kaeon/JavaScript-Utilities/master/JavaScript%20Utilities/IO/io.js"
};

var philosophersStone = require(moduleDependencies.philosophersStone);
var fusion = require(moduleDependencies.fusion);

var io = require(moduleDependencies.io);

var platform = "Browser";

if(typeof process === 'object') {

	if(typeof process.versions === 'object') {

		if(typeof process.versions.node !== 'undefined') {
			platform = "Node";
		}
	}
}

function Use() {

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

		for(var i = 0; i < element.children.length; i++) {

			try {

				let path = element.children[i].content;

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

function KaeonFUSION() {

	philosophersStone.abide(this, new fusion.FUSION());

	this.tags.push("Kaeon FUSION");

	this.returnValue = null;

	philosophersStone.connect(this, new Use(), [], true);
}

module.exports = {

	Use,
	KaeonFUSION
};