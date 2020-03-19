function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
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
            if (response.code == 2003) {
                window.alert("输入框不能为空");
            }
            if (response.code == 200) {
                $("#comment_section").hide()
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