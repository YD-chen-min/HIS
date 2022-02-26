<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查项目管理</title>
</head>
<body>
<script>
    function getOptionsDept(data) {
        var html="";
        html=html+"<option selected=\"selected\">--请选择--</option>";
        for (var i=0;i<data.length;i++){
            html=html+"<option value='"+data[i].id+"' >"+data[i].name+"</option>";
        }
        return html;
    }
</script>
<table align="center">
    <td>
        <label for="deptId">科室选择:</label>
        <select id="deptId"  name="deptId" style="width:200px;">
            <!--远程加载科室数据(已完成)-->
        </select>
    </td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="projectTable" class="easyui-datagrid" style="width:400px;height:250px">
</table>
<div id="projectTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
</div>
<div id="projectDiag"></div>
<script>
    $(function () {
        $('#projectTable').datagrid({
            url:'project/getPage',
            queryParams:{
                deptId:$('#deptId').val()
            },
            toolbar:'#projectTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex,rowData) {
                $('#projectDiag').dialog({
                    title:'修改检查项目信息',
                    href: 'project/update.jsp?id='+rowData.id+'&name='+rowData.name+'&cost='+rowData.cost,
                    width:1000,
                    height:350,
                    resizable:true,
                    closed:true
                });
                $('#projectDiag').dialog('open');
            },
            columns:[[
                {field:'ck' ,checkbox: true},//复选框
                {field:'id',title:'项目编号',width:100,align:'center'},
                {field:'name',title:'项目名称',width:100,align:'center'},
                {field:'deptId',title:'科室',width:100,align:'center',
                formatter:function (value,rowData,rowIndex) {
                    var name;
                    $.ajax({
                        url:'dept/get/'+value,
                        type:'get',
                        async:false,
                        resultType:'json',
                        success:function (data) {
                            name=data.deptInfos[0].name;
                        }
                    });
                    return name;
                }},
                {field:'cost',title:'价格',width:100,align:'center'},
            ]]
        });
        reload();
        $.get('dept/getAll',{},function (data) {
            $('#deptId').empty();
            $('#deptId').append(getOptionsDept(data));
        },'json');
    });
    function reload() {
        $('#projectTable').datagrid('reload');
    }
    function insert() {
        $('#projectDiag').dialog({
            title:'新添检查项目',
            href: 'project/add.jsp',
            width:1000,
            height:150,
            resizable:true,
            closed:true
        });
        $('#projectDiag').dialog('open');
    }
    function deleteRows() {
        if($('#projectTable').datagrid('getChecked').length>0){
            var rows= $('#projectTable').datagrid('getChecked');
            var ids='';
            for(var i=0;i<rows.length;i++){
                var project=rows[i];
                ids+=','+project.id;
            }
            ids=ids.substring(1,ids.length);
            $.get('project/delete',{
                ids:ids
            },function (data) {
                $.messager.alert('message',data.msg,'info');
                reload();
            },'json');
        }
    }
</script>
</body>
</html>
