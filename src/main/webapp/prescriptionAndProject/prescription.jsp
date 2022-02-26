<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/19
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处方信息</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<%String registrationId=request.getParameter("registrationId");%>
<form id="registrationForm">
    <div  align="left"  style="padding: 20px 0px 0px 50px">
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
<form id="prescriptionForm">
    <table id="prescriptionTable" width="100%">
        <thead>
        <td>药品编号</td>
        <td>药品名称</td>
        <td>规格</td>
        <td>单价</td>
        <td>数量</td>
        <td>医嘱</td>
        </thead>
        <tbody id="Tbody">

        </tbody>
    </table>
</form>
<div align="center">
    <button onclick="addPrescription()">添加处方</button>
    <button onclick="submit2()">确认</button>
</div>
<div id="prescriptionDiag"></div>
<script>
    $(function () {
        $.get('registrationInfo/getInfo',{registrationId:'<%=registrationId%>'},function (data) {
            $('#registrationForm').form('load',data.readOnlyDate);
            },'json');
        $('#prescriptionForm').form({
            url:'prescription/insert',
            onSubmit:function (param) {
                param.p1=$('#Tbody').find('tr').length;
                param.p2='<%=request.getParameter("diagnosticId")%>';
            },
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });
    function addPrescription() {
        $('#prescriptionDiag').dialog({
            title:'选择药品',
            href: 'medicine/select.jsp',
            width:1000,
            height:600,
            resizable:true,
            closed:true
        });
        $('#prescriptionDiag').dialog('open');
    }
    function submit2() {
        $('#prescriptionForm').submit();
    }
</script>
</body>
</html>
