package Servlets.Collaboration;

import Logic.Logic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "CleanPrDataServlet", urlPatterns = {"/clean_pr"})
public class CleanPrDataServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String theirs_name = req.getParameter("theirs_name");


        Logic logicManager = new Logic(username, repo_name);

        File PR = new File(logicManager.getPathFolder(".magit")+File.separator+"PR");
        logicManager.deleteFolder( new File(PR.toPath() + File.separator + theirs_name));
        if(PR.listFiles()!=null)
            logicManager.deleteFolder(PR);

        logicManager.deleteFolder(new File(logicManager.getPathFolder("merge")));

    }
}