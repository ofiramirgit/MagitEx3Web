<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="WebLogic.WebObjects.Repository" %>
<%@ page import="WebLogic.WebObjects.Notification" %>
<%@ page import="Logic.Node.CommitNode" %>
<%@ page import="Logic.Objects.Commit" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.Files" %>
<!DOCDTYPE html>
<html>
    <head>
        <title>${repository_name}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="repository.css">
        <script>
            myCookies={};
            var kv = document.cookie.split(";");
            for(var id in kv)
            {
                var cookie = kv[id].split("=");
                myCookies[cookie[0].trim()]=cookie[1];
            }
            var url_string = window.location.href;
            var url = new URL(url_string);
            var username = url.searchParams.get("username")
            if(!myCookies['username'])
            {
                window.location.replace('loginPage.jsp');
            }
            else{
                if(username!=myCookies['username'])
                    window.location.replace('loginPage.jsp');
            }
        </script>
    </head>
    <body>

    <%
        ArrayList<Notification> notifications= (ArrayList<Notification>)request.getAttribute("notificationsList");
        ArrayList<CommitNode> commitBranch= (ArrayList<CommitNode>)request.getAttribute("commits");
        Repository repository = (Repository)request.getAttribute("repository");

        Path remoteDataPath = Paths.get("C:\\magit-ex3\\" + request.getParameter("username") + "\\repositories\\" +repository.getName() +"\\RemoteData.txt");
        String remoteName= "-";
        String remoteRepoName = "-";

        if(Files.exists(remoteDataPath))
        {
             String remoteDataStr =new String(Files.readAllBytes(remoteDataPath));
             remoteName= remoteDataStr.split(",")[0];
             remoteRepoName = remoteDataStr.split(",")[1];
        }

    %>
      <div class="container">
        <div class="row row0">
            <button id="logout" type="button" class="btn btn-danger">LOGOUT</button>
        </div>
        <div class="row row1">
          <div class="col-5">
            <h3 id="repoName"><%=repository.getName()%></h3>
          </div>
          <div class="col-1">

          </div>
          <div class="col-6">
            <h4>NOTIFICATIONS</h4>
              <div class="notifications">
                  <% for (Notification noti:notifications) { %>
                  <li><%= noti.getM_text() %></li>
                  <%}%>
              </div>
          </div>
        </div>
        <h3>Branches Details</h3>
        <span id="head_branch_span">Head Branch:  <span id="head_branch"><%=repository.getActiveBranch()%></span></span>
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
          <h4>Commits</h4>
          <button id="commitButton" class="btn btn-primary">COMMIT</button>
          <div class="row row4">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="commits">
                    <table style="width: 100%;">
                        <tr>
                            <th>Sha1</th>
                            <th>message</th>
                            <th>created date</th>
                            <th>creater</th>
                        </tr>
                        <% for (CommitNode commit:commitBranch) { %>
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
            <div class="col-1"></div>
        </div>
          <h4>WC</h4>
          <div class="row row5">
              <div class="col-2"></div>
              <div class="col-8">
                  <button id="wc" class="btn btn-primary">WC</button>
              </div>
              <div class="col-2"></div>
          </div>
          <div id = "collaborateDiv">
              <div id = "Div1"><h3>Collaboration</h3></div>
              <div id = "Div2">
                <button id="pull" class="btn btn-primary">Pull</button>
              </div>
              <div id = "Div4" class="col-4">
                <button id="push" class="btn btn-primary">Push</button>
              </div>
          </div>
          <div id="remote_repo">
              <strong>Remote Repo</strong>
              <div>
                  Remote User: <h5 id="user_rr"><%=remoteName%></h5><br>
                  Remote Repository: <h5 id="repo_rr"><%=remoteRepoName%></h5>
              </div>
          </div>
    </div>
    </body>
    <script src="jQuery-v3.4.1.js"></script>
    <script src="repository.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
</html>