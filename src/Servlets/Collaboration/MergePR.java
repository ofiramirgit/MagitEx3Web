package Servlets.Collaboration;

import Logic.Logic;
import com.google.gson.Gson;
import Logic.OpenAndConflict;
import Logic.OpenChange;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MergePR", urlPatterns = {"/merge_pull_request"})
public class MergePR extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String theirs_user = req.getParameter("theirs_user");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);

        try {
            OpenAndConflict openAndConflict = logicManager.MergePR(theirs_user);
            logicManager.handleOpenChanges(openAndConflict.getOpenChangesList());

            if(!openAndConflict.getConflictList().isEmpty()){
                req.setAttribute("username",username);
                req.setAttribute("repo_name",repo_name);
                req.setAttribute("theirs_user",theirs_user);
                req.setAttribute("openAndConflicts",openAndConflict);
                RequestDispatcher rd =  req.getRequestDispatcher("conflictPage.jsp");
                rd.forward(req,res);
            }

            logicManager.createCommit("Merged with " + theirs_user);
            File PR = new File(logicManager.getPathFolder(".magit")+File.separator+"PR");
            logicManager.deleteFolder( new File(PR.toPath() + File.separator + theirs_user));
            if(PR.listFiles()!=null)
                logicManager.deleteFolder(PR);

            logicManager.deleteFolder(new File(logicManager.getPathFolder("merge")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}