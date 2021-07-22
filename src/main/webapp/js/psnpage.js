// 右上角小箭头动画
$(".ul-right > li > a").hover(function () {
    $(".mylist").addClass("mylist-hover");
    $(".down-arrow").addClass("rotate");
});
$(".ul-right > li").mouseleave(function () {
    $(".mylist").removeClass("mylist-hover");
    $(".down-arrow").removeClass("rotate");
});

// 头像放大动画
$(".uploadfile").hover(function () {
    $("#headimg").addClass("changesize");
}).mouseleave(function () {
    $("#headimg").removeClass("changesize");
});

// 标签变色特效
var tags = $(".left-tags");
tags.mouseover(function () {
    $(this).addClass("light");
}).mouseleave(function () {
    $(this).removeClass("light");
});
tags.click(function () {
    tags.removeClass("active");
    $(this).addClass("active");
});

$(function () {
    // 获取个人信息
    $.get("findUserInfoServlet",{},function (data_info) {
        $.each(data_info.list,function (index,obj) {
            $("#nav-me").html(obj['number']+" ");
            $(".personal-id > span").html(obj['number']);
            $(".personal-username >span").html(obj['username']);
            $(".personal-sex > span").html(obj['sex']);
            $(".personal-email > span").html(obj['email']);
            $(".personal-phone > span").html(obj['phonenum']);
            $(".personal-native > span").html(obj['natives']);
            $(".personal-birthday > span").html(obj['birthday']);
            $(".personal-intro > span").html(obj['intro']);
            $("#headimg").attr("src","img/users/"+obj['headimg']);
        })
    },"json");
    // 获取最近一次访问时间
    $.get("lastVisitServlet",function (data_cookie) {
        if (data_cookie) {
            $(".personal-head > div > span").html("Ta "+data_cookie+"这里")
        }
    },"json");
});