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
        String source = "C:\\magit-ex3\\ofir\\repositories\\repo 2";
        String dest = "C:\\magit-ex3\\amir\\repositories";
        String repoName = "repo 2";

        logicManager.Clone(source,dest,repoName);
    }
}
