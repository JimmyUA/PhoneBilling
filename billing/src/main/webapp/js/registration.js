$(document).ready(function() {
    $("#confirmPassword").keyup(validate);
});


function validate() {
    var password1 = $("#password").val();
    var password2 = $("#confirmPassword").val();


    if (password2 === password1) {
    $("#validate-status").text("Passwords are equals");
}
else {
    $("#validate-status").text("Passwords are not equals");
}

}
