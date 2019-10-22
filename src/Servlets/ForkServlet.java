package Servlets;

import Logic.Logic;
import WebLogic.WebLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForkServlet", urlPatterns = {"/fork"})
public class ForkServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Logic logicManager = new Logic();
        WebLogic m_WebLogic = new WebLogic();

        String other_user = req.getParameter("other_user");
        String repo_name = req.getParameter("repo_name");
        String username = req.getParameter("username");
        String repoName = req.getParameter("new_repo_name");


        String source = "C:\\magit-ex3\\"+ other_user +"\\repositories\\" + repo_name;
        String dest = "C:\\magit-ex3\\" + username + "\\repositories";

        logicManager.Clone(source,dest,repoName);
        String message = username + " forked " + repo_name + " repository";
        m_WebLogic.addNotification(other_user,message);
    }
}
