<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/22
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加菜单结点</title>
</head>
<body>
<form id="addMenuForm">
    <div style="padding: 20px 0px 0px 50px">
    <label for="id">序号:</label>
    <input class="easyui-validatebox" type="text" name="id" id="id" maxlength="18" data-options="required:true,digits:true" />
    <label for="text">名称:</label>
    <input class="easyui-validatebox" type="text" name="text" id="text" data-options="required:true" />
    <label for="pid">父级序号:</label>
    <input class="easyui-validatebox" type="text" name="pid" id="pid" data-options="required:true,digits:true" />
    <label for="href">路径:</label>
    <input class="easyui-validatebox" type="text" name="href" id="href" data-options="required:false" />
    </div>
    <div align="center" style="padding-top: 20px">
        <button>增加</button>
    </div>
</form>
    <script>
        $(function() {
            $('#addMenuForm').form({
            url:'myNode/insert',
            success:function(data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
            });
        });
    </script>
</body>
</html>
