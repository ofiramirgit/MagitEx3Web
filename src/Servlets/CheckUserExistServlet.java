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
import java.util.List;
@WebServlet(name = "CheckUserExistServlet", urlPatterns = {"/check_user_exist"})
public class CheckUserExistServlet extends HttpServlet {
    WebLogic m_WebLogic = new WebLogic();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username_me = req.getParameter("username-me");
        String username_their = req.getParameter("username-thries");
        String repo_name = req.getParameter("repo_name");

         
    }
}
