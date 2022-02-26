<%@ page import="com.yandan.model.DoctorInfo" %><%--
  Created by IntelliJ IDEA.
  User: YanDan
  Date: 2020/7/19
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>待处理</title>
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
<%String workId=request.getParameter("workId");%>
<table id="myRegistration"></table>
<div id="myRegistrationTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
</div>
<div id="myRegistrationDiag" ></div>
<script>
    var accidentDate;
    $(function () {
        reload();
    });
    function reload() {
        console.log('<%=workId%>');
        $('#myRegistration').datagrid({
            url:'registrationInfo/get',
            queryParams:{
                doctorWorkId:'<%=workId%>'
            },
            toolbar:'#myRegistrationTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex,rowData) {
                //加载诊断页面
                if(0==rowData.status||-1==rowData.status||1==rowData.status){
                    alert("已完成，未缴费，已取消的挂号无法进行诊断");
                }else{
                    $('#myRegistrationDiag').dialog({
                        title:'诊断',
                        href: 'diagnostic/diagnostic.jsp?registrationId='+rowData.id,
                        width:1400,
                        height:500,
                        resizable:true,
                        closed:true
                    });
                    $('#myRegistrationDiag').dialog('open');
                    $.get('registrationInfo/getInfo',{registrationId:rowData.id},function (data) {
                        $('#registrationForm').form('load',data.readOnlyDate);
                        $('#medicalHistory').val(data.medicalHistory);
                        $('#allergicHistory').val(data.allergicHistory);
                        $('#accidentDate').datebox({
                           onSelect:function (date) {
                                   accidentDate=date.getMonth()+1+'/'+date.getDate()+'/'+date.getFullYear();
                           }
                        });
                        $('#diagnosticForm').form({
                            url:'diagnostic/insert',
                            onSubmit:function (param) {
                                    param.p1='<%=workId%>';
                                    param.p2=rowData.id;
                                    param.p3=accidentDate;
                            },
                            success:function (data) {
                                data=JSON.parse(data);
                                $('#myRegistrationDiag').dialog({
                                    title:'处方与检查项目',
                                    href: 'prescriptionAndProject/main.jsp?registrationId='+data.registrationId+'&diagnosticId='+data.diagnosticId,
                                    width:1400,
                                    height:500,
                                    resizable:true,
                                    buttons:$('#myRegistrationBb'),
                                    closed:true
                                });
                                $('#myRegistrationDiag').dialog('open');
                                $.messager.alert('信息',data.msg,'info');
                            }
                        });
                    },'json')
                }
            } ,
            columns:[[
                {field:'id',title:'挂单号',width:100,align:'center'},
                {field:'cardId',title:'病人姓名',width:100,align:'center',
                formatter:function (value) {
                    var patientName;
                    $.ajax({
                        url:'patient/get',
                        type: 'get',
                        data:{cardId:value},
                        async:false,
                        resultType:'json',
                        success:function (data) {
                            patientName= data.patients[0].name;
                        }
                    });
                    return patientName;
                }},
                {field:'type',title:'种类',width:100,align:'center',
                    formatter:function (value) {
                            if(value==1){
                                return "普通号";
                            }
                            if(value==2){
                                return "专家号";
                            }
                    }},
                {field:'firstOrLast',title:'初复诊',width:100,align:'center',
                    formatter:function (value,row,index) {
                        if(value==1){
                            return "初诊";
                        }
                        if (value==2){
                            return "复诊";
                        }
                    }},
                {field:'status',title:'状态',width:100,align:'center',
                formatter:function (value) {
                    if(value==2){
                        return"已交费";
                    }
                    if (value==1){
                        return "未缴费";
                    }
                    if (value==-1){
                        return "已取消";
                    }
                    if(value==0){
                        return "已完成";
                    }
                }},
            ]]

        });
    }
    function deleteRows() {
        if($('#myRegistrationTable').datagrid('getChecked').length>0){
            var rows= $('#myRegistrationTable').datagrid('getChecked');
            var ids='';
            for(var i=0;i<rows.length;i++){
                var registration=rows[i];
                ids+=','+registration.id;
            }
            ids=ids.substring(1,ids.length);
            $.get('registration/delete',{
                ids:ids
            },function (data) {
                $.messager.alert('message',data.msg,'info');
            },'json');
            reload();
        }
    }
</script>
</body>
</html>
