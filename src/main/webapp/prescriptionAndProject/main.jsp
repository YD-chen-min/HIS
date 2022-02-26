<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/19
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处方和检查项目</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<%
    String registrationId=request.getParameter("registrationId");
    String diagnosticId=request.getParameter("diagnosticId");
%>

<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
    <div id="PPmianTabs" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true,">
    </div>
</div>
<script>
    $(function () {
        $('#PPmianTabs').tabs({
            fit:true
        });
        $('#PPmianTabs').tabs('add',{
            title:'处方信息',
            selected:true,
            href:'prescriptionAndProject/prescription.jsp?registrationId='+'<%=registrationId%>&diagnosticId=<%=diagnosticId%>',
            closable:false,
        });
        $('#PPmianTabs').tabs('add',{
            title:'检查项目信息',
            selected:true,
            href:'prescriptionAndProject/project.jsp?registrationId='+'<%=registrationId%>&diagnosticId=<%=diagnosticId%>',
            closable:false,
        });
    });
</script>
</body>
</html>
