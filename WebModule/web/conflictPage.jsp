<%@ page import="Logic.Conflict" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Logic.OpenChange" %>
<%@ page import="Logic.OpenAndConflict" %><%--
  Created by IntelliJ IDEA.
  User: OL
  Date: 28/10/2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Conflicts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="conflictPage.css">
</head>
<body>
<%
    OpenAndConflict openAndConflicts = (OpenAndConflict)request.getAttribute("openAndConflicts");
    ArrayList<Conflict> conflicts = openAndConflicts.getConflictList();
    ArrayList<OpenChange> openChanges = openAndConflicts.getOpenChangesList();
    String username = (String) request.getAttribute("username");
    String repo_name = (String) request.getAttribute("repo_name");

%>
    <div class="container">
        <div class="row row4" style="height: 10%; margin-top:20px">
            <div class="col-3">
                <button id="edit-btn" class="newbtn"><i class="fa fa-home wc-buttons"></i></button>
            </div>
            <div class="col-6"><span id="username"><%=username%></span><span>, </span><span id ="repo_name"><%=repo_name%></span></div>
            <div class="col-3">

            </div>
        </div>
        <div class="row row0 content-text" style="height: 20%; margin-top:20px">
            <div class="col-12">
                <h3>Conflicts</h3>
                <table style="width: 100%;">
                    <tr>
                        <th>Our Path</th>
                        <%--<th class="hide">Theirs Path</th>--%>
                        <%--<th class="hide">Parent Path</th>--%>
                    </tr>
                    <% for (Conflict conflict:conflicts) { %>
                    <tr class="conflict-tr" path="<%= conflict.getM_our().getM_filePath()%>">
                        <td id="conflict-ourpath"><%= conflict.getM_our().getM_filePath() %></td>
                        <td id="conflict-theirspath" class="hide"><%= conflict.getM_theirs().getM_filePath() %></td>
                        <td id="conflict-parentpath" class="hide"><%= conflict.getM_father().getM_filePath() %></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
        <div class="row row1" style="height: 55%">
            <%--<span class="options">--%>

                <div id="ours" class="col-3 content-text" style="height:100%">
                    <h3>Ours</h3>
                    <textarea id="textarea-our" class="textarea" readonly></textarea>
                </div>
                <div id="result" class="col-4 content-text result" style="height:100%">
                    <h3>Result</h3>
                    <textarea id="txtarea-result" class="textarea"></textarea>
                </div>

                <div id="theirs" class="col-3 content-text" style="height:100%">
                    <h3>Theirs</h3>
                    <textarea id="textarea-theirs" class="textarea" readonly></textarea>
                </div>
            <%--</span>--%>
        </div>
        <br>
        <div class="row row2" style="height: 15%; margin-top:20px;">
            <div class="col-12">
                <button id="save" class="btn btn-primary" path="" style="left:50%; right:50%;">Save Changes</button>
            </div>
        </div>
    </div>
</body>
<script src="jQuery-v3.4.1.js"></script>
<script src="conflict.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="sweetalert.js"></script>

</html>
