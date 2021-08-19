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
            <c:if test="${activity != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${activity == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${activity != null}">
                                Edit activity
                            </c:if>
                            <c:if test="${activity == null}">
                                Add new activity
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${activity != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Time</label> <input type="time"
                                                        value="<c:out value='${activity.time}' />" class="form-control"
                                                        name="time" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Description</label> <input type="text"
                                                          value="<c:out value='${activity.description}' />"
                                                          class="form-control"
                                                          name="description">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>