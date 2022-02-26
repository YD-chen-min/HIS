<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查项目信息</title>
</head>
<body>
<form id="registrationForm">
    <div>
        <label for="name">姓名</label>
        <input name="name" id="name" type="text" readonly="readonly">
        <label for="age">年龄</label>
        <input name="age" id="age" type="text" readonly="readonly">
        <label for="sex">性别</label>
        <input name="sex" id="sex" type="text" readonly="readonly">
        <label for="id">身份证号</label>
        <input name="id" id="id" type="text" readonly="readonly">
        <label for="dept">科室</label>
        <input name="dept" id="dept" type="text" readonly="readonly">
        <label for="doctor">医生</label>
        <input name="doctor" id="doctor" type="text" readonly="readonly">
    </div>
</form>
<form id="projectForm">
    <table id="projectTable" width="100%">
        <thead>
        <td>项目编号</td>
        <td>项目名称</td>
        <td>价格</td>
        </thead>
        <tbody id="Tbody1"></tbody>
    </table>
</form>
<div align="center">
    <button onclick="addProject()">添加项目</button>
    <button onclick="submit1()">确认</button>
</div>
<div id="projectDiag"></div>
<script>
    $(function () {
        $('#projectForm').form({
            url:'projectInfo/insert?diagnosticId=&registrationId=',
            onSubmit:function (param) {
                param.p1=$('#Tbody1').find('tr').length;
                param.p2='<%=request.getParameter("diagnosticId")%>';
                param.p3='<%=request.getParameter("registrationId")%>';
            },
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });
    function addProject() {
        $('#projectDiag').dialog({
            title:'选择检查项目',
            href: 'project/select.jsp',
            width:1000,
            height:600,
            resizable:true,
            closed:true
        });
        $('#projectDiag').dialog('open');
    }
    function submit1() {
        $('#projectForm').submit();
    }
</script>
</body>
</html>
