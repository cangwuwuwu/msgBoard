var comment = null;
var pages,counts,lists,page;
var pre = $("#previous");
var next = $("#next");
var long = $("#limitSize");
var myAuth = 0;

// 获取当前页
var currentPage = localStorage.getItem('currentPage');

function changePage(ps) {
    localStorage.setItem('currentPage',ps);
    window.location.reload();
}

function div(name, msg, time) {
    comment = '<li>\n' +
        '<hr>\n' +
        '<div class="mname">' + name +
        '</div>\n' +
        '<div class="mmsg">' + msg +
        '</div>\n' +
        '<div class="mtime">' + time +
        '</div>\n' +
        '<hr>\n' +
        '</li>';
}


$(function () {
    // 主页 个人中心 课程表页面共用的登录检查
    $.get("findUserInfoServlet",function (data_cookie) {
        var lc = $("#loginCheck");
        if (data_cookie) {
            let user = data_cookie.list[0];
            myAuth = user.auth;
            // console.log(user);
            lc.html(user.username);
            lc.attr('href','personalpage.html');
            $("#bar-login-right").append("<a class='bar navbar-brand' href='clearCookieServlet'>退出登录</a>");
            $(".input-name").val(user.username);
        }
    },"json");

    // 页面加载时从数据库读取留言数据
    $.post("displayMsgServlet", {currentPage: currentPage}, function (data_display) {
        pages = data_display.totalPage;
        counts = data_display.totalCount;
        lists = data_display.list;
        page = data_display.currentPage;
        if (page > 1){
            pre.click(function () {
                changePage(page - 1);
            });
        } else {
            pre.addClass("disabled");
        }

        if (page < pages) {
            next.click(function () {
                changePage(parseInt(page) + 1);
            });
        } else {
            next.addClass("disabled");
        }

        $.each(lists, function (index, obj) {
            $("#ul").append(
                '<li id="msgList-'+ obj['id'] +'">\n' +
                    '<hr>\n' +
                    //获取名字
                    "<div class='mname'>#"+ obj['id'] + ' ' + obj['mname'] + "</div>" +
                    //获取留言
                    "<div class='mmsg'>" + obj['mmsg'] +
                        (myAuth===1?"<div class='del-btn' onclick='delMsg("+ obj['id'] +")'>删除</div>":"") +
                    "</div>" +
                    //获取留言时间
                    "<div class='mtime'>" + obj['mtime'] + "</div>" +
                    '<hr>\n' +
                "</li>"
            );
        });
        $("#totalPage").html(pages);
        $("#totalCount").html(counts);

        // 当前页显示蓝色
        for (var i = pages; i >= 1 ;i-- ) {
            pre.after("<li id='page-"+ i +"'><a onclick='changePage("+ i +")'>"+ i +"</a></li>");
            if (i === page) {
                $("#page-"+i).addClass("active");
            }
        }

    }, "json");
    // 清除所有本地存储
    localStorage.clear();

    // 设置留言最长为100个字符
    $("#textArea").bind("input propertychange", function () {
        long.html(100 - this.value.length);
        if (this.value.length > 100) {
            long.css("color", "red");
            // 禁用发表按钮
            $(".submitMsg").attr("disabled", true);
        } else {
            long.css("color", "black");
            $(".submitMsg").attr("disabled", false);
        }
    });
    // 提交留言
    $(".submitMsg").click(function () {
        var _name = $(".input-name").val();
        var _msg = $("#textArea").val();
        if (_msg !== "" && _name !== "") {
            $.post("sensitiveWordsServlet", {name: _name, msg: _msg}, function (data_submit) {
                if (data_submit.code === 200) {
                    alert("留言成功！");
                    div(data_submit.name, data_submit.msg, data_submit.time);
                    $("#ul").prepend(comment);
                    // 清空留言框中的内容
                    $("#textArea").val("");
                    $("#limitSize").html(100);
                } else {
                    alert(data_submit.msg);
                    window.location.href = "clearCookieServlet";
                }
            }, "json");
        } else {
            alert("请输入留言或昵称！请检查");
            window.location.reload();
        }
    });
});

function delMsg(id) {
    var msg = confirm("确定要删除吗?");
    if (msg === true) {
        $.get("deleteMsg", {mid: id}, function (data) {
            // console.log(data);
            if(data == 1) {
                alert("删除成功！");
                $("#msgList-"+ id).remove();
                let count = $("#totalCount").html();
                $("#totalCount").html(count-1);
            }
        })
    }

}