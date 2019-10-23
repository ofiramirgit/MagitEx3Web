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

$('#create_new_branch').click(function () {
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    branch_name = prompt("Please enter branch name:", "");
    $.ajax({
        url: '/create_branch',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name,"branch_name":branch_name},
        success:function (result) {
            if(result.isValid)
            {
                alert("BRANCH CREATED");
            }
            else
            {
                alert("BRANCH EXIST ALREADY!");
            }

        }
    });
});

$('#checkout_head_branch').click(function () {
    username = myCookies['username'];
    repo_name = $("#repoName").text();
    branch_name = prompt("Please enter branch name:", "");
    $.ajax({
        url: '/wc_changed',
        type: 'POST',
        dataType: 'json',
        data: {"username": username, "repo_name": repo_name, "branch_name": branch_name},
        success: function (result) {
            if(result.isNotChanged)
            {
                //checkout
                checkout(username,repo_name);
            }
            else
            {
                //ask if to commit
                Swal.fire({
                    title: 'There are Open changes',
                    text: "Do You want to commit those changes first?",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, Commit!'
                }).then((result) => {
                    if (result.value) {
                        commit(username,repo_name);
                    }
                    checkout(username,repo_name);
                })

            }
        }
    });
});

function checkout(username,repo_name) {
    $.ajax({
        url: '/check_out',
        type: 'POST',
        dataType: 'json',
        data: {"username": username, "repo_name": repo_name, "branch_name": branch_name},
        success: function (result) {
            if(result.isCheckedOut)
            {
                Swal.fire(
                    'Check Out Head Branch',
                    'Checked Out Head Branch Successfully!',
                    'success'
                )
            }
        }

    });
}
$('#show_all_branches').click(function () {
    alert('show all branches');
});

$('#pull').click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    user_rr =$("#user_rr").text();
    repo_rr =$("#repo_rr").text();
    $.ajax({
        url: '/pull',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "user_name_rr":user_rr, "repo_name_rr":repo_rr}
    });

});

$('#push').click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    user_rr =$("#user_rr").text();
    repo_rr =$("#repo_rr").text();
    $.ajax({
        url: '/push',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "user_name_rr":user_rr, "repo_name_rr":repo_rr}
    });
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
    commit(username,repo_name);

});


$(".commits-tr").click(function() {
    commit_sha1 = $(this).find("#commit-sha1").text()
    alert(commit_sha1);
});

function commit(username,repo_name){
    new_commit_msg = prompt("Please enter commit message:", "");
    $.ajax({
        url: '/commit',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "new_commit_msg":new_commit_msg}
    });
}

