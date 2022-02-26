<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录入科室基本信息</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="deptInfoFormInsert" method="post">
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
        <button>添加</button>
    </div>
</form>
<script>
    $(function () {
        $('#deptInfoFormInsert').form({
            url:'dept/insert',
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });
</script>
</body>
</html>
