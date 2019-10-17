$("#logout").click(function(){
    alert("logout");
});

$(".users-li").click(function() {
    val = $(this).text();
    $.ajax({
        url: '/redirectuser',
        type: 'GET',
        dataType: 'json',
        data: {"username":val}
    });
});

$(".repos-tr").click(function(){
    val = ($(this).text());
    alert(val);
});

