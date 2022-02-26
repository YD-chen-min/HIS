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
    <title>病人管理</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<script>
    function checkIdcard(personnumber) {
        personnumber = personnumber.toUpperCase();
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(personnumber))) {
            return false;
        }
        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
        //下面分别分析出生日期和校验位
        var len, re;
        len = personnumber.length;
        if (len == 15) {
            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
            var arrSplit = personnumber.match(re);

            //检查生日日期是否正确
            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                return false;
            }
            else {
                //将15位身份证转成18位
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                personnumber = personnumber.substr(0, 6) + '19' + personnumber.substr(6, personnumber.length - 6);
                for (i = 0; i < 17; i++) {
                    nTemp += personnumber.substr(i, 1) * arrInt[i];
                }
                personnumber += arrCh[nTemp % 11];
                return true;
            }
        }
        if (len == 18) {
            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
            var arrSplit = personnumber.match(re);

            //检查生日日期是否正确
            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                return false;
            } else {
                //检验18位身份证的校验码是否正确。
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var valnum;
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                for (i = 0; i < 17; i++) {
                    nTemp += personnumber.substr(i, 1) * arrInt[i];
                }
                valnum = arrCh[nTemp % 11];
                if (valnum != personnumber.substr(17, 1)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    function change(deptId,type) {
        $.get('doctor/get',{
            deptId:deptId,
            type:type
        },function (data) {
            $('#doctorWorkId').empty();
            $('#doctorWorkId').append(getOptionsDoctor(data));
        },'json');
    }
    function getOptionsDoctor(data) {
        var html="";
        html=html+"<option selected=\"selected\">--请选择--</option>";
        for (var i=0;i<data.length;i++){
            html=html+"<option value='"+data[i].workId+"' >"+data[i].name+"</option>";
        }
        return html;
    }
    function getOptionsDept(data) {
        var html="";
        html=html+"<option selected=\"selected\">--请选择--</option>";
        for (var i=0;i<data.length;i++){
            html=html+"<option value='"+data[i].id+"' >"+data[i].name+"</option>";
        }
        return html;
    }
    // ————————————————
    // 版权声明：本文为CSDN博主「燕丹12」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    // 原文链接：https://blog.csdn.net/weixin_42957408/article/details/105729038
</script>
<table align="center">
    <td>身份证号码</td><td><input name="id" id="id" type="text" maxlength="18" ></td>
    <td>姓名</td><td><input name="name"  id="name" type="text"></td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="patientTable" class="easyui-datagrid" style="width:400px;height:250px">

</table>
<div id="patientTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
</div>
<div id="patientDiag"></div>
<div id="patientBb" >
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
        $('#patientDiag').dialog({
            title:'添加病人信息',
            href: 'patient/add.jsp',
            width:1000,
            height:350,
            resizable:true,
            buttons:$('#patientBb'),
            closed:true
        });
        $('#patientDiag').dialog('open');

    }
    function submit() {
        //true: insert;  false: update
        if(flag){
            $('#patientInfoFormInsert').form('submit',{
                url: 'patient/insert',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                }

            });
        }else{
            $('#patientInfoFormUpdate').form('submit',{
                url: 'patient/update',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                }

            });
        }
        reload();
    }
    function mclose() {
        $('#patientInfoFormInsert').form('clear');
        $('#patientInfoFormUpdate').form('clear');
        $('#patientDiag').dialog('close');
    }

    function reload() {
        $('#patientTable').datagrid({
            url:'patient/getPage',
            queryParams:{
                name:$('#name').val(),
                id:$('#id').val()
            },
            toolbar:'#patientTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex,rowData) {
                //加载病人详细信息页面
                $('#patientDiag').dialog({
                    title:'修改病人信息',
                    href: 'patient/update.jsp',
                    width:1000,
                    height:350,
                    resizable:true,
                    buttons:$('#patientBb'),
                    closed:true
                });
                $('#patientDiag').dialog('open');
                console.log($('#patientInfoFormUpdate').length);
                $.get('patient/get/'+$('#patientTable').datagrid('getSelected').id,{},function (data) {
                    $('#patientInfoFormUpdate').form('load',data.patientInfos[0]);
                },'json');
            } ,
            columns:[[
                {field:'id',title:'身份证号码',width:100,align:'center'},
                {field:'cardId',title:'卡号',width:100,align:'center'},
                {field:'name',title:'姓名',width:100,align:'center'},
                {field:'sex',title:'性别',width:100,align:'center',
                    formatter:function (value,row,index) {
                            if(value==0){
                                return '男';
                            }else{
                                return '女';
                            }
                    }},
                {field:'age',title:'年龄',width:100,align:'center'},
                {field:'tel',title:'联系电话',width:100,align:'center'},
                {field:'bloodType',title:'血型',width:100,align:'center'},
                {field:'birth',title:'出生日期',width:100,align:'center'},
            ]]

        });
    }
    function update() {
        flag=false;
        if($('#patientTable').datagrid('getSelected')==null){
            $.messager.alert('警告','请选择一条数据','warning');
        }else{

            $('#patientDiag').dialog({
                title:'修改病人信息',
                href: 'patient/update.jsp',
                width:1000,
                height:350,
                resizable:true,
                buttons:$('#patientBb'),
                closed:true
            });
            $('#patientDiag').dialog('open');
            console.log($('#patientInfoFormUpdate').length);
            $.get('patient/get/'+$('#patientTable').datagrid('getSelected').id,{},function (data) {
                $('#patientInfoFormUpdate').form('load',data.patientInfos[0]);
            },'json');

        }
    }
</script>
</body>
</html>
