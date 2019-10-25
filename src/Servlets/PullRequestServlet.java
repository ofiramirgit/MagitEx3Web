package Servlets;

import Logic.Logic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PullRequestServlet", urlPatterns = {"/pull_request"})
public class PullRequestServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String RRUserName = req.getParameter("user_name_rr");
        String RRName = req.getParameter("repo_name_rr");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name, "C:\\magit-ex3\\" + RRUserName + "\\repositories\\" + RRName);

        try {
            logicManager.PullRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
