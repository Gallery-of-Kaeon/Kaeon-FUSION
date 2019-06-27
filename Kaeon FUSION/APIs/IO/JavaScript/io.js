var platform = "browser";

if(typeof process === 'object') {

	if(typeof process.versions === 'object') {

		if(typeof process.versions.node !== 'undefined') {
			platform = "node";
		}
	}
}

if(platform == "node")
	module.exports = require("./ioNode.js");

if(platform == "browser")
	module.exports = require("./ioBrowser.js");