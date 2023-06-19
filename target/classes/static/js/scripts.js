document.querySelector('#nome').oninvalid = function() {

	this.setCustomValidity("");

	if (!this.validity.valid) {
		this.setCustomValidity("Insira seu Nome.");
	}
};


document.querySelector('#cpf').oninvalid = function() {

	this.setCustomValidity("");

	if (!this.validity.valid) {
		this.setCustomValidity("O CPF deve conter 11 digitos.");
	}
};


document.querySelector('#senha').oninvalid = function() {

	this.setCustomValidity("");

	if (!this.validity.valid) {
		this.setCustomValidity("A senha deve conter 8 dígitos, caracteres especiais, letras maiúsculas e minúsculas.");
	}
};