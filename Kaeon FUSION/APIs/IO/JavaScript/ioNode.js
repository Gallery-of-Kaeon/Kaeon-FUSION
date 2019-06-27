var fs = require('fs');
var readline = require('readline-sync');
var xhr = require("xmlhttprequest");

function getInput(query) {
	return readline.question(query != null ? query : "");
}

function open(file) {

	if(file.startsWith("http://") || file.startsWith("https://")) {
		
		var rawFile = new xhr.XMLHttpRequest();
		rawFile.open("GET", file, false);
		
		var allText = "";
		
		rawFile.onreadystatechange = function() {
		
			if(rawFile.readyState === 4) {
		
				if(rawFile.status === 200 || rawFile.status == 0) {
					allText = rawFile.responseText;
				}
			}
		}
		
		rawFile.send(null);
		
		return allText;
	}
	
	else
		return fs.readFileSync(file, 'utf8');
}

function save(content, file) {
	fs.writeFileSync(file, content);
}

module.exports = {

	getInput,
	open,
	save
};