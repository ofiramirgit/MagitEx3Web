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
            if (result.isBranchExist) {
                if (result.isNotChanged) {
                    checkout(username, repo_name);
                } else {
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
                            commit(username, repo_name);
                        }
                        checkout(username, repo_name);
                    })

                }
            }
            else
                alert("BRANCH NAME IS NOT EXIST!");
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

$('#pullRequest').click(function () {
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
        url: '/pull_request',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "user_name_rr":user_rr, "repo_name_rr":repo_rr}
    });
    Swal.fire({
        title:'Pull Request',
        text: 'Pull Request sent Successfully',
        type: 'success',
        timer: 1500
    });
});

$('#merge_pr_button').click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    let username= myCookies['username'];
    repo_name = $("#repoName").text();
    theirs_user = prompt("Choose user to merge with:", "");

    $.ajax({
        url: '/check_user_exist',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "theirs_user":theirs_user},
        success: function(result){
            if(result.isValid){
                window.location.href='/merge_pull_request?username='+username+'&repo_name='+repo_name+'&theirs_user='+theirs_user;
            }
            else
            {
                alert(theirs_user + " did not make a PR to your repository");
            }

        }
    });
});

$('#reject_pr_button').click(function () {
    myCookies={};
    var kv = document.cookie.split(";");
    for(var id in kv)
    {
        var cookie = kv[id].split("=");
        myCookies[cookie[0].trim()]=cookie[1];
    }
    let username= myCookies['username'];
    repo_name = $("#repoName").text();
    theirs_user = prompt("Choose user to reject PR:", "");
    $.ajax({
        url: '/check_user_exist',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "theirs_user":theirs_user},
        success: function(result){
            if(result.isValid) {
                $.ajax({
                    url: '/reject_pull_request',
                    type: 'POST',
                    dataType: 'json',
                    data: {"username": username, "repo_name": repo_name, "theirs_user": theirs_user}
                });
            }
            else
            {
                alert(theirs_user + " did not make a PR to your repository");
            }
        }
    });

});

$('body').on('click', '.li-file', function() {
    // brother_is_selected_and_folder(this);
    // $(".li-file").addClass("li-not-item-selected");
    $("#content").prop("disabled", true);
    $("#save_file").removeClass("show").addClass("hide");
    $("#create_delete").removeClass("hide").addClass("show");
    // $("#buttons-wc").removeClass("hide").addClass("show");
    if (this === event.target) {
        let file_isFolder = $(this).attr("is_folder");
        if (file_isFolder == "false") {
            // $(this).addClass("item-selected");
            // $(this).removeClass("li-not-item-selected");
            let filePath = $(this).attr("path");
            $("#hidden-path").attr("path",filePath);
            $("#hidden-path").attr("is_folder","false");
            console.log(filePath);
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
$('body').on('click', '.li-folder', function() {
    $("#content").prop("disabled", true);
    $("#save_file").removeClass("show").addClass("hide");
    let filePath = $(this).attr("path");
    let file_isFolder = $(this).attr("is_folder");
    if (this === event.target) {
        $("#hidden-path").attr("path",filePath);
        $("#hidden-path").attr("is_folder","true");
        if (file_isFolder == "true") {
            let icon = $(this).find(".fa").eq(0);
            let span = $(this).find(".folder");
            var attribute ="<ul class=\"fa-ul\">";
            if (icon.hasClass("fa-folder")) {
                $.ajax({
                    url: '/wc_files',
                    type: 'POST',
                    dataType: 'json',
                    data: {"path": filePath, "isFolder": file_isFolder},
                    success: function (result) {
                        icon.toggleClass("fa-folder").toggleClass("fa-folder-open");
                        for (let i = 0; i < result.mainfolder.length; i++) {
                            // span.empty();
                            let fileSplitArray = result.mainfolder[i].path.split("\\\\");
                            let file_name = fileSplitArray[fileSplitArray.length - 1];
                            var li_elements = "";
                            if (!result.mainfolder[i].isFolder) {
                                li_elements += "<li class=\"li-file\" path=\""+result.mainfolder[i].path+"\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                            } else {
                                li_elements += "<li class=\"li-folder\" path=\"" + result.mainfolder[i].path + "\" is_folder=\"true\"><span class=\"fa-li\"><i class=\"fa fa-folder\"></i></span>" + file_name + "<span class=\"folder\"></span></li>";
                            }
                            attribute += li_elements;
                        }
                        attribute += "</ul>";
                        span.append(attribute);
                    }
                });

            } else {
                console.log("in else");
                icon.toggleClass("fa-folder-open").toggleClass("fa-folder");
                console.log($(this).find(".li-file"));
                $(this).find(".li-file").remove();
                $(this).find(".li-folder").remove();
                $(this).find(".fa-ul").remove();
            }
        }
    }
});

$('body').on('click', '.li-commit-file', function() {
    if (this === event.target) {
        let file_isFolder = $(this).attr("is_folder");
        if (file_isFolder == "false") {

            let filePath = $(this).attr("path");
            console.log(filePath);
            $.ajax({
                url: '/wc_files',
                type: 'POST',
                dataType: 'json',
                data: {"path": filePath, "isFolder": file_isFolder},
                success: function (result) {
                    $("#content-commit-file").val(result.content);
                }
            });
        }
    }
});
$('body').on('click', '.li-commit-folder', function() {
    let filePath = $(this).attr("path");
    let file_isFolder = $(this).attr("is_folder");
    if (this === event.target) {
        if (file_isFolder == "true") {
            let icon = $(this).find(".fa").eq(0);
            let span = $(this).find(".folder");
            var attribute ="<ul class=\"fa-ul\">";
            if (icon.hasClass("fa-folder")) {
                $.ajax({
                    url: '/wc_files',
                    type: 'POST',
                    dataType: 'json',
                    data: {"path": filePath, "isFolder": file_isFolder},
                    success: function (result) {
                        icon.toggleClass("fa-folder").toggleClass("fa-folder-open");
                        for (let i = 0; i < result.mainfolder.length; i++) {
                            // span.empty();
                            let fileSplitArray = result.mainfolder[i].path.split("\\\\");
                            let file_name = fileSplitArray[fileSplitArray.length - 1];
                            var li_elements = "";
                            if (!result.mainfolder[i].isFolder) {
                                li_elements += "<li class=\"li-commit-file\" path=\""+result.mainfolder[i].path+"\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                            } else {
                                li_elements += "<li class=\"li-commit-folder\" path=\"" + result.mainfolder[i].path + "\" is_folder=\"true\"><span class=\"fa-li\"><i class=\"fa fa-folder\"></i></span>" + file_name + "<span class=\"folder\"></span></li>";
                            }
                            attribute += li_elements;
                        }
                        attribute += "</ul>";
                        span.append(attribute);
                    }
                });

            } else {
                console.log("in else");
                icon.toggleClass("fa-folder-open").toggleClass("fa-folder");
                console.log($(this).find(".li-commit-file"));
                $(this).find(".li-commit-file").remove();
                $(this).find(".li-commit-folder").remove();
                $(this).find(".fa-ul").remove();
            }
        }
    }
});

$('#save_file').click(function(){
    let filePath = $('#save_file').attr("file_path");
    let file_new_content = $('#content').val();
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


$('#wc').click(function () {
    getRepositoryWc();
});

function getRepositoryWc(){
    $("#ul-files").empty();
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    $.ajax({
        url: '/wc',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name},
        success:function (result) {
            $("#buttons-wc").removeClass("hide").addClass("show");
            $("#hidden-path").attr("is_folder","true");
            $("#hidden-path").attr("path",result.mainpath);
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
}


$('#create-btn').click(function () {
    let file_name = prompt("Please enter file name:", "");
    let filePath = $('#hidden-path').attr("path");
    let folderPath = filePath;
    let is_folder = $('#hidden-path').attr("is_folder");
    let elementToInsertInto;
    if(is_folder=="false") {
        let elements = $('.li-file[path]');
        for (let i = 0; i <= elements.length; i++) {
            if (elements.eq(i).attr("path") == filePath) {
                // elementToInsertInto = elements.eq(i);
                let parentElement = elements.eq(i).closest(".li-folder");
                folderPath = parentElement.attr("path");
                elementToInsertInto = elements.eq(i).closest(".fa-ul");
            }
        }
    }else{
        let elements = $('.li-folder[path]');
        for (let i = 0; i <= elements.length; i++) {
            if (elements.eq(i).attr("path") == filePath) {
                // elementToInsertInto = elements.eq(i);
                // let parentElement = elements.eq(i).closest(".li-folder");
                console.log(elements.eq(i));
                folderPath = elements.eq(i).attr("path");
                elementToInsertInto = elements.eq(i).children('.folder').children(".fa-ul");
                // console.log(folderPath);

            }
        }
        // if(folderPath == null)
        // {
        //     folderPath = $('#hidden-path').attr("path");
        //     elementToInsertInto= $("#ul-files");
        // }
    }
    $.ajax({
        url: '/create_file',
        type: 'POST',
        dataType: 'json',
        data: {"file_name": file_name, "folderPath":folderPath},
        success: function (result) {
            if(result.result) {
                console.log(result);
                console.log(elementToInsertInto);
                // let faulElement = elementToInsertInto.closest(".fa-ul");
                let li_element = "<li class=\"li-file\" path=\"" + folderPath + "\\" + file_name + "\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                elementToInsertInto.append(li_element);
            }
        }
    });

});

$('#edit-btn').click(function () {
    let is_folder = $('#hidden-path').attr("is_folder");
    if(is_folder=="false") {
        $("#content").prop("disabled", false);
        $("#save_file").removeClass("hide").addClass("show");
    }else{
        alert("you have to select file to edit");
    }
});

$('#delete-btn').click(function () {
    let filePath = $('#hidden-path').attr("path");
    if($('#hidden-path').attr("is_folder")=="false") {
        $.ajax({
            url: '/delete_file',
            type: 'POST',
            dataType: 'json',
            data: {"path": filePath},
            success: function (data) {
                if (data.result) {
                    getRepositoryWc();
                    $("#content").val("");
                    $("#content").prop("disabled", true);
                }
            }
        });
    } else {
        alert("can delete only files");
    }
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
    let commit_sha1 = $(this).find("#commit-sha1").text();
    $(".commits-tr").removeClass("commit_selected");
    $(this).addClass("commit_selected");
    getCommitFiles(commit_sha1);
});
function getCommitFiles(commit_sha1){
    $("#ul-commit-files").empty();
    username= myCookies['username'];
    repo_name = $("#repoName").text();
    $.ajax({
        url: '/showCommit',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name,"commit_sha1":commit_sha1},
        success:function (result) {
            for(let i = 0; i < result.mainfolder.length; i++){
                let fileSplitArray = result.mainfolder[i].path.split("\\\\");
                let file_name = fileSplitArray[fileSplitArray.length-1];
                if(!result.mainfolder[i].isFolder) {
                    var li_element = "<li class=\"li-commit-file\" path=\""+result.mainfolder[i].path+"\" is_folder=\"false\"><span class=\"fa-li\"><i class=\"fa fa-file\"></i></span>" + file_name + "</li>";
                }else {
                    li_element = "<li class=\"li-commit-folder\" path=\""+result.mainfolder[i].path+"\" is_folder=\"true\"><span class=\"fa-li\"><i class=\"fa fa-folder\"></i></span>" + file_name +"<span class=\"folder\"></span></li>";
                }
                $("#ul-commit-files").append(li_element);
            }
        }
    });
}
function commit(username,repo_name){
    new_commit_msg = prompt("Please enter commit message:", "");
    $.ajax({
        url: '/commit',
        type: 'POST',
        dataType: 'json',
        data: {"username":username, "repo_name":repo_name, "new_commit_msg":new_commit_msg}
    });
}

