<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<C:if test="${empty requestScope.allLinkman}">
    <C:redirect url="/LinkManServlet?method=listLinkMan&userId=${sessionScope.user.userId}&page=1&count=3"></C:redirect>
</C:if>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .btn1{
            background-color: deepskyblue;
        }

        div {
            margin: 0 auto;
        }

        h3 {
            text-align: center;
        }

        table, tr, th {
            text-align: center;
            border: 1px solid gray;
            border-collapse: collapse;
        }
        td{
            border: 1px solid gray;
            width: 100px;
        }

        table {
            width: 100%;
        }

        tr:nth-child(2n) {
            background-color: aliceblue;
        }

        tr:hover {
            background-color: gray;
        }

        #tt {
            text-align: right;
            padding-right: 20px;
        }


    </style>


</head>
<body>


<div>
    <div>欢迎你:<span style="background-color: aquamarine">${sessionScope.user.username}</span>-----<a href="UserServlet?method=logoff" >安全退出</a></div>

    <br/>

    <h3>您的通讯录用户列表如下：</h3>

    <table>

        <table>

            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>分组</th>
                <th>操作</th>
            </tr>
            <C:forEach var="m" items="${requestScope.allLinkman}"   varStatus="s">
                <tr>
                    <td>${m.name}</td>
                    <td>${m.sex}</td>
                    <td>${m.phone}</td>
                    <td>${m.email}</td>
                    <td>${m.group}</td>
                    <td>
                        <button class="tdButt" onclick="delRow(this)">删除</button>
                    </td>
                </tr>
            </C:forEach>


        </table>
        <br>
        <a href="/LinkManServlet?method=listLinkMan&userId=${sessionScope.user.userId}&page=1&count=3">首页</a>
        <a href="/LinkManServlet?method=listLinkMan&userId=${sessionScope.user.userId}&page=${requestScope.pageBean.previousPage}&count=3">上一页</a>
        <a href="/LinkManServlet?method=listLinkMan&userId=${sessionScope.user.userId}&page=${requestScope.pageBean.nextPage}&count=3">下一页</a>
        <a href="/LinkManServlet?method=listLinkMan&userId=${sessionScope.user.userId}&page=${requestScope.pageBean.allPages}&count=3">尾页</a>
        <span>当前第${requestScope.pageBean.nowPage}页/总共${requestScope.pageBean.allPages}页 ,每页最多${requestScope.pageBean.everyPageCount}条/总共${requestScope.pageBean.allCount}条</span>

    </table>
</div>




</body>
</html>
