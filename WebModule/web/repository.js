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
    window.location.replace('loginPage.jsp');
});

$('#create_new_branch').click(function () {
    alert('new branch');
});

$('#checkout_head_branch').click(function () {
    alert('checkout head branch');
});
$('#show_all_branches').click(function () {
    alert('show all branches');
});
$('#pull').click(function () {
    alert('pull');
});
$('#push').click(function () {
    alert('push');
});
$('#wc').click(function () {
    alert('wc');
});

$('#commitButton').click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    new_commit_msg = prompt("Please enter commit message:", "");

    $.ajax({
        url: '/commit',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "new_commit_msg":new_commit_msg}
    });
});


$(".commits-tr").click(function() {
    commit_sha1 = $(this).find("#commit-sha1").text()
    alert(commit_sha1);
});




