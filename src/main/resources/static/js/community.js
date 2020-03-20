function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {

    if (!content) {
        window.alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
                //$("#comment_section").hide()
            } else {
                var isAccepted = confirm("未登录,请先登录再进行操作");
                if (isAccepted) {
                    window.open("/login");
                    window.localStorage.setItem("closable", "true");
                }

            }
            console.log(response)
        },
        dataType: "json"
    });

}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

// 展开二级评论
function openComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data, function (index, comment) {
                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    });
                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body",
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.accountId
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        class: "menu"
                    }).append($("<span/>", {
                        class: "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    }))).append($("<hr/>", {
                        class: "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp"
                    }));
                    subCommentContainer.prepend(commentElement);
                    subCommentContainer.prepend(mediaBodyElement);
                });
                comments.addClass("in");
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");

            });
        }
    }
}
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    //不等于-1说明存在这个标签了
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }

    //有标签了就输入一个逗号，没有就直接输入值

    
}
function showSelectTag() {
    $("#select-tag").show();
    
}