package Servlets;

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

@WebServlet(name = "CheckOutActiveServlet", urlPatterns = {"/check_out"})
public class CheckOutActiveServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();

        String repo_name = req.getParameter("repo_name");
        String username = req.getParameter("username");
        String branch_name = req.getParameter("branch_name");

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name);
        Boolean isCheckedOut = logicManager.CheckOutHeadBranch(branch_name);
        map.put("isCheckedOut", isCheckedOut);
        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}
