<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- DataTables CSS -->
<link href="${contextPath }/vendor/datatables/dataTables.bootstrap.css" rel="stylesheet">
<link href="${contextPath }/vendor/datatables/dataTables.responsive.css" rel="stylesheet">
<!-- DataTables JavaScript -->
<script src="${contextPath }/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="${contextPath }/vendor/datatables/dataTables.bootstrap.min.js"></script>
<script src="${contextPath }/vendor/datatables/dataTables.responsive.js"></script>

<!-- page-wrapper  start -->

		<div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header text-primary">添加新用户</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div>
                            <ol class="breadcrumb">
							  <li><a href="#">系统设置</a></li>
							  <li><a href="#" onclick="toPage(this, '/toUser', '')">用户管理</a></li>
							  <li class="active">添加用户</li>
							</ol>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="userForm">
                                        <div class="form-group has-feedback">
                                            <label>用户编号</label>
                                            <input class="form-control" placeholder="请输入用户编号" name="userNo">
                                            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
                                            <span class="text-danger">（*必填项）</span>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>密码</label>
                                            <input type="password"   class="form-control" placeholder="请输入密码" name="userPwd">
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>重复密码</label>
                                            <input type="password"  class="form-control" placeholder="请重新输入密码">
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>姓名</label>
                                            <input class="form-control" placeholder="请输入姓名" name="userName">
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>手机号码</label>
                                            <input class="form-control" placeholder="请输入手机号码" name="mobile">
                                        </div>

                                        <div class="form-group has-feedback">
                                            <label>邮箱</label>
                                            <input class="form-control" placeholder="请输入邮箱" name="email">
                                        </div>
                                        <div class="form-group has-feedback">
                                            <div><label>请选择角色</label></div>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">系统管理员
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">操作员
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">用户
                                            </label>
                                        </div>



                                        <button type="button" class="btn btn-primary" id="commit">提   交</button>
                                        <button type="button" class="btn btn-default">取   消</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
         

<!-- page-wrapper   end -->

<script>
    $("#commit").click(function() {
        debugger;
        var url = "${contextPath }/ajaxUserEdit"
        var params = $("#userForm").serializeArray();
        $.getJSON(url, params, function(data) {
           alert(data);
        });
    });
</script>