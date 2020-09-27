var cors_api_url = 'https://cors-anywhere.herokuapp.com/';

function getInput(query) {
	return prompt("" + (query != null ? query : ""));
}

function getXMLHTTP(url) {

	var rawFile = new XMLHttpRequest();
	rawFile.open("GET", url, false);

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

function open(file) {

	if(typeof file == "function") {
		
		let input = document.createElement("input");

		input.setAttribute("type", "file");
		input.setAttribute("style", "display: none");

		var callback = file;

		let listener = function(event) {

			let upload = event.target.files[0];

			if(!upload)
				return;
			
			let reader = new FileReader();

			reader.onload = function(event) {
				callback(event.target.result);
			}

			reader.readAsText(upload);
		}

		input.addEventListener(
			'change',
			listener,
			false
		);

		document.documentElement.appendChild(input);

		input.click();

		document.documentElement.removeChild(input);
	}

	else {

		try {
			return getXMLHTTP(cors_api_url + file);
		}

		catch(error) {

			try {
				return getXMLHTTP(file);
			}

			catch(error) {
				return null;
			}
		}
	}
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

	cors_api_url,
	getInput,
	open,
	save
};