<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药品管理</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<table align="center">
    <td>药品编号</td><td><input name="id" id="id" type="text"></td>
    <td>名称</td><td><input name="name"  id="name" type="text"></td>
    <td>种类</td>
    <td>
    <select id="type" class="easyui-combobox" name="type" style="width:200px;">
        <option value="" selected="selected">请选择</option>
        <option value="1">中药</option>
        <option value="2">西药</option>
    </select>
    </td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="medicineTable" class="easyui-datagrid" style="width:400px;height:250px">

</table>
<div id="medicineTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
</div>
<div id="medicineDiag"></div>
<div id="medicineBb" >
    <a href="#" class="easyui-linkbutton" onclick="submit()">Save</a>
    <a href="#" class="easyui-linkbutton" onclick="mclose()">Close</a>
</div>
<script>
    var flag;
    $(function () {
        reload();
    });
    function search() {
        reload();
    };
    function insert() {
        flag=true;
        $('#medicineDiag').dialog({
            title:'添加药品',
            href: 'medicine/add.jsp',
            width:1000,
            height:350,
            resizable:true,
            buttons:$('#medicineBb'),
            closed:true
        });
        $('#medicineDiag').dialog('open');

    }
    function deleteRows() {
        if($('#medicineTable').datagrid('getChecked').length>0){
            var rows= $('#medicineTable').datagrid('getChecked');
            var ids='';
            for(var i=0;i<rows.length;i++){
                var medicine=rows[i];
                ids+=','+medicine.id;
            }
            ids=ids.substring(1,ids.length);
            $.get('medicine/delete',{
                ids:ids
            },function (data) {
                $.messager.alert('message',data.msg,'info');
                reload();
            },'json');
        }
    }
    function submit() {
        //true: insert;  false: update
        if(flag){
            $('#medicineInfoFormInsert').form('submit',{
                url: 'medicine/insert',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                    reload();
                }

            });
        }else{
            $('#medicineInfoFormUpdate').form('submit',{
                url: 'medicine/update',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                    reload();
                }

            });
        }

    }
    function mclose() {
        $('#medicineInfoFormInsert').form('clear');
        $('#medicineInfoFormUpdate').form('clear');
        $('#medicineDiag').dialog('close');
    }

    function reload() {
        $('#medicineTable').datagrid({
            url:'medicine/getPage',
            queryParams:{
                name:$('#name').val(),
                id:$('#id').val(),
                type:$('#type').val()
            },
            toolbar:'#medicineTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex,rowData) {
                //加载药品详细信息页面
                $('#medicineDiag').dialog({
                    title:'修改药品信息',
                    href: 'medicine/update.jsp',
                    width:1000,
                    height:350,
                    resizable:true,
                    buttons:$('#medicineBb'),
                    closed:true
                });
                $('#medicineDiag').dialog('open');
                console.log($('#medicineInfoFormUpdate').length);
                $.get('medicine/get/'+$('#medicineTable').datagrid('getSelected').id,{},function (data) {
                    $('#medicineInfoFormUpdate').form('load',data.medicineInfos[0]);
                },'json');
            } ,
            columns:[[
                {field:'ck' ,checkbox: true},//复选框
                {field:'id',title:'药品编号',width:100,align:'center'},
                {field:'name',title:'药品名称',width:100,align:'center'},
                {field:'type',title:'种类',width:100,align:'center',
                    formatter:function (value) {
                        if(value==1){
                            return "中药";
                        }
                        if(value==2){
                            return "西药";
                        }
                    }},
                {field:'specification',title:'规格',width:100,align:'center'},
                {field:'price',title:'价格',width:100,align:'center'},
                {field:'inventory',title:'库存',width:100,align:'center'},
                {field:'remark',title:'备注',width:100,align:'center'},
                {field:'manufacturer',title:'厂商',width:100,align:'center'},
                {field:'madeDate',title:'生成日期',width:100,align:'center'},
                {field:'buyDate',title:'购入日期',width:100,align:'center'},
            ]]

        });
    }
    function update() {
        flag=false;
        if($('#medicineTable').datagrid('getSelected')==null){
            $.messager.alert('警告','请选择一条数据','warning');
        }else{

            $('#medicineDiag').dialog({
                title:'修改药品信息',
                href: 'medicine/update.jsp',
                width:1000,
                height:350,
                resizable:true,
                buttons:$('#medicineBb'),
                closed:true
            });
            $('#medicineDiag').dialog('open');
            $.get('medicine/get/'+$('#medicineTable').datagrid('getSelected').id,{},function (data) {
                console.log(data[0]);
                $('#medicineInfoFormUpdate').form('load',data[0]);
            },'json');

        }
    }
</script>
</body>
</html>
