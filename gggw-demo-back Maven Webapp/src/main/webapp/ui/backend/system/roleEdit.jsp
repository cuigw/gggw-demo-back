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
				view: {                  
						showIcon: false  //隐藏icon 
					},              
				data: {                  
					key: {                      
						children: "setSubArea"  //更加json设置子节点的名称json格式                 
 						}              
 					},              
 					check : {                  
 						enable: true,                  
 						autoCheckTrigger: false,                  
 						chkboxType: { "Y": "p", "N": "s" }                  
 					}              //每个节点上是否显示 CheckBox            
 				};  
</script>