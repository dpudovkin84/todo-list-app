<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
    <title>View for all</title>
</head>

<body>
<br><br>
<h3>Information for all employees</h3>
<br><br>
<security:authorize access="hasRole('HR')">
<input type="button" value="Salary"
       onclick="window.location.href='hr_info'">
Only for HR staff
</security:authorize>
<br><br>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Perfomance"
       onclick="window.location.href='manager_info'">
Only for Managers
</security:authorize>
<br><br>

</body>
</html>
