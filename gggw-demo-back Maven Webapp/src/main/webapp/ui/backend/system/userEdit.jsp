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

<script src="${contextPath }/vendor/js/validate.js"></script>


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
                                            <input class="form-control" placeholder="请输入用户编号" name="userNo" id="userNo" valid="NotBlank">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <span class="text-danger">（*必填项）</span>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>密码</label>
                                            <input type="password"   class="form-control" placeholder="请输入密码" name="userPwd" id="userPwd"  valid="NotBlank">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>重复密码</label>
                                            <input type="password"  class="form-control" placeholder="请重复输入密码" name="reUserPwd" id="reUserPwd" valid="NotBlank">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>姓名</label>
                                            <input class="form-control" placeholder="请输入姓名" name="userName" id="userName"  valid="NotBlank">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group" >
                                            <label>性别</label>
                                            <select class="form-control" name="gender" id="gender">
                                                <option>男</option>
                                                <option>女</option>
                                            </select>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>手机号码</label>
                                            <input class="form-control" placeholder="请输入手机号码" name="mobile" id="mobile"  valid="NotBlank">
                                        </div>

                                        <div class="form-group has-feedback">
                                            <label>邮箱</label>
                                            <input class="form-control" placeholder="请输入邮箱" name="email" id="email"  valid="NotBlank">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group">
                                            <label>状态</label>
                                            <select class="form-control" name="status" id="status">
                                                <option>正常</option>
                                                <option>停用</option>
                                                <option>注销</option>
                                            </select>
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

										<div hidden>
											<input name="operatType" value="${operatType}" >
										</div>

                                        <button type="button" class="btn btn-primary" id="commit">提   交</button>
                                        <button type="button" class="btn btn-default" onclick="toPage(this, '/toUser', '')">取   消</button>
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

			<!-- Modal
			<div class="modal fade" id="errorModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			        <h4 class="modal-title" id="errorModelTitle">温馨提示</h4>
			      </div>
			      <div class="modal-body text-danger" id="errorModelBody">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			         <button type="button" class="btn btn-primary">Save changes</button>
			      </div>
			    </div>
			  </div>
			</div>-->

<!-- page-wrapper   end -->

<script>
	$("#commit").click(function() {
		debugger;
		
		var email = $("#email").val();
		var userPwd = $("#userPwd").val();
		var reUserPwd = $("#reUserPwd").val();

		if (!checkNotBlank($("#userForm"))) {
			return;
		}

		if (userPwd != reUserPwd) {
			checkErrorHandle($("#reUserPwd"));
			showError("两次密码输入不一致！");
			return;
		}

		if (!(validateEmail(email) == "success")) {
			checkErrorHandle($("#email"));
			showError(validateEmail(email));
			return;
		}

		var url = "${contextPath }/ajaxUserEdit"
		var params = $("#userForm").serializeArray();

        $.ajax({
           type : "post" ,
           data : params,
           url : url,
           success: function(data) {
               if (data.error_no == "0") {
                   showError(data.error_info);
               } else {
                   showError(data.error_info);
               }
           }
        });

	});
</script>