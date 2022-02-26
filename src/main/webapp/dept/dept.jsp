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
    <title>科室管理</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<table align="center">
    <td>科室编号</td><td><input name="id" id="id" type="text"></td>
    <td>科室名称</td><td><input name="name"  id="name" type="text"></td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="deptTable" class="easyui-datagrid" style="width:400px;height:250px">

</table>
<div id="deptTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
</div>
<div id="deptDiag"></div>
<div id="deptBb" >
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
        $('#deptDiag').dialog({
            title:'添加科室',
            href: 'dept/add.jsp',
            width:600,
            height:280,
            resizable:true,
            buttons:$('#deptBb'),
            closed:true
        });
        $('#deptDiag').dialog('open');

    }
    function deleteRows() {
        if($('#deptTable').datagrid('getChecked').length>0){
            var rows= $('#deptTable').datagrid('getChecked');
            var ids='';
            for(var i=0;i<rows.length;i++){
                var dept=rows[i];
                ids+=','+dept.id;
            }
            ids=ids.substring(1,ids.length);
            $.get('dept/delete',{
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
            $('#deptInfoFormInsert').form('submit',{
                url: 'dept/insert',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                    reload();
                }

            });
        }else{
            $('#deptInfoFormUpdate').form('submit',{
                url: 'dept/update',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                    reload();
                }

            });
        }

    }
    function mclose() {
        $('#deptInfoFormInsert').form('clear');
        $('#deptInfoFormUpdate').form('clear');
        $('#deptDiag').dialog('close');
    }

    function reload() {
        $('#deptTable').datagrid({
            url:'dept/getPage',
            queryParams:{
                name:$('#name').val(),
                id:$('#id').val()
            },
            toolbar:'#deptTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex, rowData) {
                //加载病人详细信息页面
                $('#deptDiag').dialog({
                    title:'修改科室信息',
                    href: 'dept/update.jsp',
                    width:600,
                    height:280,
                    resizable:true,
                    buttons:$('#deptBb'),
                    closed:true
                });
                $('#deptDiag').dialog('open');
                console.log($('#deptInfoFormUpdate').length);
                $.get('dept/get/'+$('#deptTable').datagrid('getSelected').id,{},function (data) {
                    $('#deptInfoFormUpdate').form('load',data.deptInfos[0]);
                },'json');
            } ,
            columns:[[
                {field:'ck' ,checkbox: true},//复选框
                {field:'id',title:'科室编号',width:100,align:'center'},
                {field:'name',title:'科室名称',width:100,align:'center'},
                {field:'remark',title:'备注说明',width:100,align:'center'},
            ]]

        });
    }
    function update() {
        flag=false;
        if($('#deptTable').datagrid('getSelected')==null){
            $.messager.alert('警告','请选择一条数据','warning');
        }else{

            $('#deptDiag').dialog({
                title:'修改科室信息',
                href: 'dept/update.jsp',
                width:600,
                height:280,
                resizable:true,
                buttons:$('#deptBb'),
                closed:true
            });
            $('#deptDiag').dialog('open');
            console.log($('#deptInfoFormUpdate').length);
            $.get('dept/get/'+$('#deptTable').datagrid('getSelected').id,{},function (data) {
                $('#deptInfoFormUpdate').form('load',data.deptInfos[0]);
            },'json');

        }
    }
</script>
</body>
</html>
