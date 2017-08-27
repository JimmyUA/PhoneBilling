$(document).ready(function() {
    $("confirmPassword").keyup(validate);
});


function validate() {
    var password1 = $("password").value();
    var password2 = $("confirmPassword").value();



    if(password1=== password2){
        $("validate-status").text("valid");
    }
    else {
        $("validate-status").text("invalid");
    }

}
