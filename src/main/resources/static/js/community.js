function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    if (!content){
        window.alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
                //$("#comment_section").hide()
            }else {
                var isAccepted =confirm("未登录,请先登录再进行操作");
                if (isAccepted){
                    window.open("/login");
                    window.localStorage.setItem("closable","true");
                }

            }
            console.log(response)
        },
        dataType: "json"
    });
}