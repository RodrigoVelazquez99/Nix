
var password = document.getElementById("password");
var passwordConfirm = document.getElementById("passwordConfirm");

function validatePassword(){
  if(password.value != passwordConfirm.value) {
    passwordConfirm.setCustomValidity("No coinciden las entradas");
  } else {
    passwordConfirm.setCustomValidity('');
  }
}

password.onchange = validatePassword;
passwordConfirm.onkeyup = validatePassword;
