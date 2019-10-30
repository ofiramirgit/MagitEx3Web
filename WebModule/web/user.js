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

$(".fork_btn").click(function(){
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    username= myCookies['username'];
    other_user = $('#other_user').text();
    repo_name = $(this).closest('tr').children('td:first').text();
    new_repo_name = prompt("Please enter new repository name:", "");
    $.ajax({
        url: '/fork',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "other_user":other_user, "repo_name":repo_name, "new_repo_name":new_repo_name}
    });
    Swal.fire({
        title:'Clone',
        text: 'Repository Cloned Successfully',
        type: 'success',
        timer: 1500
    });
});

$("#home-btn").click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    username= myCookies['username'];
    window.location.href='/homePage?username='+username;

});


