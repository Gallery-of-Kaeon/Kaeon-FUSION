function getInput(query) {
	return prompt("" + (query != null ? query : ""));
}

function open(file) {

	var rawFile = new XMLHttpRequest();
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

function save(content, file) {

	let element = document.createElement('a');

	element.setAttribute(
		'href',
		'data:text/plain;charset=utf-8,' + encodeURIComponent(content));

	element.setAttribute('download', file);

	element.style.display = 'none';
	document.documentElement.appendChild(element);

	element.click();

	document.documentElement.removeChild(element);
}

module.exports = {

	getInput,
	open,
	save
};