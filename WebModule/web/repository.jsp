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
<%@ page import="Logic.Objects.BranchData" %>
<%@ page import="Logic.Objects.BranchDates" %>
<!DOCDTYPE html>
<html>
    <head>
        <title>${repository_name}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        ArrayList<BranchData> branchData= (ArrayList<BranchData>)request.getAttribute("branchData");
        Repository repository = (Repository)request.getAttribute("repository");
        Boolean isPR = (Boolean)request.getAttribute("is_pr");
        Boolean isLR = (Boolean)request.getAttribute("is_lr");

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
            <div id="remote_repo">
                <strong>Remote Repo</strong>
                <div>
                    User: <span id="user_rr">ofir</span><br>
                    Repo Name: <span id="repo_rr">repo 2</span>
                </div>
            </div>
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
              <div class="col-1"></div>
              <div class="col-10">
                  <div class="brnaches">
                      <table style="width: 100%;">
                          <tr>
                              <th>Branch Name</th>
                              <th>Pointing Sha1</th>
                              <th>Commit Msg</th>
                          </tr>
                          <% for (BranchData branch:branchData) { %>
                          <tr class=branch-tr">
                              <td id="branch-name"><%= branch.getM_BranchName() %></td>
                              <td id="branch-sha1"><%= branch.getM_CommitSha1() %></td>
                              <td id="branch-msg"><%= branch.getM_CommitMessage() %></td>
                          </tr>
                          <%}%>
                      </table>
                  </div>

              </div>
              <div class="col-1"></div>
          </div>

              <div class="row row3">
          <div class="col-3">
              <button id="create_new_branch" type="button" class="btn btn-primary">New Branch</button>
          </div>
          <div class="col-4">
              <button id="checkout_head_branch" type="button" class="btn btn-primary">Check Out Head Branch</button>
          </div>
          <div class="col-3">
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
          <div class="row row10">
              <div class="col-5">
                  <div class="files">
                      <div class="content">
                          <ul class="fa-ul" id="ul-commit-files">
                          </ul>
                      </div>
                  </div>
              </div>
              <div class="col-7">
                  <div class="file_content">
                      <div class="content">
                          <textarea id="content-commit-file" disabled></textarea>
                      </div>
                  </div>
              </div>
          </div>
          <h4>WC</h4>
          <div class="row row5">
              <div class="col-2"></div>
              <div class="col-8">
                  <button id="wc" class="btn btn-primary">WC</button>
              </div>
              <div class="col-2"></div>
          </div>

          <div class="row row6">
              <div class="col-5">
                  <div class="files">
                      <div class="content">
                          <ul class="fa-ul" id="ul-files">
                          </ul>
                      </div>
                  </div>
              </div>
              <div class="col-7">
                  <div class="row row8">
                      <div class="col-10">
                          <div class="file_content">
                              <div class="content">
                                <textarea id="content" disabled></textarea>
                                  <input id="hidden-path" path="" is_folder="" class="hide"/>
                              </div>
                          </div>
                      </div>
                      <div class="col-2">
                          <div id="buttons-wc" class="hide">
                              <button id="create-btn" class="newbtn"><i class="fa fa-plus-circle wc-buttons"></i></button>
                              <span id="create_delete" class="hide">
                                  <button id="edit-btn" class="newbtn"><i class="fa fa-edit wc-buttons"></i></button>
                                  <button id="delete-btn" class="newbtn" ><i class="fa fa-trash wc-buttons"></i></button>
                              </span>
                        </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="row row9">
              <div class="col-2"></div>
              <div class="col-8">
                  <button id="save_file" class="btn btn-primary btn_save hide">save</button>
              </div>
              <div class="col-2"></div>
          </div>
          <%if(isLR) {%>
          <div id = "collaborateDiv" >
              <div id = "colla_div"><h3>Collaboration</h3></div>
              <div id = "pull_div">
                  <button id="pull" class="btn btn-primary">Pull</button>
              </div>
              <div id = "push_div" class="col-4">
                  <button id="push" class="btn btn-primary">Push</button>
              </div>
              <div id = "pull_request_div" class="col-4">
                  <button id="pullRequest" class="btn btn-primary">Pull Request</button>
              </div>
          </div>
          <% } %>
          <%if(isPR) {%>
          <div id = "prDiv" >
              <div><h4>PR</h4></div>
              <div id = "merge_pr_div" class="col-4">
                  <button id="merge_pr_button" class="btn btn-primary">Merge PR</button>
                  <button id="reject_pr_button" class="btn btn-primary">Reject PR</button>
              </div>
          </div>
          <% } %>
      </div>
    </body>
    <script src="jQuery-v3.4.1.js"></script>
    <script src="repository.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
</html>