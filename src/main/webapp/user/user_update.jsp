<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/5/1
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="userForm" method="post" style="align-content: center; text-align: center">
    <div>
        <label for="id">账号</label>
        <input class="easyui-validatebox" type="text" name="id" id="id"   height="28px" readonly="true"/>
    </div>
    <p></p>
    <div>
        <label for="name">用户名</label>
        <input class="easyui-validatebox" type="text" name="name" id="name"   height="28px"/>
    </div>
    <p></p>
    <div>
        <label for="roleId">权限</label>
        <input id="roleId" name="roleId">
    </div>
    <p></p>
</form>
</body>
</html>
