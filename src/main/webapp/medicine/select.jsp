<%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/19
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择药品</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<table align="center"  width="100%">
    <td>药品编号</td><td><input name="medicineId" id="medicineId" type="text"></td>
    <td>名称</td><td><input name="medicineName"  id="medicineName" type="text"></td>
    <td>种类</td>
    <td>
        <select id="medicineType" class="easyui-combobox" name="medicineType" style="width:200px;">
            <option value="" selected="selected">请选择</option>
            <option value="1">中药</option>
            <option value="2">西药</option>
        </select>
    </td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="medicineTable" class="easyui-datagrid" style="width:400px;height:250px">
</table>
<div id="medicineTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok' ,plain:true"  onclick="select()"></a>
</div>
<script>
    $(function () {
        reload();
    });
    function search() {
        reload();
    }
    function reload() {
        $('#medicineTable').datagrid({
            url:'medicine/getPage',
            queryParams:{
                name:$('#medicineName').val(),
                id:$('#medicineId').val(),
                type:$('#medicineType').val()
            },
            toolbar:'#medicineTb',
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
                {field:'id',title:'药品编号',width:100,align:'center'},
                {field:'name',title:'药品名称',width:100,align:'center'},
                {field:'type',title:'种类',width:100,align:'center',
                    formatter:function (value) {
                        if(value==1){
                            return "中药";
                        }
                        if(value==2){
                            return "西药";
                        }
                    }},
                {field:'specification',title:'规格',width:100,align:'center'},
                {field:'price',title:'价格',width:100,align:'center'},
            ]]

        });
    }
   function select() {
            var medicines=$('#medicineTable').datagrid('getChecked');
            var counts=medicines.length;
            for(var i=0;i<counts;i++){
                $('#prescriptionTable').append(getHtml(medicines[i],i));
            }
            $('#prescriptionDiag').dialog('close');
   }
   function getHtml(medicine,index) {
       var html="";
       html+="<tr>";
       html+="<td><input name='id"+index+"' type='text' readonly='readonly' value='"+medicine.id+"'></td>";
       html+="<td><input name='name"+index+"' type='text' readonly='readonly' value='"+medicine.name+"'></td>";
       html+="<td><input name='specification"+index+"' type='text' readonly='readonly' value='"+medicine.specification+"'></td>";
       html+="<td><input id='price"+index+"'  name='price"+index+"'  type=\"number\"  step='0.01' readonly='readonly' value='"+medicine.price+"'></td>";
       html+="<td><input id='amount"+index+"' name='amount"+index+"'  type=\"number\" required='required' > </td>";
       html+="<td><textarea name='remark"+index+"' type='text' '></textarea></td>";
       html+="</tr>";
       console.log(html);
       return html;

   }
</script>
</body>
</html>
