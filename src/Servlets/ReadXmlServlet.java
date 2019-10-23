package Servlets;

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
    String path = "C:\\Users\\amira\\Desktop\\Java\\ex3-medium.xml";

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = req.getParameter("username");
//        FileUploadServlet fileUploadServlet = new FileUploadServlet();

//        try {
//            fileUploadServlet.doPost(req, res);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("im here");
            Boolean repositoryAdded = true;
//            logicManager.readXML("C:\\Users\\amira\\Desktop\\Java\\ex3-medium.xml", "C:\\magit-ex3\\"+username + "\\repositories");
            logicManager.readXML("C:\\Users\\OL\\Desktop\\Java Course\\ex3-medium.xml", "C:\\magit-ex3\\" + username + "\\repositories");
            System.out.println("im here2");

            map.put("repositoryAdded", repositoryAdded);
            write(res, map);
        } catch (Exception ex) {
            System.out.println("Exception");
            ex.printStackTrace();
        }

    }


    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }
}