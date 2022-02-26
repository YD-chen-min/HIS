<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录入医生基本信息</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="doctorInfoFormInsert" method="post">
    <div style="padding: 20px 0px 0px 50px">
        <label for="id">身份证号码:</label>
        <input class="easyui-validatebox" type="text" name="id" id="id" maxlength="18" data-options="required:true" />
        <label for="name">姓名:</label>
        <input class="easyui-validatebox" type="text" name="name" id="name" data-options="required:true" />
        <label for="sex">性别</label>
        <input  id="sex" class="easyui-radiobutton" name="sex" value="0" label="男"  labelPosition="after" labelWidth="60px">
        <input class="easyui-radiobutton" name="sex" value="1" label="女" labelPosition="after" labelWidth="60px">
    </div>
    <div style="padding: 20px 0px 0px 50px">
        <label for="workId">工号:</label>
        <input class="easyui-validatebox" type="text" name="workId" id="workId" maxlength="8" data-options="required:true" />
        <label for="job">职位:</label>
        <select id="job" class="easyui-combobox" name="job" style="width:200px;">
            <option value="医生">医生</option>
            <option value="专家">专家</option>
            <option value="护士">护士</option>
        </select>
        <label for="deptIdAdd">部门:</label>
        <select id="deptIdAdd"   name="deptId" style="width:200px;">
<%--           远程加载部门信息--%>
        </select>
    </div>
    <div style="padding: 20px 0px 0px 50px">
        <label for="joinDate">入职日期:</label>
        <input id="joinDate" type="text" class="easyui-datebox" required="required">
        <label for="remark">备注说明:</label>
        <textarea name="remark" id="remark" maxlength="255"></textarea>
    </div>
    <div align="center">
        <button>添加</button>
    </div>
</form>
<script>
    var joinDate;
    $(function () {

        $('#joinDate').datebox({
            onSelect:function (date) {
                joinDate=date.getMonth()+1+'/'+date.getDate()+'/'+date.getFullYear();
            }
        });
        $.get('dept/getAll',{},function (data) {
            $('#deptIdAdd').empty();
            $('#deptIdAdd').append(getOptionsDept(data));
            console.log(getOptionsDept(data));
        },'json');
        $('#doctorInfoFormInsert').form({
            url:'doctor/insert',
            onSubmit:function (param) {
                // if(!checkIdcard($('#id').val())){
                //     alert($('#id').val()+'这不是一个合法的身份证号码');
                //     return false;
                // }
                param.p1=joinDate;
            },
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
                reload();
            }
        });
    });
</script>
</body>
</html>
