<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: amira
  Date: 13-Oct-19
  Time: 03:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@  page language="java" contentType="text/html" session ="false" %>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="home.css">
</head>
<body>
<%
    List<String> notifications= (List<String>)request.getAttribute("notificationsList");
    List<String> users= (List<String>)request.getAttribute("usersList");
%>
<div class="container">
    <div class="row row0">
        <button id="logout" type="button" class="btn btn-danger">LOGOUT</button>
    </div>
    <h1>Hello ${user}</h1>
    <div class="row row1">
        <div class="col-5">

            <h2>NOTIFICATIONS</h2>
            <div class="notifications">
                <%
                    for (String noti:notifications) {
                  %>
                <li><%= noti %></li>
                <%
                    }
                %>
            </div>
            <button id="add" type="button" class="btn btn-primary">ADD REPOSITORY</button>
        </div>

        <div class="col-7">
            <h2>LIST REPOSITORIES</h2>
            <div class="list-repositories">
                <table class="repo-table">
                    <tr>
                        <th>Name</th>
                        <th>Active Branch</th>
                        <th>Amount Of Branches</th>
                        <th>Last Commit</th>
                        <th>Commit Msg</th>
                    </tr>
                    <tr class="repos-tr">
                        <td>repo1</td>
                        <td>master</td>
                        <td>4</td>
                        <td>18-5-2010</td>
                        <td>test1</td>
                    </tr>
                    <tr class="repos-tr">
                        <td>repo2</td>
                        <td>test</td>
                        <td>2</td>
                        <td>11-5-2021</td>
                        <td>test2</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="row row2">
        <div class="col-12">
            <h2>List Users</h2>
            <div class="list-users">
                <%
                    for (String user:users) {
                %>
                <li class="users-li"><%= user %></li>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.js"
        integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI="
        crossorigin="anonymous"></script>
<script src="home.js"></script>

</html>
