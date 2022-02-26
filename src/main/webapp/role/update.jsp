<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改角色信息</title>
</head>
<body>
<form id="updateRoleForm">
    <div style="padding: 20px 0px 0px 50px">
        <label for="id">序号:</label>
        <input class="easyui-validatebox" readonly="readonly" required="required"  value="<%=request.getParameter("roleId")%>" type="text" name="id" id="id"   />
        <label for="name">角色名:</label>
        <input class="easyui-validatebox" required="required" type="text" name="name" id="name" value="<%=request.getParameter("roleName")%>" />
    </div>
    <div align="center" style="padding-top: 20px">
        <button>修改</button>
    </div>
</form>
<script>
    $(function () {
        $('#updateRoleForm').form({
            url:'role/updsate',
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });
</script>
</body>
</html>
