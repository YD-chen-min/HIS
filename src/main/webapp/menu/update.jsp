<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/22
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改菜单结点</title>
</head>
<body>
<form id="updateMenuForm">
<div style="padding: 20px 0px 0px 50px">
<label for="id">序号:</label>
<input required="required" type="number" name="id" id="id" value="<%=request.getParameter("id")%>"  />
<label for="text">名称:</label>
<input required="required" type="text" name="text" id="text"  value="<%=request.getParameter("text")%>" />
<label for="pid">父级序号:</label>
<input  type="number" name="pid" id="pid" required="required"  value="<%=request.getParameter("pid")%>" />
<label for="href">路径:</label>
<input  type="text" name="href" id="href"   value="<%=request.getParameter("href")%>" />
</div>
<div align="center" style="padding-top: 20px">
<button>修改</button>
</div>
</form>
<script>
$(function() {
    $('#updateMenuForm').form({
        url:'myNode/update',
        success:function(data) {
            data=JSON.parse(data);
            $.messager.alert('信息',data.msg,'info');
    }
    });
 });
</script>
</body>
</html>
