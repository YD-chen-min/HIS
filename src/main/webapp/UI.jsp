<%@ page import="com.yandan.model.User" %>
<%@ page import="com.yandan.model.DoctorInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>easyUI</title>
    <link rel="stylesheet" type="text/css" href="static/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/jquery-easyui-1.7.0/themes/icon.css">
    <script src="static/js/jquery.min.js"></script>
    <script src="static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<%
    User user=(User) request.getSession().getAttribute("user");
    DoctorInfo doctorInfo=(DoctorInfo)request.getSession().getAttribute("doctor");
    String workId=doctorInfo.getWorkId();
%>
<div data-options="region:'north',title:'North Title',split:true" style="height:100px;">
    <h1>医疗管理系统(<%=user.getName()%>)</h1>
</div>
<div data-options="region:'west',title:'导航栏',split:true" style="width:100px;">
    <ul id="mytree" class="easyui-tree">
    </ul>
</div>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">
    </div>
</div>
</body>
<script>
    $(function () {
        console.log("<%=doctorInfo%>>");
        console.log("<%=doctorInfo.getWorkId()%>")
        $.get('tree/node/getUserNode',{roleId:'<%=user.getRoleId()%>'},function (data) {
            $('#mytree').tree({
                        data:data,
                onClick:function (node) {
                    //加载子节点
                    $.get('tree/node/'+node.id,{},function (data) {

                        var children=$('#mytree').tree('getChildren',node.target);
                        if(children!=null&&children.length>0){
                            for(var i=0;i<children.length;i++){
                                $('#mytree').tree('remove',children[i].target);
                            }
                        }
                        $('#mytree').tree('append',{
                            parent:node.target,
                            data:data
                        });
                        var href=node.attributes.href;
                        if(href!=null&&href.length>0){
                            if($('#tabs').tabs('exists',node.text)){
                                $('#tabs').tabs('select',node.text);
                                var tab=$('#tabs').tabs('getTab',node.text);
                                tab.panel('refresh');
                            }else{
                               if(node.text=='文件管理'){
                                   $('#tabs').tabs('add',{
                                       title:node.text,
                                       select:true,
                                       closable:true,
                                       href:'<%=request.getContextPath()%>'+href+'?parentName=0'
                                   });
                               }else{
                                   $('#tabs').tabs('add',{
                                       title:node.text,
                                       select:true,
                                       closable:true,
                                       href:'<%=request.getContextPath()%>'+href+'?workId=<%=workId%>'
                                   });
                               }
                            }

                        }
                    },'json');
                }
            });
        },'json')



    });

</script>
</html>
