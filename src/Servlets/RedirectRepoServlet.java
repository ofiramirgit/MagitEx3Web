package Servlets;
import Logic.Logic;
import Logic.Node.CommitNode;
import Logic.Objects.BranchData;
import WebLogic.WebLogic;
import WebLogic.WebObjects.Notification;
import WebLogic.WebObjects.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RedirectRepoServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String repository_name = req.getParameter("repository_name");
        Logic logicManager = new Logic(username, "C:\\magit-ex3\\"+ username +"\\repositories\\" + repository_name);
        if(m_WebLogic.userExist(username)) {
            ArrayList<Notification> notificationList = m_WebLogic.getNotifications(username);
            req.setAttribute("notificationsList", notificationList);
            req.setAttribute("user", username);
//            req.setAttribute("repository_name", repository_name);
            File repoFile = new File("C:\\magit-ex3\\"+username+"\\repositories\\"+repository_name);
            Repository repositoryDetails = m_WebLogic.getRepositoryDetails(repoFile);
            req.setAttribute("repository", repositoryDetails);
            ArrayList<CommitNode> CommitBranchList = m_WebLogic.getAllBranchCommits(username,repositoryDetails);
            req.setAttribute("commits", CommitBranchList);
            ArrayList<BranchData> branchData = (ArrayList<BranchData>) logicManager.GetAllBranchesDetails();
            req.setAttribute("branchData", branchData);

            Boolean isPr = Files.exists(Paths.get(logicManager.getPathFolder(".magit"),"PR"));
            req.setAttribute("is_pr", isPr);

            Boolean isLr = Files.exists(Paths.get(logicManager.getPathFolder("branches"),"RTB"));
            req.setAttribute("is_lr", isLr);

            RequestDispatcher rd = req.getRequestDispatcher("repository.jsp");
            rd.forward(req, res);
        }
    }

}
