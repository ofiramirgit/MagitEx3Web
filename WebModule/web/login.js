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
                // alert("success");
                    $.ajax({
                    url: '/redirect',
                    type: 'GET',
                    dataType: 'json',
                    data: $("#login-form").serialize()
                });
            }else{
                alert("NOT success");
            }
        }
    });
});