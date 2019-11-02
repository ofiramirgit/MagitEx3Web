$("#logout").click(function(){
    username= myCookies['username'];
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    $.ajax({
        url: '/logout',
        type: 'POST',
        dataType: 'json',
        data: {"username":username}
    });
    Swal.fire({
        title:'Logged Out',
        text: 'Logged Out',
        type: 'success',
        showConfirmButton: true
    });
    window.location.href='loginPage.jsp';
});
$(".users-li").click(function() {
    user = $(this).text();
    window.location.href='/redirectuser?username='+user;

    username=amir
});

$(".repos-tr").click(function(){
    username= myCookies['username'];
    repo_name = $(this).find("#repo-name").text();
    window.location.href='/redirectrepo?repository_name='+repo_name+'&username='+username;

});

$("#addButton").click(function() {
    let xmlFileChooser = prompt("Please paste XML local path", "");

    $.ajax({
        url: '/readXml',
        type: 'POST',
        dataType: 'json',
        data: {"username": username, "filepath": xmlFileChooser},
        success: function () {
            location.reload();
        }
    });
});

/*$("#xmlFileInput").change(function() {
        username = $("#username").text();
    let xmlFileInput = document.getElementById("xmlFileInput").files[0].get;

    if (xmlFileInput) {
            $.ajax({
                url: '/readXml',
                type: 'POST',
                dataType: 'json',
                data: {"username": username, "filepath": xmlFileInput},
                success: function () {
                        location.reload();
            }
          });
        }else {
            alert("No file chosen");
        }
    });*/

