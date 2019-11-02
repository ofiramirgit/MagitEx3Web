package Servlets.Collaboration;

import Logic.Logic;
import com.google.gson.Gson;
import Logic.OpenAndConflict;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckConflict", urlPatterns = {"/check_conflict"})
public class CheckConflict extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String theirs_user = req.getParameter("theirs_user");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);

        try {
            OpenAndConflict openAndConflict = logicManager.MergePR(theirs_user);
            logicManager.handleOpenChanges(openAndConflict.getOpenChangesList());

            if(!openAndConflict.getConflictList().isEmpty()){
                map.put("isConflicts", true);
            }
            else{
                map.put("isConflicts", false);
                logicManager.createCommit("Merged with " + theirs_user);
                File PR = new File(logicManager.getPathFolder(".magit")+File.separator+"PR");
                logicManager.deleteFolder( new File(PR.toPath() + File.separator + theirs_user));
                if(PR.listFiles()!=null)
                    logicManager.deleteFolder(PR);
                logicManager.deleteFolder(new File(logicManager.getPathFolder("merge")));
            }
            write(res, map);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}