<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/17
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>结算</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<form id="settleForm3">
    <table>
        <div style="padding: 10px 0px 0px 50px">
            <tr>
                <td>
                    <label for="initialCost">总金额</label>
                    <input type="text" class="easyui-numberbox" value="<%=request.getParameter("initialCost")%>" readonly="readonly" id="initialCost" name="initialCost"  data-options="min:0,precision:2">
                </td>
                <td>
                    <label for="roundingAmount">舍入金额</label>
                    <input type="text" class="easyui-numberbox" readonly="readonly" id="roundingAmount" name="roundingAmount"  data-options="min:0,precision:2">
                </td>
            </tr>
        </div>
        <div style="padding: 10px 0px 0px 50px">
            <tr>
                <td>
                    <label for="payType">支付方式</label>
                    <select id="payType" class="easyui-combobox" name="dept" style="width:200px;">
                        <option value="现金">现金</option>
                    </select>
                </td>
            </tr>
        </div>
        <div style="padding: 10px 0px 0px 50px">
            <tr>
                <td>
                    <label for="actualPayment">实际收款</label>
                    <input type="text" class="easyui-numberbox" id="actualPayment" name="actualPayment"  data-options="min:0,precision:2">
                </td>
                <td>
                    <label for="change">找零</label>
                    <input type="text" class="easyui-numberbox"  readonly="readonly" id="change" name="change" data-options="min:0,precision:2">
                </td>
            </tr>
        </div>
    </table>
    <div align="center" style="padding-top: 10px">
        <tr>
            <td><button >结算</button></td>
        </tr>
    </div>
</form>
<script>
    var initialCost=0+<%=request.getParameter("initialCost")%>;
    var roundingAmount=0;
    var id='<%=request.getParameter("id")%>';
    $(function () {
        $('#initialCost').numberbox({
            onChange:function (newValue,oldValue) {
                var num=newValue;
                num=num.substring(num.indexOf(".")+2,num.indexOf(".")+3);
                $('#roundingAmount').numberbox('setValue',num);
            }
        });
        $('#actualPayment').numberbox({
            onChange:function (newValue,oldValue) {
                var num=newValue-$('#initialCost').numberbox('getValue');
                $('#change').numberbox('setValue',num);
            }
        });
        $('#settleForm3').form({
            url:'projectInfo/settle',
            onSubmit:function (param) {
                var finalCost=$('#initialCost').val()-$('#roundingAmount').val();
                param.p1=finalCost;
                param.p2=id;
            },
            success:function (data) {
                data=JSON.parse(data);
                $.messager.alert('信息',data.msg,'info');
            }
        });
    });


</script>
</body>
</html>
