<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/22
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
</head>
<body>
<table id="roleTable"></table>
<div id="roleTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan' ,plain:true"  onclick="deleteRows()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
</div>
<div id="roleDiag"></div>
<script>
    $(function () {
        $('#roleTable').datagrid({
            url:'role/getPage',
            toolbar:'#roleTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:false,
            onDblClickRow:function(rowIndex, rowData){
                // $('#menuDiag').dialog({
                //     title:'修改菜单结点',
                //     href: 'menu/update.jsp?id='+rowData.id+'&text='+rowData.text+'&pid='+rowData.pid+'&href='+rowData.href,
                //     width:1000,
                //     height:150,
                //     resizable:true,
                //     closed:true
                // });
                // $('#menuDiag').dialog('open');

            },
            columns:[[
                {field: 'ck', checkbox:true},
                {field:'id',title:'序号',width:100,align: 'center'},
                {field:'name',title:'角色名',width:100,align: 'center'},
                {field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
                    formatter:function(value, rowData, index){
                        var str = '<a href="#"  onclick="updateRole(\''+rowData.id+'\',\''+rowData.name+'\')" >修改信息</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                         str += '<a href="#" onclick="updatePower(\''+rowData.id+'\')">修改权限</a>';
                        return str;
                    }}
            ]]
        });
    });
    function deleteRows() {
        var roles=$('#roleTable').datagrid('getChecked');
        var ids='';
        for (var i=0;i<roles.length;i++){
            ids+=','+roles[i].id;
        }
        $.get('role/delete/'+ids,{},function (data) {
            $.messager.alert('信息',data.msg,'info');
        },'json');
    }
    function insert() {
        $('#roleDiag').dialog({
            title:'新添角色',
            href:'role/add.jsp',
            width:500,
            height:150,
            resizable:true,
            closed:true
        });
        $('#roleDiag').dialog('open');
    }
    function updateRole(roleId,roleName) {
        $('#roleDiag').dialog({
            title:'修改角色信息',
            href:'role/update.jsp?roleId='+roleId+'&roleName='+roleName,
            width:500,
            height:150,
            resizable:true,
            closed:true
        });
        $('#roleDiag').dialog('open');
    }
    function updatePower(roleId) {
        $('#roleDiag').dialog({
            title:'修改权限信息',
            href:'role/power.jsp?roleId='+roleId,
            width:1000,
            height:600,
            resizable:true,
            closed:true
        });
        $('#roleDiag').dialog('open');
    }
</script>
</body>
</html>
