<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/22
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
</head>
<body>
    <table id="myNodeTable"></table>
    <div id="menuTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan' ,plain:true"  onclick="deleteRow()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
    </div>
    <div id="menuDiag"></div>
<script>
   $(function() {
        $('#myNodeTable').datagrid({
            url:'myNode/getPage',
            toolbar:'#menuTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function(rowIndex, rowData){
                $('#menuDiag').dialog({
                title:'修改菜单结点',
                href: 'menu/update.jsp?id='+rowData.id+'&text='+rowData.text+'&pid='+rowData.pid+'&href='+rowData.href,
                width:1000,
                height:150,
                resizable:true,
                closed:true
                });
                $('#menuDiag').dialog('open');

            },
            columns:[[
            {field:'id',title:'序号',width:100,align: 'center'},
            {field:'text',title:'菜单名',width:100,align: 'center'},
            {field:'pid',title:'父级序号',width:100,align:'center'},
            {field:'href',title:'路径',width:100,align:'left'},
            ]]
        });
        reload();
    });
    function deleteRow() {
        if($('#myNodeTable').datagrid('getSelected').length>0){
            var row= $('#myNodeTable').datagrid('getSelected');
            var id='';
            id=row.id;
            $.get('myNode/delete',{
            ids:id
            },function (data) {
            $.messager.alert('message',data.msg,'info');
            reload();
            },'json');
        }
    }
    function insert() {
    $('#menuDiag').dialog({
    title:'新添菜单结点',
    href: 'menu/add.jsp',
    width:1000,
    height:150,
    resizable:true,
    closed:true
    });
    $('#menuDiag').dialog('open');
    }
    function reload() {
        $('#myNodeTable').datagrid('reload');
    }
</script>
</body>
</html>
