<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/16
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择病人信息</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<button onclick="getPatient()">确认</button>
<table id="patientTableSelect">
<%--    远程加载病人信息--%>
</table>
<script>
    $(function () {
        $('#patientTableSelect').datagrid({
            url:'patient/getPage',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:false,
            singleSelect:true,
            queryParams:{
                id:$('#patientId').val(),
                name:$('#patientName').val()
            },
            columns:[[
                {field:'id',title:'身份证号码',width:100,align:'center'},
                {field:'cardId',title:'卡号',width:100,align:'center'},
                {field:'name',title:'姓名',width:100,align:'center'},
                {field:'sex',title:'性别',width:100,align:'center',
                    formatter:function (value,row,index) {
                        if(value==0){
                            return '男';
                        }else{
                            return '女';
                        }
                    }},
                {field:'age',title:'年龄',width:100,align:'center'},
                {field:'birth',title:'出生日期',width:100,align:'center'},
            ]]

        });
    });
    function getPatient() {
        var patientInfo=$('#patientTableSelect').datagrid('getSelected');
       if(patientInfo==null){
           $.messager.alert('警告','请至少选择一条信息','warning');
       }else{
           $('#name').val(patientInfo.name);
           $('#sex').val(patientInfo.sex);
           $('#age').val(patientInfo.age);
           $('#birth').val(patientInfo.birth);
           $('#cardId').val(patientInfo.cardId);
           $('#patientId').val(patientInfo.id);
           $('#patientName').val(patientInfo.name);
           $('#dg').datagrid('load',{
               patientId: patients[0].id
           });
       }
    }
</script>
</body>
</html>
