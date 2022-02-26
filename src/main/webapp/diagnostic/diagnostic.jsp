<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/18
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>诊断页面</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="registrationForm">
    <div style="padding: 20px 0px 0px 50px">
        <label for="name">姓名</label>
        <input name="name" id="name" type="text" readonly="readonly">
        <label for="age">年龄</label>
        <input name="age" id="age" type="text" readonly="readonly">
        <label for="sex">性别</label>
        <input name="sex" id="sex" type="text" readonly="readonly">
        <label for="id">身份证号码</label>
        <input name="id" id="id" type="text" readonly="readonly">
        <label for="dept">科室</label>
        <input name="dept" id="dept" type="text" readonly="readonly">
        <label for="doctor">医生</label>
        <input name="doctor" id="doctor" type="text" readonly="readonly">
    </div>
</form>
主观资料<hr>
<form id="diagnosticForm">
    <div style="padding: 20px 0px 0px 50px">
        <label for="accidentDate">发病日期</label>
        <input id="accidentDate"  type="text" class="easyui-datebox" required="required">
    </div>
    <div style="padding: 20px 0px 0px 50px">
        <label for="selfReported">主诉</label>
        <textarea id="selfReported" maxlength="255" name="selfReported" required="required"></textarea>
    </div>
    <div style="padding: 20px 0px 0px 50px">
        <label for="medicalHistory">病史</label>
        <textarea id="medicalHistory" maxlength="255" name="medicalHistory"></textarea>
        <label for="allergicHistory">过敏史</label>
        <textarea id="allergicHistory" maxlength="255" name="allergicHistory"></textarea>
    </div>
    处置计划<hr>
    <div style="padding: 20px 0px 0px 50px">
        <label for="conclusion">处置计划</label>
        <textarea id="conclusion" maxlength="255" name="conclusion" required="required"></textarea>
        <label for="remark">医嘱</label>
        <textarea id="remark" maxlength="255" name="remark"></textarea>
    </div>
    <div align="center">
        <button >确认就诊</button>
    </div>
</form>
<script>
</script>
</body>

</html>
