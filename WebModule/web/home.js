
$("#logout").click(function(){
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    window.location.replace('loginPage.jsp');
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
    repo_name = $(this).find("#repo-name").text();
    username = $("#username").text();

    $.ajax({
        url: '/redirectrepo',
        type: 'GET',
        dataType: 'json',
        data: {"repository_name":repo_name,
                "username":username}
    });
});

$("#addButton").click(function(){
    val=$("#username").text();

    $.ajax({
        url: '/readXml',
        type: 'POST',
        dataType: 'json',
        data: {"username":val},
        success:function(result){
            console.log(result);
            if(result.repositoryAdded){
                location.reload(true);
            }
            else
            {
                alert("Repository Already Exist");
            }
        }
    });
    });
