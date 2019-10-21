package Servlets;

import Logic.Logic;

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
        String source = "C:\\magit-ex3\\"+ req.getParameter("other_user") +"\\repositories\\" + req.getParameter("repo_name");
        String dest = "C:\\magit-ex3\\" + req.getParameter("username") + "\\repositories";
        String repoName = req.getParameter("new_repo_name");

        logicManager.Clone(source,dest,repoName);
    }
}
