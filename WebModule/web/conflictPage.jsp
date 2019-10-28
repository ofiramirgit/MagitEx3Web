<%@ page import="Logic.Conflict" %>
<%@ page import="Logic.OpenAndConflict" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: OL
  Date: 28/10/2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="conflictPage.css">
</head>
<body>
<%
    ArrayList<OpenAndConflict> openAndConflicts= (ArrayList<OpenAndConflict>)request.getAttribute("openAndConflicts");
    ArrayList<Conflict>conflicts = openAndConflicts.
    %>
    <div class="container">
        <div class="row row0 content-text" style="height: 30%; margin-top:20px">
            <div class="col-12">
                <h3>Conflicts</h3>
                <table style="width: 100%;">
                    <tr>
                        <th>Sha1</th>
                        <th>message</th>
                        <th>created date</th>
                        <th>creater</th>
                    </tr>
                    <% for (Conflict conflict:openAndConflicts) { %>
                    <tr class="commits-tr">
                        <td id="commit-sha1"><%= commit.getSha1() %></td>
                        <td id="commit-msg"><%= commit.getMessage() %></td>
                        <td id="commit-date"><%= commit.getTimestamp() %></td>
                        <td id="commit-creater"><%= commit.getCommitter() %></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
        <div class="row row1" style="height: 70%">
            <%--<span class="options">--%>
                <div id="ours" class="col-3 content-text" style="height:100%">
                    <h3>Ours</h3>
                </div>
                <div id="result" class="col-4 content-text" style="height:100%">
                    <h3>Result</h3>

                </div>
                <div id="theirs" class="col-3 content-text" style="height:100%">
                    <h3>Theirs</h3>

                </div>
            <%--</span>--%>
        </div>
    </div>
</body>
<script src="jQuery-v3.4.1.js"></script>
<script src="repository.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
</html>
