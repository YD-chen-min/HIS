<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择权限结点</title>
</head>
<body>
<table id="selectNodeTable"></table>
<div id="selectTb">
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok' ,plain:true"  onclick="select()"></a>
</div>
<script>
    $(function() {
        $('#selectNodeTable').datagrid({
            url:'myNode/getPage',
            toolbar:'#selectTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:false,
            columns:[[
                {field: 'ck',checkbox:true},
                {field:'id',title:'序号',width:100,align: 'center'},
                {field:'text',title:'菜单名',width:100,align: 'center'},
                {field:'pid',title:'父级序号',width:100,align:'center'},
                {field:'href',title:'路径',width:100,align:'left'},
            ]]
        });
    reload();
    });
    function reload() {
        $('#selectNodeTable').datagrid('reload');
    }
    function select() {
        var nodes=$('#selectNodeTable').datagrid('getChecked');
        var nodeIds='';
        for(var i=0;i<nodes.length;i++){
                nodeIds+=','+nodes[i].id;
         }
        nodeIds=nodeIds.substring(1,nodeIds.length);
        $.get('role/addRolePower',{roleId:'<%=request.getParameter("roleId")%>',nodeIds:nodeIds},function(data) {
            $.messager.alert('信息',data.msg,'info');
        },'json');
    }
</script>
</body>
</html>
