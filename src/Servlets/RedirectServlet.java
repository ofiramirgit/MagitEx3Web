package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedirectServlet extends HttpServlet {
    WebLogic.Logic m_WebLogic = new WebLogic.Logic();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("user", username);
        List<String> notificationList = m_WebLogic.getNotifications(username);
        req.setAttribute("notificationsList", notificationList);
        List<String> usersList = m_WebLogic.getUsers();
        req.setAttribute("usersList", usersList);
        RequestDispatcher rd =  req.getRequestDispatcher("homePage.jsp");
        rd.forward(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
