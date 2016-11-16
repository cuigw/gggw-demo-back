<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- page-wrapper  start -->

		<div class="row">
            <div class="col-lg-12">
                <h3 class="page-header text-primary">用户管理
                	<button type="button" class="btn btn-outline btn-primary btn-sm" style="float:right;" onclick="toPage(this, '/toUserEdit', { operatType: '0' })" >添加用户</button>
                </h3>
            </div>
            <!-- /.col-lg-12 -->
        </div>

	    <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTable add by cgw   搜索功能需要优化 , 使用$("#form").serializeArray();传值
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="userList">
                                <thead>
                                    <tr>
                                        <th>用户编号</th>
                                        <th>姓名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
         

<!-- page-wrapper   end -->

<script>

    $(document).ready(function() {
        // API:     http://datatables.club/reference/option/
        // EXAMPLE: http://datatables.club/example/user_share/mir/

        var $table = $('#userList');
        var _table = $table.DataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION ,{
            stripeClasses   :       ["odd", "even"],        //为奇偶行加上样式，兼容不支持CSS伪类的场合
            serverSide      :       true,                  //启用服务器端分页
            renderer        :       "bootstrap",            //渲染样式：Bootstrap和jquery-ui
            searching       :       false,                 //禁用原生搜索
            ajax            :       {
                    url : "${contextPath }/ajaxUserList",
                    type: "POST"
            },
            columns         :       [
                { "data": "userNo" },
                { "data": "userName" },
                { "data": "mobile" },
                { "data": "email" },
                { "data":  null }
            ],
            createdRow      :       function ( row, data, index ) {
                //给当前行加样式
                if (data.userNo=="000003") {
                    $(row).addClass("success");
                }
                //给当前行某列加样式
                debugger;
                $('td', row).eq(3).addClass(data.email == "cuigw0293@cairenhui.com" ? "text-success":"text-error");
                //不使用render，改用jquery文档操作呈现单元格
                var $btnEdit =   $('<button type="button" class="btn btn-sm btn-primary btn-edit " >修改</button>  ');
                var $btnDel  =   $('<button type="button" class="btn btn-sm btn-danger btn-del " >删除</button>');
                $('td', row).eq(4).text("");
                $('td', row).eq(4).append($btnEdit).append("  ").append($btnDel);
            }
        }));

        //点击删除按钮
        $table.on("click",".btn-del",function() {
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("info").siblings().removeClass("info");
            del(item)
        });

        //点击编辑按钮
        $table.on("click",".btn-edit",function() {
            debugger;
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("info").siblings().removeClass("info");
            edit(item)
        });
    });

    function del(obj) {
        alert("数据已经删除!" + obj.userNo + "   " + obj.userName);
    }

    function edit(obj) {
        alert("数据已经编辑!" + obj.userNo + "   " + obj.userName);
    }
</script>