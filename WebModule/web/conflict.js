$(".conflict-tr").click(function(){
    let conflit_ourpath = $(this).find("#conflict-ourpath").text();
    let conflict_theirspath = $(this).find("#conflict-theirspath").text();
    $("#save").attr("path",conflit_ourpath);
    $.ajax({
        url: '/wc_files',
        type: 'POST',
        dataType: 'json',
        data: {"path": conflit_ourpath, "isFolder": false},
        success: function (result) {
            $("#textarea-our").val(result.content);
        }
    });
    $.ajax({
        url: '/wc_files',
        type: 'POST',
        dataType: 'json',
        data: {"path": conflict_theirspath, "isFolder": false},
        success: function (result) {
            $("#textarea-theirs").val(result.content);
        }
    });
});

$('#save').click(function(){
    let filePath = $('#save').attr("path");
    let file_new_content = $('#txtarea-result').val();
    $.ajax({
        url: '/save_file',
        type: 'POST',
        dataType: 'json',
        data: {"path": filePath, "content": file_new_content},
        success: function (result) {
            console.log("FILE SAVED");
        }
    });
    let all_tr=$("tr");
    for(let i=0;i<all_tr.length;i++)
    {
        if (all_tr.eq(i).attr("path") == filePath){
            all_tr.eq(i).remove();
            $("#textarea-our").val("");
            $("#textarea-theirs").val("");
            $("#txtarea-result").val("");
        }
    }
    all_tr=$(".conflict-tr");
    if(all_tr.length==0)
    {
        let username = $("#username").text();
        let repo_name =  $("#repo_name").text();
        let new_commit_msg = prompt("Please enter commit message:", "");

        //do commit
        $.ajax({
            url: '/commit',
            type: 'POST',
            dataType: 'json',
            data: {"username":username, "repo_name":repo_name, "new_commit_msg":new_commit_msg},
        });

        //goto repository
        window.location.href='/redirectrepo?repository_name='+repo_name+'&username='+username;

    }
});