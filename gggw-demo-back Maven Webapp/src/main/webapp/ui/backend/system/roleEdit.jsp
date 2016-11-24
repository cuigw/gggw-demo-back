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
                    <h3 class="page-header text-primary"><c:if test="${operatType == '0' }">添加新用户</c:if><c:if test="${operatType == '1' }">修改用户</c:if> </h3>
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
							  <li><a href="#" onclick="toPage(this, '/toUser', '')">用户管理</a></li>
							  <li class="active"><c:if test="${operatType == '0' }">添加新用户</c:if><c:if test="${operatType == '1' }">修改用户</c:if></li>
							</ol>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="zTreeDemoBackground left">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
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

var setting = {
			check: {
						enable: true
					},
			data: {
						simpleData: {
							enable: true
						}
					},
			view: {
					showIcon: false
					}
		};

		var zNodes =[
			
			{ id:1, pId:0, name:"随意勾选 1"},
				{ id:11, pId:1, name:"随意勾选 1-1"},
					{ id:111, pId:11, name:"随意勾选 1-1-1"},
					{ id:112, pId:11, name:"随意勾选 1-1-2"},
				{ id:"12", pId:1, name:"随意勾选 1-2"},
					{ id:121, pId:12, name:"随意勾选 1-2-1"},
					{ id:122, pId:"12", name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2"},
				{ id:21, pId:2, name:"随意勾选 2-1"},
				{ id:22, pId:2, name:"随意勾选 2-2"},
					{ id:221, pId:22, name:"随意勾选 2-2-1"},
					{ id:222, pId:22, name:"随意勾选 2-2-2"},
				{ id:23, pId:2, name:"随意勾选 2-3"}
			/**
			{"name":"网站导航", open:true, children: [
			{ "name":"google", "url":"http://g.cn", "target":"_blank"},
			{ "name":"baidu", "url":"http://baidu.com", "target":"_blank"},
			{ "name":"sina", "url":"http://www.sina.com.cn", "target":"_blank"}
			]
			}*/
		];
		

$(document).ready(function(){
        $.post("${contextPath }/getAllResource", "", function(result){
            debugger;
            $.fn.zTree.init($("#treeDemo"), setting, result);
        })

		});
</script>