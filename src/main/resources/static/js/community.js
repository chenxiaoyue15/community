function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    $.ajax({
        type:"POST",
        url:"/comment"   ,
        contentType:'application/json',
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (d) {
            console.log(d)
            window.location.href = '/';
            if (d.success){
                $("#comment-section").hide();}
        },

        dataType:"json"
        });
}