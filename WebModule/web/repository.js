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
    user_rr = $("#user_rr").text();
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
$('body').on('click', '.li-file', function() {
    let filePath = $(this).attr("path");
    let file_isFolder = $(this).attr("is_folder");
    if (this === event.target) {
        console.log(filePath);
        if (file_isFolder == "false") {
            $.ajax({
                url: '/wc_files',
                type: 'POST',
                dataType: 'json',
                data: {"path": filePath, "isFolder": file_isFolder},
                success: function (result) {
                    $("#content").val(result.content);
                    $("#save_file").attr("file_path",filePath);
                }
            });
        }
    }
});

$('#save_file').click(function(){
    let filePath = $('#save_file').attr("file_path");
    let file_new_content = $('#content').val();
    console.log(filePath);
    console.log(file_new_content);
    $.ajax({
        url: '/save_file',
        type: 'POST',
        dataType: 'json',
        data: {"path": filePath, "content": file_new_content},
        success: function (result) {
            console.log("FILE SAVED");
        }
    });
});

$('body').on('click', '.li-folder', function() {
    // do something
    let filePath = $(this).attr("path");
    let file_isFolder = $(this).attr("is_folder");
    if (this === event.target) {
        if (file_isFolder == "true") {
            let icon = $(this).find(".fa");
            let span = $(this).find(".folder");
            console.log(span);
            if (icon.hasClass("fa-folder")) {
                console.log("in if");
                $.ajax({
                    url: '/wc_files',
                    type: 'POST',
                    dataType: 'json',
                    data: {"path": filePath, "isFolder": file_isFolder},
                    success: function (result) {
                        icon.toggleClass("fa-folder").toggleClass("fa-folder-open");
                        for (let i = 0; i < result.mainfolder.length; i++) {

                            let fileSplitArray = result.mainfolder[i].path.split("\\\\");
                            let file_name = fileSplitArray[fileSplitArray.length - 1];
                            var li_elements = "";
                            if (!result.mainfolder[i].isFolder) {
                                li_elements += "<li class=\"li-file\" path=\"" + result.mainfolder[i].path + "\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                            } else {
                                li_elements += "<li class=\"li-folder\" path=\"" + result.mainfolder[i].path + "\" is_folder=\"true\"><span class=\"fa-li\"><i class=\"fa fa-folder\"></i></span>" + file_name + "<span class=\"folder\"></span></li>";
                            }
                            var attribute = "<ul class=\"fa-ul\">" + li_elements + " </ul>";
                            span.append(attribute);
                        }
                    }
                });

            } else {
console.log("in else");
                icon.toggleClass("fa-folder-open").toggleClass("fa-folder");
                console.log($(this).find(".li-file"));
                $(this).find(".li-file").remove();
                $(this).find(".li-folder").remove();
                // span.remove();
                // $( ".li-file" ).remove();
            }

        }
    }
});

$('#wc').click(function () {
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    $.ajax({
        url: '/wc',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name},
        success:function (result) {
            console.log(result.mainfolder);
            for(let i = 0; i < result.mainfolder.length; i++){
                let fileSplitArray = result.mainfolder[i].path.split("\\\\");
                let file_name = fileSplitArray[fileSplitArray.length-1];
                if(!result.mainfolder[i].isFolder) {
                    var li_element = "<li class=\"li-file\" path=\""+result.mainfolder[i].path+"\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                }else {
                    li_element = "<li class=\"li-folder\" path=\""+result.mainfolder[i].path+"\" is_folder=\"true\"><span class=\"fa-li\"><i class=\"fa fa-folder\"></i></span>" + file_name +"<span class=\"folder\"></span></li>";
                }
                $("#ul-files").append(li_element);
            }
        }
    });
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

