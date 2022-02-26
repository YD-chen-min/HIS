<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新添检查项目</title>
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
<form id="addProjectForm">
    <table width="100%">
        <tr>
            <td>序号<input type="number" maxlength="6" required="required" name="id"></td>
            <td>名称<input type="text" maxlength="20" required="required" name="name"></td>
            <td> <label for="deptId1">科室选择:</label>
                <select id="deptId1"  name="deptId" style="width:200px;">
                    <!--远程加载科室数据(已完成)-->
                </select>
            </td>
            <td>费用<input type="number" maxlength="6" required="required" step="0.01" name="cost"></td>
        </tr>
    </table>
    <br>
    <div align="center"><button>添加</button></div>
</form>
<script>
    $(function () {
        $('#addProjectForm').form({
            url:'project/insert',
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
                reload();
            }
        });
        $.get('dept/getAll',{},function (data) {
            $('#deptId1').empty();
            $('#deptId1').append(getOptionsDept(data));
        },'json');
    });
</script>
</body>
</html>
