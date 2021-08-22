<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Manager</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #42e04f">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> User
                Management Application </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/users"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/create" class="btn btn-success">Add
                New UserActivity</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>IdUser</th>
                <th>Time</th>
                <th>Activities</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="userActivity" items="${requestScope.activityList}">

                <tr>
                    <td><c:out value="${userActivity.id}"/></td>
                    <td><c:out value="${userActivity.user_id}"/></td>
                    <td><c:out value="${userActivity.time}"/></td>
                    <td><c:out value="${userActivity.activities}"/></td>
                    <td><a href="change?id=<c:out value='${userActivity.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteActivity?id=<c:out value='${userActivity.id}' />">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>