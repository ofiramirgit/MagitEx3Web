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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@WebServlet(name = "ForkServlet", urlPatterns = {"/fork"})
public class ForkServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Logic logicManager = new Logic();
        WebLogic m_WebLogic = new WebLogic();

        String other_user = req.getParameter("other_user");
        String repo_name = req.getParameter("repo_name");
        String username = req.getParameter("username");
        String newRepoName = req.getParameter("new_repo_name");


        String source = "C:\\magit-ex3\\"+ other_user +"\\repositories\\" + repo_name;
        String dest = "C:\\magit-ex3\\" + username + "\\repositories";

        logicManager.Clone(source,dest,newRepoName);

        String remoteDataStr = other_user + "," + repo_name;
        Path path = Paths.get(dest + File.separator + newRepoName + File.separator + "RemoteData.txt");
        Files.write(path, remoteDataStr.getBytes(), StandardOpenOption.CREATE);

        String message = username + " forked " + repo_name + " repository";
        m_WebLogic.addNotification(other_user,message);
    }
}
