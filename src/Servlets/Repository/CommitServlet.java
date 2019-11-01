package Servlets.Repository;

import Logic.Logic;
import WebLogic.WebLogic;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CommitServlet", urlPatterns = {"/commit"})
public class CommitServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String new_commit_msg = req.getParameter("new_commit_msg");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name);

        logicManager.createCommit(new_commit_msg);
        map.put("isValid",true);
        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}