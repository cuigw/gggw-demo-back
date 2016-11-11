<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
    	<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Yeah~~~~</title>
		<!-- Bootstrap Core CSS -->
		<link href="${contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">		
		<!-- MetisMenu CSS 
		<link href="${contextPath }/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">	-->	
		<!-- Custom CSS -->
		<link href="${contextPath }/vendor/css/sb-admin-2.css" rel="stylesheet">		
		<!-- Morris Charts CSS 
		<link href="${contextPath }/vendor/morrisjs/morris.css" rel="stylesheet">-->	
		<!-- Custom Fonts -->
		<link href="${contextPath }/vendor/font-awesome/css/font-awesome.min.css"
					rel="stylesheet" type="text/css">
		
		<!-- jQuery -->
		<script src="${contextPath }/vendor/jquery/jquery.min.js"></script>		
		<!-- Bootstrap Core JavaScript -->
		<script src="${contextPath }/vendor/bootstrap/js/bootstrap.min.js"></script>		
		<!-- Metis Menu Plugin JavaScript 
		<script src="${contextPath }/vendor/metisMenu/metisMenu.min.js"></script>	-->	
		<!-- Morris Charts JavaScript 
		    <script src="../vendor/raphael/raphael.min.js"></script>
		    <script src="../vendor/morrisjs/morris.min.js"></script>
		    <script src="../data/morris-data.js"></script>-->		
		<!-- Custom Theme JavaScript -->
		<script src="${contextPath }/vendor/js/sb-admin-2.js"></script>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-primary">
                    <div class="panel-heading text-center">
                        <h3 class="panel-title">登 录</h3>
                    </div>
                    <div class="panel-body">

                        <h3 class="page-header text-center text-info" style="margin-top: 0px">请输入用户名密码</h3>

                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <a href="index.html" class="btn btn-primary pull-right">Login</a>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                    <div class="panel-footer">
                        <a href="index.html" class=""><i class="fa fa-arrow-left fa-fw"></i> 忘记密码</a>
                        <a href="index.html" class="pull-right">现在注册 <i class="fa fa-arrow-right fa-fw"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
