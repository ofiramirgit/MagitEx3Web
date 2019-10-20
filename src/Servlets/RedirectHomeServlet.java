package Servlets;

import WebLogic.WebLogic;
import WebLogic.WebObjects.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/homePage")
public class RedirectHomeServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("user", username);
        List<String> notificationList = m_WebLogic.getNotifications(username);
        req.setAttribute("notificationsList", notificationList);
        List<String> usersList = m_WebLogic.getUsers(username);
        req.setAttribute("usersList", usersList);
        ArrayList<Repository> repositoriesList = m_WebLogic.getUserRepositories(username);
        req.setAttribute("repositoriesList", repositoriesList);
        RequestDispatcher rd =  req.getRequestDispatcher("homePage.jsp");
        rd.forward(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
