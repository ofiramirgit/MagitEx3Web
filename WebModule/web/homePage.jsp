<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>user page - ${user}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="home.css">
</head>
<body>
<%
    List<String> notifications= (List<String>)request.getAttribute("notificationsList");
    ArrayList<String> users= (ArrayList<String>)request.getAttribute("usersList");
    ArrayList<String> repositories= (ArrayList<String>)request.getAttribute("repositoriesList");
%>
<div class="container">
    <div class="row row0">
        <button id="logout" type="button" class="btn btn-danger">LOGOUT</button>
    </div>
    <h1>Hello <span id="username">${user}</span></h1>
    <div class="row row1">
        <div class="col-5">

            <h2>NOTIFICATIONS</h2>
            <div class="notifications">
                <% for (String noti:notifications) { %>
                <li><%= noti %></li>
                <%}%>
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
                    <% for (String repo:repositories) { %>
                    <tr class="repos-tr">
                        <td id="repo-name"><%= repo %></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </div>
    <div class="row row2">
        <div class="col-12">
            <h2>List Users</h2>
            <div class="list-users">
                <% for (String user:users) { %>
                <li class="users-li" name="username"><%= user %></li>
                <% }%>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous"></script>

<script src="home.js"></script>

</html>
