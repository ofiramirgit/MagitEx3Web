package Servlets;

import Logic.Logic;
import WebLogic.WebLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CommitServlet", urlPatterns = {"/commit"})
public class CommitServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String new_commit_msg = req.getParameter("new_commit_msg");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name);

        logicManager.createCommit(new_commit_msg);

    }
}