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
                    let cookieString="";
                    cookieString = "username="+username+";";
                    document.cookie=cookieString;
                    window.location.href='/homePage?username='+username;
            }else{
                alert("NOT success");
            }
        }
    });
});

$("#signup").click(function () {
    let username = prompt("Please enter user name:", "");
    if(username==""){
        alert("error! empty input");
    }
    else {
        $.ajax({
            url: '/signup',
            type: 'POST',
            dataType: 'json',
            data: {"username": username},
            success: function (result) {
                if (!result.user_exist) {
                    sessionStorage.setItem('username', username);
                    // var expiresAttrib = new Date(Date.now()+60*1000).toString();
                    let cookieString = "";
                    cookieString = "username=" + username + ";";
                    document.cookie = cookieString;
                    window.location.href = '/homePage?username=' + username;
                }
            }
        });
    }
});

