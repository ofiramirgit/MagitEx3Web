package Servlets;

import Logic.Logic;
import WebLogic.WebLogic;
import WebLogic.WebObjects.Repository;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CheckUserExistServlet", urlPatterns = {"/check_user_exist"})
public class CheckUserExistServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();
    Map<String,Object> map=new HashMap<String,Object>();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String theirs_user = req.getParameter("theirs_user");
        Boolean isValid = false;

        Logic logicManager = new Logic(username, "C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);
        File pr = new File(logicManager.getPathFolder(".magit") +File.separator+ "PR");

        for(File file : pr.listFiles())
            if(file.getName().equals(theirs_user))
                isValid = true;


        map.put("isValid", isValid);
        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }
}
