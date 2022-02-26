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
    <title>医生信息管理</title>
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
    <td>工号</td><td><input name="workId" id="workId" type="text"></td>
    <td>姓名</td><td><input name="name"  id="name" type="text"></td>
    <td>职位</td>
    <td><select id="job"  name="job" style="width:200px;">
                <option value="医生">医生</option>
                <option value="专家">专家</option>
                <option value="护士">护士</option>
            </select>
    </td>
    <td>科室</td><td><select id="deptId"  name="deptId" style="width:200px;"></select></td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="doctorTable" class="easyui-datagrid" style="width:400px;height:250px">

</table>
<div id="doctorTb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
</div>
<div id="doctorDiag"></div>
<div id="doctorBb" >
    <a href="#" class="easyui-linkbutton" onclick="submit()">Save</a>
    <a href="#" class="easyui-linkbutton" onclick="mclose()">Close</a>
</div>
<script>
    var flag;
    $(function () {
        $.get('dept/getAll',{},function (data) {
            $('#deptId').empty();
            $('#deptId').append(getOptionsDept(data));
        },'json');
        reload();
    });
    function search() {
        reload();
    };
    function insert() {
        flag=true;
        $('#doctorDiag').dialog({
            title:'添加医生',
            href: 'doctor/add.jsp',
            width:800,
            height:300,
            resizable:true,
            buttons:$('#doctorBb'),
            closed:true
        });
        $('#doctorDiag').dialog('open');

    }
    function deleteRows() {
        if($('#doctorTable').datagrid('getChecked').length>0){
            var rows= $('#doctorTable').datagrid('getChecked');
            var wordIds='';
            for(var i=0;i<rows.length;i++){
                var doctor=rows[i];
                wordIds+=','+doctor.workId;
            }
            wordIds=wordIds.substring(1,wordIds.length);
            $.get('doctor/delete',{
                wordIds:wordIds
            },function (data) {
                $.messager.alert('message',data.msg,'info');
                reload();
            },'json');

        }
    }
    function submit() {
        //true: insert;  false: update
        if(flag){
            $('#doctorInfoFormInsert').form('submit',{
                url: 'doctor/insert',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                    reload();
                }

            });
        }else{
            $('#doctorInfoFormUpdate').form('submit',{
                url: 'doctor/update',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');

                }

            });
        }

    }
    function mclose() {
        $('#doctorInfoFormInsert').form('clear');
        $('#doctorInfoFormUpdate').form('clear');
        $('#doctorDiag').dialog('close');
    }

    function reload() {
        $('#doctorTable').datagrid({
            url:'doctor/getPage',
            queryParams:{
                name:$('#name').val(),
                id:$('#id').val(),
                job:$('#job').val(),
                deptId:$('#deptId').val()
            },
            toolbar:'#doctorTb',
            contentType:'charset=utf-8',
            pagination:true,
            pagePosition: 'top',
            fitColumns:true,
            fit:true,
            striped:true,
            selectOnCheck:true,
            singleSelect:true,
            onDblClickRow:function (rowIndex,rowData) {
                //加载医生详细信息页面
                $('#doctorDiag').dialog({
                    title:'修改医生信息',
                    href: 'doctor/update.jsp',
                    width:800,
                    height:300,
                    resizable:true,
                    buttons:$('#doctorBb'),
                    closed:true
                });
                $('#doctorDiag').dialog('open');
                console.log($('#doctorInfoFormUpdate').length);
                $.get('doctor/get/'+$('#doctorTable').datagrid('getSelected').workId,{},function (data) {
                    $('#doctorInfoFormUpdate').form('load',data.doctorInfos[0]);
                },'json');
            } ,
            columns:[[
                {field:'ck' ,checkbox: true},//复选框
                {field:'workId',title:'工号',width:100,align:'center'},
                {field:'name',title:'姓名',width:100,align:'center'},
                {field:'sex',title:'性别',width:100,align:'center',
                formatter:function (value) {
                        if(value==0){
                            return '男';
                        }else{
                            return '女';
                        }
                }},
                {field:'job',title:'职位',width:100,align:'center'},
                {field:'deptId',title:'部门',width:100,align:'center',
                formatter:function (value,row,index) {
                //    将部门编号转为部门名称
                    var name;
                      $.ajax({
                        url:'dept/get',
                        async:false,
                        type: 'get',
                        resultType:'json',
                        data:{id:value},
                        success:function(data){
                            name=  data.deptInfos[0].name;
                        }
                    });
                      return name;
                }},
                {field:'joinDate',title:'入职日期',width:100,align:'center'},
                {field:'remark',title:'备注',width:100,align:'center'},
            ]]

        });
    }
    function update() {
        flag=false;
        if($('#doctorTable').datagrid('getSelected')==null){
            $.messager.alert('警告','请选择一条数据','warning');
        }else{

            $('#doctorDiag').dialog({
                title:'修改医生信息',
                href: 'doctor/update.jsp',
                width:800,
                height:300,
                resizable:true,
                buttons:$('#doctorBb'),
                closed:true
            });
            $('#doctorDiag').dialog('open');
            $.get('doctor/get/'+$('#doctorTable').datagrid('getSelected').workId,{},function (data) {
                $('#doctorInfoFormUpdate').form('load',data.doctorInfos[0]);
            },'json');

        }
    }
</script>
</body>
</html>
