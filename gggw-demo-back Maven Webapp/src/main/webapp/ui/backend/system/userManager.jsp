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
    	getDataTables();  	 
    });
    
    function getDataTables(){
    	 // API: http://datatables.club/reference/option/   ； http://datatables.club/manual/server-side.html
    	 // fork from  http://www.jb51.net/article/84751.htm  服务器分页显示     http://blog.csdn.net/angelvyvyan/article/details/51783272
         //提示信息
		 var lang = {
		  "sProcessing": "处理中...",
		  "sLengthMenu": "每页 _MENU_ 条",
		  "sZeroRecords": "没有匹配结果",
		  "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
		  "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
		  "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
		  "sInfoPostFix": "",
		  "sSearch": "搜索:",
		  "sUrl": "",
		  "sEmptyTable": "表中数据为空",
		  "sLoadingRecords": "载入中...",
		  "sInfoThousands": ",",
		  "oPaginate": {
		  "sFirst": "首页",
		  "sPrevious": "上页",
		  "sNext": "下页",
		  "sLast": "末页",
		  "sJump": "跳转"
		  },
		  "oAria": {
		  "sSortAscending": ": 以升序排列此列",
		  "sSortDescending": ": 以降序排列此列"
		  }
		 };
		 
        $('#userList').DataTable({
        	language:lang, //提示信息
        	stripeClasses: ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        	serverSide: true, //启用服务器端分页
        	renderer: "bootstrap",//渲染样式：Bootstrap和jquery-ui
        	searching: true, //禁用原生搜索
  			orderMulti: false, //启用多列排序
        	order: [], //取消默认排序查询,否则复选框一列会出现小箭头
        	ordering: false,
        	pagingType: "full_numbers", //分页样式：simple,simple_numbers,full,full_numbers
        	columnDefs: [{
			  "targets": "nosort", //列的样式名
			  "orderable": false //包含上样式名‘nosort'的禁止排序
			  }],
            responsive: true,
            ajax: {
		        url: "${contextPath }/ajaxUserList",
		        type: "POST",
		        dataType : "json",
		    }
        });
    }
</script>