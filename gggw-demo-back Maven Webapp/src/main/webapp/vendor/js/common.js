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

function initDatetimepicker(obj) {
	$(obj).datetimepicker({
		autoclose: 1,
		minView: 2
	});
}

function buildPageForm(data) {
    return "&start=" + data.start + "&length=" + data.length + "&draw=" + data.draw;
}

//删除修改操作封装
var Manage = {
    del :  function del(table, url, obj) {
        BootstrapDialog.show({
            title: '提示',
            message: '亲，确定删除该条记录吗?',
            buttons: [
                {
                    label: '确定',
                    action: function (dialog) {
                        $.ajax({
                            url : url+"?rnd=" + new Date().getTime(),
                            type: "POST",
                            data: $.param(obj),
                            success: function(result) {
                                debugger;
                                dialog.close();
                                if (result.error_no == 0) {
                                    showError("删除成功！");
                                    table.ajax.reload();
                                } else {
                                    showError("删除失败:" + result.error_info);
                                }
                            },
                            error: function(result) {
                                debugger;
                                dialog.close();
                                showError("删除失败:" + result.error_info);
                            }

                        });
                    }
                },
                {
                    label: '取消',
                    action: function(dialog) {
                        dialog.close();
                    }
                }]
        });

    },
    edit :  function edit(table, url, obj) {
    	obj.operatType = "1";
    	toPage("修改用户", url, $.param(obj));
    },
    getDict : function(dictEntry, data){
    	
    }
}