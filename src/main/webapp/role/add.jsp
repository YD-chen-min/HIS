<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新添角色</title>
</head>
<body>
<form id="addRoleForm">
    <div style="padding: 20px 0px 0px 50px">
        <label for="id">序号:</label>
        <input class="easyui-validatebox" type="text" name="id" id="id" maxlength="18" data-options="required:true,digits:true" />
        <label for="name">角色名:</label>
        <input class="easyui-validatebox" type="text" name="name" id="name" data-options="required:true" />
    </div>
    <div align="center" style="padding-top: 20px">
        <button>增加</button>
    </div>
</form>
<script>
    $(function() {
        $('#addRoleForm').form({
            url:'role/insert',
            success:function(data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });
</script>
</body>
</html>
