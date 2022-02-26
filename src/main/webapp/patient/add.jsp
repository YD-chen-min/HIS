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
    <title>录入病人基本信息</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="patientInfoFormInsert" method="post">
    <div  style="padding: 20px 0px 0px 50px">
        <label for="name">姓名:</label>
        <input class="easyui-validatebox" type="text" name="name" id="name" data-options="required:true" />
        <label for="sex">性别</label>
        <input  id="sex" class="easyui-radiobutton" name="sex" value="0" label="男"  labelPosition="after" >
        <input class="easyui-radiobutton" name="sex" value="1" label="女" labelPosition="after" >
        <label for="id">身份证号码:</label>
        <input class="easyui-validatebox" type="text" name="id" id="id" data-options="digits:true" />
    </div>
    <div  style="padding: 20px 0px 0px 50px">
        <label for="cardId">卡号:</label>
        <input class="easyui-validatebox" type="text" name="cardId" id="cardId" data-options="digits:true,required:true" />
        <label for="age">年龄:</label>
        <input class="easyui-validatebox" type="text" name="age" id="age" data-options="digits:true,required:true" />
        <label for="tel">电话:</label>
        <input class="easyui-validatebox" type="tel" name="tel" id="tel" data-options="digits:true,required:true" />
        <label for="birth">出生日期:</label>
        <input id="birth" type="text" class="easyui-datebox" required="required">
    </div>
    <div  style="padding: 20px 0px 0px 50px">
        <label for="bloodType">血型:</label>
        <select id="bloodType" class="easyui-combobox" name="bloodType" style="width:200px;">
            <option value="">未知</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="AB">AB</option>
            <option value="O">O</option>
        </select>
    </div >
    <div  style="padding: 20px 0px 0px 50px">
        <label for="medicalHistory">病史:</label>
        <textarea name="medicalHistory" id="medicalHistory" maxlength="255"></textarea>
        <label for="allergicHistory">过敏史:</label>
        <textarea name="allergicHistory" id="allergicHistory" maxlength="255"></textarea>
    </div>
    <div align="center">
        <button>添加</button>
    </div>
</form>
<script>
    var birth;
    $(function () {
        $('#birth').datebox({
            onSelect:function (date) {
                birth=date.getMonth()+1+'/'+date.getDate()+'/'+date.getFullYear();
            }
        });
        $('#patientInfoFormInsert').form({
            url:'patient/insert',
            onSubmit:function (param) {
                // if(!checkIdcard($('#id').val())){
                //     alert($('#id').val()+'这不是一个合法的身份证号码');
                //     return false;
                // }
                param.p1=birth;
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
