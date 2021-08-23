<%--
  Created by IntelliJ IDEA.
  User: kpere
  Date: 18.08.2021
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #2288e7">
        <div>
            <a href="https://github.com/VolgaDaniluk/controlList" class="navbar-brand"> User Management Application </a>
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
                                Add new activity
                        </h2>
                    <input type="hidden" name="id" value=${requestScope.user.get().id}/>

                    <fieldset class="form-group">
                        <label>Time <input type="time" class="form-control" name="time"
                                           required="required" value=${requestScope.activity.get().durationMin}></label>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Description <input type="text" class="form-control"
                                                  name="description" value=${requestScope.activity.get().description}></label>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>