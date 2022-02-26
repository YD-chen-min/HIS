<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/23
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改检查项目</title>
</head>
<body>
<form id="updateProjectForm">
    <table width="100%">
        <tr>
            <td>序号<input type="number" maxlength="6" required="required" name="id" value="<%=request.getParameter("id")%>"></td>
            <td>名称<input type="text" maxlength="20" required="required" name="name" value="<%=request.getParameter("name")%>"></td>
            <td> <label for="deptId2">科室选择:</label>
                <select id="deptId2"  name="deptId" style="width:200px;">
                    <!--远程加载科室数据(已完成)-->
                </select>
            </td>
            <td>费用<input type="number" required="required" step="0.01" name="cost" value="<%=request.getParameter("cost")%>"></td>
        </tr>
    </table>
    <br>
    <div align="center"><button>修改</button></div>
</form>
<script>
    $(function () {
        $('#updateProjectForm').form({
            url:'project/update',
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
                reload();
            }
        });
        $.get('dept/getAll',{},function (data) {
            $('#deptId2').empty();
            $('#deptId2').append(getOptionsDept(data));
        },'json');
    });
</script>
</body>
</html>
