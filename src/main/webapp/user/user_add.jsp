<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>insert</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="userForm" method="post" style="align-content: center; text-align: center">
    <div>
        <label for="id">账号</label>
        <input type="text" class="easyui-numberbox"  id="id" name="id"  data-options="min:0,precision:0">
    </div>
    <p></p>
    <div>
        <label for="name">用户名</label>
        <input class="easyui-validatebox" type="text" name="name" id="name"   height="28px"/>
    </div>
    <p></p>
    <div>
        <label for="password">密码</label>
        <input class="easyui-passwordbox" prompt="Password" iconWidth="28" id="password" name="password" style="height:28px;padding:10px">
    </div>
    <p></p>
    <div>
        <label for="roleId">权限</label>
        <input id="roleId" name="roleId">
    </div>
</form>

<script>
 $(function () {
        $('#roleId').combobox({
            url:"role/get",
            valueField:'id',
            textField:'name'
        });
 });
</script>
</body>
</html>
