package Servlets.Repository;

import Logic.Logic;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ReadXmlServlet", urlPatterns = {"/readXml"})
public class ReadXmlServlet extends HttpServlet {

    Logic logicManager = new Logic();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = req.getParameter("username");
        String filepath = req.getParameter("filepath");

        try {
            Boolean repositoryAdded = true;
            logicManager.readXML(filepath, "C:\\magit-ex3\\" + username + "\\repositories");

            map.put("repositoryAdded", repositoryAdded);
            write(res, map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }
}