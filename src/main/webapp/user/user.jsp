<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title >用户管理</title >
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<table align="center">
    <td>账号</td><td><input name="id" id="id" type="text"></td>
    <td>用户名</td><td><input name="name"  id="name" type="text"></td>
    <td><button onclick="search()">查找</button></td>
</table>
<table  id="usertable" class="easyui-datagrid" style="width:400px;height:250px">

</table>
<div id="usertb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-yandan' ,plain:true"  onclick="update()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove-yandan', plain:true" onclick="deleteRows()"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-yandan', plain:true" onclick="insert()"></a>
</div>
<div id="userdialog"></div>
<div id="userbb">
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
        $('#userdialog').dialog({
            title:'添加用户',
            href: 'user/user_add.jsp',
            width:300,
            height:300,
            resizable:true,
            buttons:$('#userbb'),
            closed:true
        });
        $('#userdialog').dialog('open');

    }
function submit() {
        //true: insert;  false: update
        if(flag){
            $('#userForm').form('submit',{
               url: 'user/insert',
               success:function (data) {
                   data=JSON.parse(data);
                   $.messager.alert('message',data.msg,'info');
               }

            });
        }else{
            $('#userForm').form('submit',{
                url: 'user/AdminUpdate',
                success:function (data) {
                    data=JSON.parse(data);
                    $.messager.alert('message',data.msg,'info');
                }

            });
        }
        reload();
}
 function mclose() {
        $('#userForm').form('clear');
        $('#userdialog').dialog('close');
 }
   function deleteRows() {
      if($('#usertable').datagrid('getChecked').length>0){
          var rows= $('#usertable').datagrid('getChecked');
          var ids='';
          for(var i=0;i<rows.length;i++){
              var user=rows[i];
              ids+=','+user.id;
          }
          ids=ids.substring(1,ids.length);
          $.get('user/delete',{
              ids:ids
          },function (data) {
              $.messager.alert('message',data.msg,'info');
          },'json');
          reload();
      }
   }

   function reload() {
       $('#usertable').datagrid({
           url:'user/get',
           queryParams:{
               name:$('#name').val(),
               id:$('#id').val(),
           },
           toolbar:'#usertb',
           contentType:'charset=utf-8',
           pagination:true,
           pagePosition: 'top',
           fitColumns:true,
           fit:true,
           striped:true,
           selectOnCheck:false,
           singleSelect:true,
           columns:[[
               {field:'ck' ,checkbox: true},//复选框
               {field:'id',title:'账号',width:100,align:'center'},
               {field:'name',title:'姓名',width:100,align:'center'},
               {field:'role',title:'权限',width:100,align:'center'}
           ]]

       });
   }
   function update() {
        flag=false;
            if($('#usertable').datagrid('getSelected')==null){
                $.messager.alert('警告','请选择一条数据','warning');
            }else if($('#usertable').datagrid('getChecked').length>1){
                $.messager.alert('警告','不能选择多条数据','warning');
            }else{

                $('#userdialog').dialog({
                    title:'修改信息',
                    href: 'user/user_update.jsp',
                    width:300,
                    height:300,
                    resizable:true,
                    buttons:$('#userbb'),
                    closed:true
                });
                $('#userdialog').dialog('open');
                $.get('user/get/'+$('#usertable').datagrid('getSelected').id,{},function (data) {
                    $('#userForm').form('load',data);
                    $('#roleId').combobox({
                        url:"role/get",
                        valueField:'id',
                        textField:'name'
                    });
                },'json');

            }
   }
</script>
</body>
</html>
