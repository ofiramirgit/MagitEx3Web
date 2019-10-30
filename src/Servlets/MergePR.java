package Servlets;

import Logic.Logic;
import com.google.gson.Gson;
import Logic.OpenAndConflict;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MergePR", urlPatterns = {"/merge_pull_request"})
public class MergePR extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map= new HashMap<>();

        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);

        try {
            OpenAndConflict openAndConflict = logicManager.MergePR("amir");
            req.setAttribute("openAndConflicts",openAndConflict);
//            write(res, map);
            RequestDispatcher rd =  req.getRequestDispatcher("conflictPage.jsp");
            rd.forward(req,res);
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