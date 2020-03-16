var one = require("./ONE.js");
var onePlus = require("./ONEPlus.js");

var kaeonFUSION = require("./KaeonFUSION.js");

var universalPreprocessor = require("./UniversalPreprocessor.js");

function parse(string) {

	return one.toList(
		onePlus.readONEPlus(
			preprocess(
				string.split("\r").join("")
			)
		)
	);
}

function preprocess(string) {
	return universalPreprocessor.preprocess(string);
}

function process(code, fusion) {

	code =
		Array.isArray(code) ?
			one.toElement(code) :
			one.toElement(parse("" + code));

	if(fusion == null) {

		start = true;

		fusion = { fusion: new kaeonFUSION.KaeonFUSION() };
	}

	else if(fusion.fusion == null) {

		start = true;

		fusion.fusion = new kaeonFUSION.KaeonFUSION();
	}

	fusion.fusion.internalProcess(code);

	return fusion.fusion.returnValue;
}

function write(element) {
	return one.writeONE(one.toElement(element));
}

module.exports = {
	parse,
	preprocess,
	process,
	write
}