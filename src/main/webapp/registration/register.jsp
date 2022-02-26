<%@ page import="com.yandan.model.DoctorInfo" %><%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/15
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>挂号</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<%
    DoctorInfo doctorInfo=(DoctorInfo)request.getSession().getAttribute("doctorInfo");
%>
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
<form id="searchForm" method="post">
    <div style="padding: 10px 0px 0px 50px">
        <label for="patientName">姓名：</label>
        <input class="easyui-validatebox" type="text" name="patientName"  id="patientName"/>
        <label for="patientId">身份证号：</label>
        <input class="easyui-validatebox" type="text" name="patientId"  id="patientId" />
        <button>查询</button>
    </div>
</form>
<form id="registration" method="post">
    <div style="padding: 10px 0px 0px 50px">
        <label for="costType">费别:</label>
        <select id="costType" class="easyui-combobox" name="costType" style="width:200px;">
            <option value="1">自费</option>
            <option value="2">公费</option>
        </select>
    </div>
    <div style="padding: 10px 0px 0px 50px">
        <label for="firstOrLast">初复诊:</label>
        <select id="firstOrLast" class="easyui-combobox" name="firstOrLast" style="width:200px;">
            <option value="1">初诊</option>
            <option value="2">复诊</option>
        </select>
        <label for="type">挂号类别:</label>
        <select id="type" class="easyui-combobox" name="type" style="width:200px;">
            <option value="1">普通号</option>
            <option value="2">专家号</option>
        </select>
        <label for="deptId">科室选择:</label>
        <select id="deptId"  name="deptId" style="width:200px;">
            <!--远程加载科室数据(已完成)-->
        </select>
        <label for="doctorWorkId">医生:</label>
        <select id="doctorWorkId"  name="doctorWorkId" style="width:200px;">
<%--            远程加载医生数据，与科室形成二级联动  （已完成）--%>
        </select>
    </div>
    <div  style="padding: 10px 0px 0px 50px">
        <table>
             <tr>
                <td>姓名：<input type="text" readonly="readonly"  id="name" name="name"></td>
                <td>性别：<input type="text" readonly="readonly" id="sex" name="sex"></td>
                <td>年龄：<input type="text" readonly="readonly" id="age" name="age"></td>
                <td>出生日期：<input type="text" readonly="readonly" id="birth" name="birth"></td>
                <td>卡号：<input type="text" readonly="readonly" id="cardId" name="cardId"></td>
            </tr>
        </table>
    </div>
    <div align="center">
        <button>保存</button>
    </div>
</form>

<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
</div>
<table id="dg">
<%--远程加载该病人的挂号记录(已完成)--%>
</table>
<div id="registerDiag"></div>
<div id="registerBb" >
    <a href="#" class="easyui-linkbutton" onclick="submit()">Save</a>
    <a href="#" class="easyui-linkbutton" onclick="mclose()">Close</a>
</div>
<script>
    $(function () {
        $('#dg').datagrid({
            url:'registrationHis/get',
            toolbar:'#tb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:false,
            singleSelect:true,
            queryParams:{
                patientId:$('#patientId').val()
            },
            onDblClickRow:function (rowIndex,rowData) {
                if(rowData.status!=1){
                    alert("已完成，已缴费以及已取消的挂号无法进行缴费");
                }else{
                    $('#registerDiag').dialog({
                        title:'结算',
                        href: 'payment/settle2.jsp'+'?initialCost='+rowData.initialCost+'&id='+rowData.id,
                        width:600,
                        height:280,
                        resizable:true,
                        closed:true,
                        buttons:$('#registerBb')
                    });
                    $('#registerDiag').dialog('open');
                }
                 loadRegistrationHis();
            },
            columns:[[
                {field:'ck' ,checkbox: true},//复选框
                {field:'id',title:'挂号单号',width:100,align:'center'},
                {field:'deptId',title:'科室',width:100,align:'center',
                    formatter:function (value,rowData,rowIndex) {
                        var deptName;
                        $.ajax({
                            url:'/dept/get/'+rowData.deptId,
                            async:false,
                            type:'get',
                            resultType: 'json',
                            success:function (data) {
                                deptName=data.deptInfos[0].name;
                            }
                        });
                        return deptName;
                    }},
                {field:'doctorId',title:'医生工号',width:100,align:'center'},
                {field:'initialCost',title:'初始费用',width:100,align:'center'},
                {field:'finalCost',title:'最终支付费用',width:100,align:'center'},
                {field:'firstOrLast',title:'初复诊',width:100,align:'center'},
                {field:'status',title:'状态',width:100,align:'center',
                    formatter:function (value,row,index) {
                        if(value==1){
                            return '未缴费';
                        }
                        if(value==0){
                            return '已完成';
                        }
                        if(value==-1){
                            return '已取消';
                        }
                        if(value==2){
                            return '已缴费';
                        }
                    }}
            ]]
        });
        $('#searchForm').form({
            url:'patient/get',
            onSubmit:function () {
                if(!checkIdcard($('#patientId').val())){
                    alert($('#patientId').val()+'这不是一个合法的身份证号码');
                    return false;
                }
            },
            success:function (data) {
                //返回数据为查询到的病人个数counts和patients（对象列表)
                data=JSON.parse(data);
                var counts=data.counts;
                var patients=data.patients;
                if(counts==0){
                    alert('暂无此病人信息，即刻进行录入');
                    $('#registerDiag').dialog({
                        title:'添加病人信息',
                        href: 'patient/add.jsp',
                        width:1000,
                        height:350,
                        resizable:true,
                        buttons:$('#registerBb'),
                        closed:true
                    });
                    $('#registerDiag').dialog('open');
                }
                if(counts==1){
                    $('#name').val(patients[0].name);
                    $('#sex').val(patients[0].sex);
                    $('#age').val(patients[0].age);
                    $('#birth').val(patients[0].birth);
                    $('#cardId').val(patients[0].cardId);
                    $('#patientId').val(patients[0].id);
                    $('#patientName').val(patients[0].name);
                }
                if(counts>1){
                    $('#registerDiag').dialog({
                        title:'病人信息选择',
                        href: 'patient/select.jsp',
                        width:1000,
                        height:350,
                        resizable:true,
                        closed:true
                    });
                    $('#registerDiag').dialog('open');

                }
                $('#dg').datagrid('load',{
                     patientId: patients[0].id,
                })  ;
            }
        });
        $.get('dept/getAll',{},function (data) {
            $('#deptId').empty();
            $('#deptId').append(getOptionsDept(data));
        },'json');
        $('#deptId').change(function () {
            change($("#deptId").val(),$('#type').val());
        });
        $('#registration').form({
           url:'registrationInfo/insert',
            success:function (data) {
                data=JSON.parse(data);
                loadRegistrationHis();
                $('#registerDiag').dialog({
                    title:'结算',
                    href: 'payment/settle2.jsp'+'?initialCost='+data.initialCost+'&id='+data.id,
                    width:600,
                    height:280,
                    resizable:true,
                    closed:true,
                    buttons:$('#registerBb')
                });
                $('#registerDiag').dialog('open');
            }
        });
        loadRegistrationHis();
    });
function loadRegistrationHis() {
    $('#dg').datagrid('load');
}

</script>
</body>
</html>
