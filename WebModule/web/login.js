$("#login-form").submit(function (event) {
    username=$("#username").val();
    event.preventDefault();
    $.ajax({
        url: '/login',
        type: 'POST',
        // contentType: "application/json",
        dataType: 'json',
        data: $("#login-form").serialize(),
        success:
            function (data) {
                if(data.isValid){
                    sessionStorage.setItem('username',username);
                    // var expiresAttrib = new Date(Date.now()+60*1000).toString();
                    var cookieString="";
                    cookieString = "username="+username+";";
                    document.cookie=cookieString;
                    window.location.href='/homePage?username='+username;
            }else{
                alert("NOT success");
            }
        }
    });
});

