/**
 * Created by cgw on 2016-11-17.
 */

//检验不为空
function checkNotBlank(obj) {
    var valid = true;
    $(obj).find("input").each(function() {
        if ($(this).attr("valid") == "NotBlank" && $(this).val() == "") {
            showError($(this).attr("placeholder"));
            checkErrorHandle(this);
            valid = false;
            return false;
        }
    });
    return valid;
}

//输入框校验错误执行
function checkErrorHandle(obj) {
    $(obj).parent().addClass("has-error");
    $(obj).next().addClass("glyphicon-warning-sign");
    $(obj).focus();
    $(obj).attr("oninput", "valChange(this)");
}

function valChange(obj) {
    //js Dom 转为 jqery对象    obj     $(obj)
    $(obj).parent().removeClass("has-error");
    $(obj).next().removeClass("glyphicon-warning-sign");
    $(obj).removeAttr("oninput");
}

//错误提示
function showError(errorModelBody) {
    BootstrapDialog.alert({
        title: "温馨提示",
        message: errorModelBody
    });
}