$("#login-form").submit(function (event) {
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
