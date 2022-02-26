<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择检查项目</title>
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
<div align="center">
    <table   width="100%">
        <td>
            <label for="deptId">科室选择:</label>
            <select id="deptId"  name="deptId" style="width:200px;">
                <!--远程加载科室数据(已完成)-->
            </select>
        </td>
        <td><button onclick="search()">查找</button></td>
    </table>
</div>
<table  id="projectTable1" class="easyui-datagrid" style="width:400px;height:250px">
</table>
<div id="projectTb1">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok' ,plain:true"  onclick="select()"></a>
</div>
<script>
    $(function () {
        reload();
        $.get('dept/getAll',{},function (data) {
            $('#deptId').empty();
            $('#deptId').append(getOptionsDept(data));
        },'json');
    });
    function search() {
        reload();
    }
    function reload() {
        $('#projectTable1').datagrid({
            url:'project/getPage',
            queryParams:{
                deptId:$('#deptId').val()
            },
            toolbar:'#projectTb1',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:false,
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
    }
    function select() {
        var projects=$('#projectTable1').datagrid('getChecked');
        var counts=projects.length;
        for(var i=0;i<counts;i++){
            $('#projectTable').append(getHtml(projects[i],i));
        }
        $('#projectDiag').dialog('close');
    }
    function getHtml(project,index) {
        var html="";
        html+="<tr>";
        html+="<td><input name='id"+index+"' type='text' readonly='readonly' value='"+project.id+"'></td>";
        html+="<td><input name='name"+index+"' type='text' readonly='readonly' value='"+project.name+"'></td>";
        html+="<td><input id='deptId"+index+"'  name='deptId"+index+"' type='text'  readonly='readonly' value='"+project.deptId+"'></td>";
        html+="<td><input id='cost"+index+"' name='cost"+index+"'  type=\"number\"   readonly='readonly' value='"+project.cost+"'></td>";
        html+="</tr>";
        console.log(html);
        return html;

    }
</script>
</body>
</html>
