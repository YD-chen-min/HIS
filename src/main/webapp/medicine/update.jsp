<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改药品信息</title>
</head>
<body>
<form id="medicineInfoFormUpdate" method="post">
    <div  style="padding: 20px 0px 0px 50px">
        <label for="id">药品编号:</label>
        <input class="easyui-validatebox" type="text" name="id" id="id" maxlength="13" data-options="required:true" />
        <label for="name">名称:</label>
        <input class="easyui-validatebox" type="text" name="name" id="name" data-options="required:true" />
        <label for="type">种类</label>
        <input  id="type" class="easyui-radiobutton" name="type" value="1" label="中药"  labelPosition="after" labelWidth="60px">
        <input   class="easyui-radiobutton" name="type" value="2" label="西药"  labelPosition="after" labelWidth="60px">
        <label for="specification">规格:</label>
        <input class="easyui-validatebox" type="text" name="specification" id="specification" data-options="required:true" />
    </div>
    <div  style="padding: 20px 0px 0px 50px">
        <label for="price">单价:</label>
        <input type="text" class="easyui-numberbox"  name="price" id="price" data-options="min:0,precision:2">
        <label for="inventory">库存:</label>
        <input type="text" class="easyui-numberbox"  name="inventory" id="inventory" data-options="min:0,precision:0">
        <label for="manufacturer">厂商</label>
        <input class="easyui-validatebox" type="text" name="manufacturer" id="manufacturer" data-options="required:true" />
    </div>
    <div  style="padding: 20px 0px 0px 50px">
        <label for="madeDate">生产日期:</label>
        <input id="madeDate" name="madeDate" type="text" class="easyui-datebox" required="required" disabled="disabled">
        <label for="buyDate">购入日期:</label>
        <input id="buyDate" type="text" name="buyDate" class="easyui-datebox" required="required" disabled="disabled">
    </div>
    <div  style="padding: 20px 0px 0px 50px">
        <label for="remark">备注说明:</label>
        <textarea name="remark" id="remark" maxlength="255"></textarea>
    </div>
    <div  align="center">
        <button>修改</button>
    </div>
</form>
<script>
    $(function () {

        $('#medicineInfoFormUpdate').form({
            url:'medicine/update',
            onsubmit:function (param) {
                param.p1=madeDate;
                param.p2=buyDate;
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
