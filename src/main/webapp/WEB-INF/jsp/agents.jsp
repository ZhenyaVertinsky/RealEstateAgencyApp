<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Hello</title>
</head>

<style>
    <%--    Add css for table, rows, column here--%>
</style>
<body>

<div>
    <b>
        ${agent}
    </b>
</div>

<div>
    <h1>System Agents</h1>
</div>
<div>
    <table>
        <tr>
            <td>Agent Id</td>
            <td>Agent Name</td>
            <td>Agent Surname</td>
            <td>Birth date</td>
            <%--            <td>Gender</td>--%>
            <td>Is Deleted</td>
            <td>Phone</td>
            <td>Reward</td>
            <td>Created</td>
            <td>Changed</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="agent" items="${agents}">
            <tr>
                <td>${agent.id}</td>
                <td>${agent.agentName}</td>
                <td>${agent.agentSurname}</td>
                <td>${agent.birthday}</td>
                <td>${agent.agentPhone}</td>
                <td>${agent.percentReward}</td>
                <td>${agent.isDeleted}</td>
                <td><fmt:formatDate value="${agent.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${agent.modificationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${agent.percentReward}"/></td>
                <td>
                    <button>Edit</button>
                </td>
                <td>
                    <button>Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>