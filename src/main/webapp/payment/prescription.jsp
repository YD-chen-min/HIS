<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/24
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处方费用管理</title>
</head>
<body>
<div align="center">
    <input type="number" id="patientId" name="patientId" maxlength="18" >
    <br>
    <button onclick="">查看</button>
</div>
<table id="prescriptionTable"></table>
<div id="prescriptionTb">
</div>
<div id="prescriptionDiag"></div>
<script>
    $(function () {
        $('#prescriptionTable').datagrid({
            url:'prescriptionHis/getPage',
            toolbar:'#prescriptionTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            queryParams:{
                patientId:$('#patientId').val()
            },

            columns:[[
                {field:'id',title:'处方号',width:100,align:'center'},
                {field:'patientId',title:'病人姓名',width:100,align:'center',
                    formatter:function (value,rowData,rowIndex) {
                        var patientName;
                        $.ajax({
                            url:'patient/get',
                            type: 'get',
                            data:{patientId:rowData.patientId},
                            async:false,
                            resultType:'json',
                            success:function (data) {
                                patientName= data.patients[0].name;
                            }
                        });
                        return patientName;
                    }},
                {field:'status',title:'状态',width:100,align:'center',
                    formatter:function (value,rowData,rowIndex) {
                        if(value==-1){
                            return "已取消";
                        }
                        if(value==0){
                            return "已完成";
                        }
                        if(value==1){
                            return "未缴费";
                        }
                        if(value==2){
                            return "已缴费";
                        }
                    }},
                {field:'initialCost',title:'初始费用',width:100,align:'center'},
                {field:'roundingCost',title:'舍入费用',width:100,align:'center'},
                {field:'finalCost',title:'已收取费用',width:100,align:'center'},
                {field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
                    formatter:function(value, rowData, index){

                        var str = '<a href="#"  onclick="settle1('+rowData.status+","+rowData.initialCost+",\'"+rowData.id+'\')" >结算</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                        str += '<a href="#" onclick="refund1('+rowData.status+",\'"+rowData.id+'\')">退费</a>';
                        return str;
                    }}
            ]]
        });
        load();
    });

    function search() {
        load();
    }
    function load() {
        $('#prescriptionTable').form('load');
    }
    function settle1(status,initialCost,id) {
        debugger;
        if(status==1){
            $('#prescriptionDiag').dialog({
                title:'结算',
                href: 'payment/settle1.jsp'+'?id='+id+'&initialCost='+initialCost,
                width:600,
                height:280,
                resizable:true,
                closed:true,
            });
            $('#prescriptionDiag').dialog('open');
        }else{
            alert("已完成，已取消，已缴费的挂号无法进行缴费操作");
        }
    }
    function refund1(status,id) {
        if(status==0||status==1||status==-1){
            alert("已完成，未缴费，已取消的挂号无法进行退费操作")
        }else{
            $.get('payment/prescriptionRefund',{id:id},function (data) {
                $.messager.alert('信息',data.msg,'info');
            },'json');
        }
    }
</script>
</body>
</html>
