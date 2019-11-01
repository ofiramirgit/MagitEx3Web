package Servlets;

import WebLogic.WebLogic;
import WebLogic.WebObjects.Notification;
import WebLogic.WebObjects.Repository;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String username= req.getParameter("username");

        Boolean isUserExist = m_WebLogic.userExist(username);
        map.put("user_exist",isUserExist);
        if(!isUserExist)
        {
            Files.createDirectory(Paths.get("C:\\magit-ex3\\"+username));
            Files.createDirectory(Paths.get("C:\\magit-ex3\\"+username+"\\repositories"));
            Files.createFile(Paths.get("C:\\magit-ex3\\"+username+"\\NOTIFICATIONS.txt"));
            Files.createFile(Paths.get("C:\\magit-ex3\\"+username+"\\LAST_LOGGED_OUT.txt"));
            m_WebLogic.setUserLastLogedOut(username);
            Files.write(Paths.get("C:\\magit-ex3\\users.txt"),(username+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);

        }

        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}