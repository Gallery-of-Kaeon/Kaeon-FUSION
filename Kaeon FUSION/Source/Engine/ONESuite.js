var moduleDependencies = {
	one: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/Source/Engine/ONE.js",
	onePlus: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/Source/Engine/ONEPlus.js",
	kaeonFUSION: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/Source/Engine/KaeonFUSION.js",
	universalPreprocessor: "https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/Source/Engine/UniversalPreprocessor.js"
};

var one = require(moduleDependencies.one);
var onePlus = require(moduleDependencies.onePlus);

var kaeonFUSION = require(moduleDependencies.kaeonFUSION);

var universalPreprocessor = require(moduleDependencies.universalPreprocessor);

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