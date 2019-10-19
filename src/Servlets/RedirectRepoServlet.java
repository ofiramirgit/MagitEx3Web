package Servlets;
import WebLogic.WebLogic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RedirectRepoServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String repository_name = req.getParameter("repository_name");
        if(m_WebLogic.userExist(username)) {
            List<String> notificationList = m_WebLogic.getNotifications(username);
            req.setAttribute("notificationsList", notificationList);
            req.setAttribute("user", username);
            req.setAttribute("repository_name", repository_name);
            RequestDispatcher rd = req.getRequestDispatcher("repository.jsp");
            rd.forward(req, res);
        }
    }

}