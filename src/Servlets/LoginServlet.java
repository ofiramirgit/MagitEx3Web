package Servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    public LoginServlet(){
        super();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String username= req.getParameter("username");
        System.out.println(username);
        map.put("isValid", true);
        write(res, map);
//        if(!isValid) {
//            map.put("isValid", isValid);
//            write(res, map);
//        }
//        else{
////            req.getRequestDispatcher("/HomePage/homePage.jsp").forward(req, res);
////            req.getRequestDispatcher("/homePage.jsp").forward(req,res);
//            req.setAttribute("user",username);
//            RequestDispatcher rd =  req.getRequestDispatcher("homePage.jsp");
//            rd.forward(req, res);
//            rd.include(req, res);
//        }
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}