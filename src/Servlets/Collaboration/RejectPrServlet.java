package Servlets.Collaboration;

import Logic.Logic;
import WebLogic.WebLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "RejectPrServlet", urlPatterns = {"/reject_pull_request"})
public class RejectPrServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String theirs_user = req.getParameter("theirs_user");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);
        WebLogic webLogic = new WebLogic();
        webLogic.addNotification(theirs_user, username + " rejected your Pull Request");

        File PR = new File(logicManager.getPathFolder(".magit")+File.separator+"PR");
        logicManager.deleteFolder( new File(PR.toPath() + File.separator + theirs_user));
        if(PR.listFiles()!=null)
            logicManager.deleteFolder(PR);
    }
}
