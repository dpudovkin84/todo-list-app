<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>All Users</h2>
<br>
<table>
    <tr>
        <th>Name</th>
<%--        <th>Surname</th>--%>
<%--        <th>Department</th>--%>
<%--        <th>Salary</th>--%>
<%--        <th>Operations</th>--%>
    </tr>
    <c:forEach var="user" items="${userList}">

<%--        <c:url var="updateButton" value="/updateInfo">--%>
<%--            <c:param name="empId" value="${emp.id}"/>--%>
<%--        </c:url>--%>

<%--        <c:url var="deleteButton" value="/deleteEmployee">--%>
<%--            <c:param name="empId" value="${emp.id}"/>--%>
<%--        </c:url>--%>


        <tr>
            <td>${user.name}</td>
<%--            <td>${user.todos}</td>--%>
<%--            <td>${emp.department}</td>--%>
<%--            <td>${emp.salary}</td>--%>
<%--            <td>--%>
<%--                <input type="button" value="Update"--%>
<%--                       onclick="window.location.href='${updateButton}'"/>--%>

<%--                <input type="button" value="Delete"--%>
<%--                       onclick="window.location.href='${deleteButton}'"/>--%>
<%--            </td>--%>

        </tr>
    </c:forEach>

</table>
<br>
<%--<input type="button" value="Add"--%>
<%--       onclick="window.location.href='/addNewEmployee'"/>--%>

</body>
</html>
