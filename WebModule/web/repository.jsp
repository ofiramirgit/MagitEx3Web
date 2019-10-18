<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCDTYPE html>
<html>
    <head>
        <title>${repository_name}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="repository.css">
    </head>
    <body>

    <%
        List<String> notifications= (List<String>)request.getAttribute("notificationsList");
//        ArrayList<String> users= (ArrayList<String>)request.getAttribute("usersList");
//        ArrayList<String> repositories= (ArrayList<String>)request.getAttribute("repositoriesList");
    %>
      <div class="container">
        <div class="row row0">
            <button id="logout" type="button" class="btn btn-danger">LOGOUT</button>
        </div>
        <div class="row row1">
          <div class="col-5">
            <h3>${repository_name}</h3>
            <span id="remote_repo">Remote Repo:  <span id="user_rr">User</span></span>
          </div>
          <div class="col-1">

          </div>
          <div class="col-6">
            <h4>NOTIFICATIONS</h4>
              <div class="notifications">
                  <% for (String noti:notifications) { %>
                  <li><%= noti %></li>
                  <%}%>
              </div>
          </div>
        </div>
        <h3>Branches Details</h3>
        <span id="head_branch_span">Head Branch:  <span id="head_branch">master</span></span>
        <div class="row row3">
          <div class="col-3">
              <button id="create_new_branch" type="button" class="btn btn-primary">New Branch</button>
          </div>
          <div class="col-4">
              <button id="checkout_head_branch" type="button" class="btn btn-primary">Check Out Head Branch</button>
          </div>
          <div class="col-3">
              <button id="show_all_branches" type="button" class="btn btn-primary">Show All Branches</button>
          </div>
      </div>
    </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.js"
    integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI="
    crossorigin="anonymous"></script>
    <script src="repository.js"></script>

</html>