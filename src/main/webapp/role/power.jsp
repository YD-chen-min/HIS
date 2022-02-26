<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理</title>
</head>
<body>
<table id="powerTable"></table>
<div id="powerTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan' ,plain:true"  onclick="deleteRow()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
</div>
<div id="powerDiag"></div>
<script>
    $(function() {
        $('#powerTable').datagrid({
            url:'myNode/getUserNodes?roleId=<%=request.getParameter("roleId")%>',
            toolbar:'#powerTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            columns:[[
                {field:'id',title:'序号',width:100,align: 'center'},
                {field:'text',title:'菜单名',width:100,align: 'center'},
                {field:'pid',title:'父级序号',width:100,align:'center'},
                {field:'href',title:'路径',width:100,align:'left'},
            ]]
        });
        reload();
    });
    function reload() {
        $('#powerTable').datagrid('reload');
    }
    function deleteRow() {
        var node=$('#powerTable').datagrid('getSelected');
        if(node!=null){
            $.get('role/removeRolePower',{nodeId:node.id,roleId:'<%=request.getParameter("roleId")%>'},function (data) {
                $.messager.alert('信息',data.msg,'info');
            },'json');
        }
    }
    function insert() {
        $('#powerDiag').dialog({
            title:'选择权限',
            href: 'menu/select.jsp?roleId=<%=request.getParameter("roleId")%>',
            width:1000,
            height:600,
            resizable:true,
            closed:true
        });
        $('#powerDiag').dialog('open');
    }
</script>
</body>
</html>
