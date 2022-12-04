/**
 * 
 */
function validateForm() {
	//validations for the location field - cannot be empty
	var locationInput = document.forms["createEvent"]["location"].value;
	if (!locationInput) {
		alert("Please provide location for the event");
		return false;
	}

	//validations for the description field - cannot be empty and minimum 50 characters.
	var descInput = document.forms["createEvent"]["description"].value;
	if (descInput == '') {
		alert("Description is not provided. It is required");
		return false;
	}
	if (descInput.length < 20) {
		alert("Description should have minimum 20 characters!");
		return false;
	}

	//validations for the name field - cannot be empty and only alphabets
	var nameInput = document.forms["createEvent"]["eventname"].value;
	if (nameInput == '') {
		alert("Please Enter your Name. It is required");
		return false;
	}
	var regex = new RegExp("^[a-zA-Z ]+$");
	if (!regex.test(nameInput)) {
		alert("Error: Event Name contains invalid characters!");
		return false;
	}

	//validations for the date field - cannot be empty and alteast one day after today
	var dateInput = document.forms["createEvent"]["date"].value;
	if (!dateInput) {
		alert("Please Enter Date for the event!");
		return false;
	}

	let today = new Date().toISOString().slice(0, 10)
	if (dateInput < today) {
		alert("Please Enter Date atleast one day after today!");
		return false;
	}
}

function validateLogin() {

	//validations for the username field - cannot be empty
	var usernameInput = document.forms["eventLogin"]["username"].value;
	if (usernameInput == '') {
		alert("Please Enter User Name. It is required to login!");
		return false;
	}

	//validations for the password field - cannot be empty
	var pwdInput = document.forms["eventLogin"]["pwd"].value;
	if (pwdInput == '') {
		alert("Please Enter password to login!");
		return false;

	}
}

function validateRegister() {

	//validations for the username field - cannot be empty
	var unameInput = document.forms["eventLogin"]["uname"].value;
	if (unameInput == '') {
		alert("Please provide a username to register");
		return false;
	}

	//validations for the password field - cannot be empty and both the passwords should match
	var pwdInput = document.forms["eventLogin"]["password"].value;
	if (pwdInput == '') {
		alert("Please provide a password to register");
		return false;

	}
	
	var confirmpwdInput = document.forms["eventLogin"]["confirmpwd"].value;
	if (confirmpwdInput == '') {
		alert("Please re-enter your password to confirm");
		return false;

	}
	
	if (confirmpwdInput != pwdInput) {
		alert("Passwords do not match. Try again!");
		return false;

	}
	//provide username when submitting the event details
	confirm("Your username for the event login will be: " + unameInput);
}