$("#login-form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: '/update',
        type: 'POST',
        // contentType: "application/json",
        dataType: 'json',
        data: $("#login-form").serialize(),
        success: function (data) {
            if(data.isValid){
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





