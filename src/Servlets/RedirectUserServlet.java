package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RedirectUserServlet extends HttpServlet {
    WebLogic.Logic m_WebLogic = new WebLogic.Logic();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);
        req.setAttribute("user", username);
        List<String> repositoriesList = m_WebLogic.getUserRepositories(username);
        req.setAttribute("repositoriesList", repositoriesList);
        RequestDispatcher rd =  req.getRequestDispatcher("user.jsp");
        rd.forward(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

}
