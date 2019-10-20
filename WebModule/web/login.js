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
                    $.ajax({
                    url: '/homePage',
                    type: 'GET',
                    dataType: 'json',
                    data: $("#login-form").serialize(),
                        success:function (data) {
                        console.log(data);
                        }
                });
            }else{
                alert("NOT success");
            }
        }
    });
});

