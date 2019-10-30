<%@ page import="java.util.List" %>
<%@ page import="WebLogic.WebObjects.Repository" %>
<%@ page import="java.util.ArrayList" %>
<!DOCDTYPE html>
<html>
    <head>
        <title>Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="user.css">
        <script>
            myCookies={};
            var kv = document.cookie.split(";");
            for(var id in kv)
            {
                var cookie = kv[id].split("=");
                myCookies[cookie[0].trim()]=cookie[1];
            }
            if(!myCookies['username'])
            {
                window.location.replace('loginPage.jsp');
            }
        </script>
    </head>
    <body>
    <% ArrayList<Repository> repositories= (ArrayList<Repository>)request.getAttribute("repositoriesList");%>
      <div class="container">
        <div class="row row0">
            <div class="col-3">
                <button id="logout" type="button" class="btn btn-danger">LOGOUT</button>
            </div>
            <div class="col-6"></div>
            <div class="col-3">
                <button id="home-btn" class="home"><i class="fa fa-home wc-buttons"></i></button>
            </div>
        </div>
          <h1 id="other_user">${user}</h1>
        <div class="row row1">
          <div class="col-12">
              <h2>LIST REPOSITORIES</h2>
              <!-- <div class="list-repositories"> -->
              <table class="repo-table">
                  <tr>
                      <th>Name</th>
                      <th>Active Branch</th>
                      <th>Amount Of Branches</th>
                      <th>Last Commit</th>
                      <th>Commit Msg</th>
                  </tr>
                  <% for (Repository repo:repositories) { %>
                  <tr class="repos-tr">
                      <td id="repo-name"><%= repo.getName() %></td>
                      <td id="repo-activeName"><%= repo.getActiveBranch() %></td>
                      <td id="repo-branchNum"><%= repo.getBranchesNumber() %></td>
                      <td id="repo-commitTime"><%= repo.getLastCommitTime() %></td>
                      <td id="repo-commitMsg"><%= repo.getLastCommitMessege() %></td>
                      <td class="fork_btn"><button class="btn btn-primary">FORK</button></td>
                  </tr>
                  <%}%>
              </table>
          </div>
        </div>
      </div>
    </body>
    <script src="jQuery-v3.4.1.js"></script>
    <script src="user.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>

</html>