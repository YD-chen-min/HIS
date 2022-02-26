<%@ page import="com.yandan.model.User" %><%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/5/7
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息修改</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<%
    User user=(User)request.getSession().getAttribute("user");
%>
<div align="center">
    <form id="puserForm" method="post" style="align-content: center; text-align: center">
        <div>
            <label for="id">账&nbsp&nbsp&nbsp号</label>
            <input class="easyui-validatebox" type="text" name="id" id="id"   height="22px" readonly="true"/>
        </div>
        <p></p>
        <div>
            <label for="name">用户名</label>
            <input class="easyui-validatebox" type="text" name="name" id="name"   height="22px"/>
        </div>
        <p></p>
        <div>
            <label for="password">密&nbsp&nbsp&nbsp码</label>
            <input class="easyui-passwordbox" id="password" name="password" prompt="Password" iconWidth="28" style="height:22px">
        </div>
        <p></p>
    </form>
    <button onclick="update()">修改</button>
</div>
<script>
    $(function () {
       $('#puserForm').form('load',{
           id:"<%=user.getId()%>",
           name:"<%=user.getName()%>",
           password:"<%=user.getPassword()%>"
       })
    });
    function update() {
            $('#puserForm').form('submit',{
               url:'user/UserUpdate',
               method:'post',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                }
            });
    }
</script>
</body>
</html>
