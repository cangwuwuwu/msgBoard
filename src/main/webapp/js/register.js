$(function () {
    $("#datepicker").datepicker();
    var retry = $("#retrypassword");

    retry.blur(function () {
        var pwd = $("#inputPassword3").val();
        var repwd = retry.val();
        if (pwd != repwd) {
            alert("两次密码输入不一致！");
            retry.prop("value","");
        }
    });
});



