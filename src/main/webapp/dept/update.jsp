<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改科室信息</title>
</head>
<body>
<form id="deptInfoFormUpdate" method="post">
    <div style="padding: 20px 0px 0px 50px">
        <label for="id">科室编号:</label>
        <input class="easyui-validatebox" type="text" name="id" id="id" maxlength="4" data-options="required:true" />
        <label for="name">科室名称:</label>
        <input class="easyui-validatebox" type="text" name="name" id="name" data-options="required:true" />
    </div>
    <div style="padding: 20px 0px 0px 50px">
        <label for="remark">备注说明:</label>
        <textarea name="remark" id="remark" maxlength="255"></textarea>
    </div>
    <div align="center">
        <button>修改</button>
    </div>
</form>
<script>
    $(function () {
        $('#deptInfoFormUpdate').form({
            url:'dept/update',
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
                reload();
            }
        });
    });
</script>
</body>
</html>
