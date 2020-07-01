function tokenize(tokens, string) {

	var tokenize = [];

	var undefined = "";

	for(var i = 0; i < string.length; i++) {

		var token = getToken(tokens, string, i);

		if(token == null)
			undefined += string.charAt(i);

		else {

			if(undefined.length > 0) {

				tokenize.push(undefined);

				undefined = "";
			}

			tokenize.push(token);

			i += token.length - 1;
		}
	}

	if(undefined.length > 0)
		tokenize.push(undefined);

	return tokenize;
}

function getToken(tokens, string, index) {
	
	var validTokens = [];

	for(var i = 0; i < tokens.length; i++) {

		var token = tokens[i];

		if(isToken(token, string, index))
			validTokens.push(token);
	}

	if(validTokens.length == 0)
		return null;

	var validToken = validTokens[0];

	for(var i = 1; i < validTokens.length; i++) {
		
		if(validTokens[i].length > validToken.length)
			validToken = validTokens[i];
	}

	return validToken;
}

function isToken(token, string, index) {

	if(string.length - index < token.length)
		return false;
	
	for(var i = index; i < string.length && i - index < token.length; i++) {

		if(string.charAt(i)!= token.charAt(i - index))
			return false;
	}

	return true;
}

module.exports = {

	tokenize,
	getToken,
	isToken
};