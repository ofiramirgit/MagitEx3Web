$("#logout").click(function(){
    alert("logout");
});

$("#fork").click(function(){
    val = ($(this).closest("tr"));
    alert(val.text());
});

