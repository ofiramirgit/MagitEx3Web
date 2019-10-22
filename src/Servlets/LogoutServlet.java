package Servlets;

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

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username= req.getParameter("username");
        System.out.println(username);
        m_WebLogic.setUserLastLogedOut(username);
    }

}