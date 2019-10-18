
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
        data: {"username":val}
    });
    });