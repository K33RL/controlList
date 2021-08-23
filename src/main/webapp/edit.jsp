<%--
  Created by IntelliJ IDEA.
  User: kpere
  Date: 16.08.2021
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Users</title>
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
      <a href="https://www.xadmin.net" class="navbar-brand"> User Management Application </a>
    </div>

    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/users"
             class="nav-link">Users</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <form method="post">
            <h2>
                Edit User
            </h2>

            <input type="hidden" name="id" value=${requestScope.user.get().id}>

          <fieldset class="form-group">
            <label>User Name <input type="text" class="form-control" name="name"
                                    required="required" value=${requestScope.user.get().name}></label>
          </fieldset>

          <fieldset class="form-group">
            <label>User Surname <input type="text" class="form-control"
                                       name="surname" value=${requestScope.user.get().surname}></label>

            <button type="submit" class="btn btn-success">Save</button>
          </fieldset>
        </form>
    </div>
  </div>
</div>
</body>
</html>
