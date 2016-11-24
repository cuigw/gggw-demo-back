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
                    <h3 class="page-header text-primary"><c:if test="${operatType == '0' }">添加新角色</c:if><c:if test="${operatType == '1' }">修改角色</c:if> </h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div>
                            <ol class="breadcrumb">
							  <li><a href="">系统设置</a></li>
							  <li><a href="#" onclick="toPage(this, '/toRole', '')">角色管理</a></li>
							  <li class="active"><c:if test="${operatType == '0' }">添加新角色</c:if><c:if test="${operatType == '1' }">修改角色</c:if></li>
							</ol>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                	<form role="form" id="roleForm">
                                        <div class="form-group has-feedback">
                                            <label>角色编号</label>
                                            <input class="form-control" placeholder="请输入角色编号" name="roleCode" id="roleCode" valid="NotBlank" value="${baseRole.roleCode}">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <span class="text-danger">（*必填项）</span>
                                        </div>
                                        <div class="form-group has-feedback">
                                            <label>角色名称</label>
                                            <input class="form-control" placeholder="请输入角色名称" name="roleName" id="roleName"  valid="NotBlank" value="${baseRole.roleName}">
                                            <span class="glyphicon  form-control-feedback"></span>
                                            <p class="text-danger">（*必填项）</p>
                                        </div>
                                        <div class="form-group">
                                        	<div><label>请选择可操作权限</label></div>
                                            <div class="zTreeDemoBackground left">
												<ul id="resourceList" class="ztree"></ul>
											</div>
                                        </div>
                                        <div class="form-group">
                                            <div><label>备注:</label></div>
                                            <textarea class="form-control" rows="3" name="memo" id="memo"></textarea>
                                        </div>

										<div hidden>
											<input name="operatType" value="${operatType}" >
										</div>
										
										<div hidden>
											<input name="roleId" value="${baseRole.roleId}" >
										</div>

                                        <button type="button" class="btn btn-primary" id="commit" onclick="getSelect()">提   交</button>
                                        <button type="button" class="btn btn-default" onclick="toPage(this, '/toRole', '')">取   消</button>
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
	$(document).ready(
		function() {
			$.post("${contextPath }/ajaxGetAllResource", "", function(result) {
				$.fn.zTree.init($("#resourceList"),
						CONSTANT.ZTREE.DEFAULT_OPTION, result);
			});
		}
	);
	
	$("#commit").click(function() {
		debugger;
		if (!checkNotBlank($("#roleForm"))) {
			return;
		}

		var url = "${contextPath }/ajaxRoleEdit"
		var params = $("#roleForm").serializeArray();
		var resources = getSelect();
		params.push({"name":"resources","value":resources})
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
	
	function getSelect() {
		var treeObj = $.fn.zTree.getZTreeObj("resourceList");
		var nodes =  treeObj.getCheckedNodes(true);
		var resources="";
		for (i in nodes) {
			if (i == (nodes.length-1)) {
				resources += (nodes[i].id)
			} else {
				resources += (nodes[i].id) + ","
			}		
		}
		return resources;
	}
</script>